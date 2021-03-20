package com.ironhack.emofcrmmicroservices.opportunityservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.emofcrmmicroservices.opportunityservice.controller.dto.CloseOpportunityDto;
import com.ironhack.emofcrmmicroservices.opportunityservice.controller.dto.ConvertOpportunityDto;
import com.ironhack.emofcrmmicroservices.opportunityservice.enums.Product;
import com.ironhack.emofcrmmicroservices.opportunityservice.enums.Status;
import com.ironhack.emofcrmmicroservices.opportunityservice.model.Opportunity;
import com.ironhack.emofcrmmicroservices.opportunityservice.repository.OpportunityRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class OpportunityControllerTest {

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Opportunity> oppList;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Opportunity opportunity1 = new Opportunity(Product.HYBRID, 20, 1, Status.OPEN, 1);
        Opportunity opportunity2 = new Opportunity(Product.BOX, 50, 2, Status.CLOSED_LOST, 1);
        oppList = new ArrayList<>();
        oppList.add(opportunityRepository.save(opportunity1));
        oppList.add(opportunityRepository.save(opportunity2));
    }

    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
    }

    @Test
    void getOpportunity_correctId_ok() throws Exception {
        MvcResult result = mockMvc.perform(get("/get-opportunity/" + oppList.get(0).getId())).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("HYBRID"));

        result = mockMvc.perform(get("/get-opportunity/" + oppList.get(1).getId())).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("BOX"));
    }

    @Test
    void convertOpportunity() throws Exception {
        ConvertOpportunityDto convertOpportunityDto = new ConvertOpportunityDto();
        convertOpportunityDto.setLeadId(1);
        convertOpportunityDto.setProduct("HYBRID");
        convertOpportunityDto.setQuantity(50);
        convertOpportunityDto.setAccountId(1);
        convertOpportunityDto.setIndustry("MEDICAL");
        convertOpportunityDto.setEmployeeCount(100);
        convertOpportunityDto.setCity("Barcelona");
        convertOpportunityDto.setCountry("Spain");
        String body = objectMapper.writeValueAsString(convertOpportunityDto);
        MvcResult result = mockMvc.perform(post("/convert-opportunity").content(body).contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isNoContent()).andReturn();
    }

    @Test
    void closeOpportunity() throws Exception {
        CloseOpportunityDto closeOpportunityDto = new CloseOpportunityDto();
        closeOpportunityDto.setOpportunityId(oppList.get(0).getId());
        closeOpportunityDto.setStatus("CLOSED_WON");
        String body = objectMapper.writeValueAsString(closeOpportunityDto);
        mockMvc.perform(put("/close-opportunity").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();
        assertEquals(opportunityRepository.findById(oppList.get(0).getId()).get().getStatus(), Status.CLOSED_WON);
    }

    @Test
    void getMeanQuantityOrderedProducts_Valid_Mean() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/report/quantity-ordered-products/mean")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("35"));

    }

    @Test
    void getMeanQuantityOrderedProducts_Empty_NotFound() throws Exception {

        opportunityRepository.deleteAll();

        MvcResult result = this.mockMvc.perform(
                get("/report/quantity-ordered-products/mean")
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getMaxQuantityOrderedProducts_Valid_Max() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/report/quantity-ordered-products/max")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("50"));

    }

    @Test
    void getMaxQuantityOrderedProducts_Empty_NotFound() throws Exception {

        opportunityRepository.deleteAll();

        MvcResult result = this.mockMvc.perform(
                get("/report/quantity-ordered-products/max")
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getMinQuantityOrderedProducts_Valid_Min() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/report/quantity-ordered-products/min")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("20"));

    }

    @Test
    void getMinQuantityOrderedProducts_Empty_NotFound() throws Exception {

        opportunityRepository.deleteAll();

        MvcResult result = this.mockMvc.perform(
                get("/report/quantity-ordered-products/min")
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getMedianQuantityOrderedProducts_Valid_Median() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/report/quantity-ordered-products/median")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("35"));

    }

    @Test
    void getMedianQuantityOrderedProducts_Empty_NotFound() throws Exception {

        opportunityRepository.deleteAll();

        MvcResult result = this.mockMvc.perform(
                get("/report/quantity-ordered-products/median")
        ).andExpect(status().isNotFound()).andReturn();
    }

    
}
