package com.ironhack.emofcrmmicroservices.leadservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.emofcrmmicroservices.leadservice.client.SalesRepClient;
import com.ironhack.emofcrmmicroservices.leadservice.controller.dto.LeadDto;
import com.ironhack.emofcrmmicroservices.leadservice.controller.dto.SalesRepDTO;
import com.ironhack.emofcrmmicroservices.leadservice.model.Lead;
import com.ironhack.emofcrmmicroservices.leadservice.repository.LeadRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class LeadControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private LeadRepository leadRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private SalesRepClient salesRepClient;

    private Lead fiona;
    private Lead asno;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        fiona = new Lead("Fiona shrek", "987654321", "fiona@gmail.com", "Cienaga SA", 1);
        asno = new Lead("Asno shrek", "654987321", "asno@gmail.com", "Galletitas SA", 2);

        leadRepository.saveAll(List.of(fiona, asno));
    }

    @AfterEach
    void tearDown() {
        leadRepository.deleteAll();
    }

    @Test
    void getLead_validId_lead() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/get-lead/" + fiona.getId())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Cienaga SA"));
    }

    @Test
    void getLead_invalidId_notFound() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/get-lead/" + fiona.getId()+654)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getAllLeads_allLeads() throws Exception{
        MvcResult result = this.mockMvc.perform(
                get("/get-leads/")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))
                .andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("fiona"));
        assertTrue(result.getResponse().getContentAsString().contains("asno"));
    }

    @Test
    void storeLead_validLead_newLead() throws Exception {
        mockStoreSalesRepDoNothing();
        LeadDto leadDto = new LeadDto("Dragona shrek", "987654321", "fiona@gmail.com", "Cienaga SA", 1);
        String body = objectMapper.writeValueAsString(leadDto);
        MvcResult result = this.mockMvc.perform(
                post("/store-lead")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Dragona shrek"))
                .andReturn();
        LeadDto responseLead = objectMapper.readValue(result.getResponse().getContentAsString(), LeadDto.class);
        assertTrue(leadRepository.findById(responseLead.getId()).isPresent());
    }

    @Test
    void storeLead_invalidPhone_badRequest() throws Exception {
        mockStoreSalesRepDoNothing();
        LeadDto leadDto = new LeadDto("Dragona shrek", "981", "fiona@gmail.com", "Cienaga SA", 1);
        String body = objectMapper.writeValueAsString(leadDto);
        MvcResult result = this.mockMvc.perform(
                post("/store-lead")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();
        assertTrue(result.getResolvedException().getMessage().contains("Not valid phone number format"));
    }

    @Test
    void storeLead_invalidEmail_badRequest() throws Exception {
        mockStoreSalesRepDoNothing();
        LeadDto leadDto = new LeadDto("Dragona shrek", "987654321", "fiona.com", "Cienaga SA", 1);
        String body = objectMapper.writeValueAsString(leadDto);
        MvcResult result = this.mockMvc.perform(
                post("/store-lead")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();
        assertTrue(result.getResolvedException().getMessage().contains("Invalid email"));
    }

    @Test
    void storeLead_invalidName_newLead() throws Exception {
        mockStoreSalesRepDoNothing();
        LeadDto leadDto = new LeadDto("Dragona", "987654321", "fiona@gmail.com", "Cienaga SA", 1);
        String body = objectMapper.writeValueAsString(leadDto);
        MvcResult result = this.mockMvc.perform(
                post("/store-lead")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();
        assertTrue(result.getResolvedException().getMessage().contains("Name must have first name and last name"));
    }



    @Test
    void storeLead_invalidSalesRepId_notFound() throws Exception {
        mockStoreSalesRepNotFound();
        LeadDto leadDto = new LeadDto("Dragona shrek", "987654321", "fiona@gmail.com", "Cienaga SA", 1);
        String body = objectMapper.writeValueAsString(leadDto);
        MvcResult result = this.mockMvc.perform(
                post("/store-lead")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    void storeLead_failSalesRepServer_serviceUnavailable() throws Exception {
        mockStoreSalesRepServerError();
        LeadDto leadDto = new LeadDto("Dragona shrek", "987654321", "fiona@gmail.com", "Cienaga SA", 1);
        String body = objectMapper.writeValueAsString(leadDto);
        MvcResult result = this.mockMvc.perform(
                post("/store-lead")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isServiceUnavailable()).andReturn();
    }

    @Test
    void deleteLead_validId_deleteLead() throws Exception {
        MvcResult result = this.mockMvc.perform(
                delete("/delete-lead/" + asno.getId())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(leadRepository.findById(asno.getId()).isEmpty());
    }

    @Test
    void deleteLead_invalidId_notDeleteLead() throws Exception {
        MvcResult result = this.mockMvc.perform(
                delete("/delete-lead/" + asno.getId()+584)
        ).andExpect(status().isNotFound()).andReturn();
    }


    //These methods are to simulate calls to other microservices.
    private void mockStoreSalesRepDoNothing() {
        Mockito.doReturn(new SalesRepDTO("Mockito", 1)).when(salesRepClient).getSalesrep(anyInt());
        Mockito.doNothing().when(salesRepClient).associateLeadToSalesRep(anyInt(), anyInt());
    }

    private void mockStoreSalesRepNotFound() {
        Mockito.doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(salesRepClient).getSalesrep(anyInt());
        Mockito.doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND)).when(salesRepClient).associateLeadToSalesRep(anyInt(), anyInt());
    }

    private void mockStoreSalesRepServerError() {
        Mockito.doThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)).when(salesRepClient).getSalesrep(anyInt());
        Mockito.doThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)).when(salesRepClient).associateLeadToSalesRep(anyInt(), anyInt());
    }
}