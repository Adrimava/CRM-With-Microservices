package com.ironhack.emofcrmmicroservices.contactservice.controller.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.emofcrmmicroservices.contactservice.dtos.LeadDto;
import com.ironhack.emofcrmmicroservices.contactservice.repository.ContactRepository;
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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ContactControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ContactRepository contactRepository;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
        contactRepository.deleteAll();
    }

    @Test
    void storeContact_validDto_storedContact() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setName("Imanol Arias");
        leadDto.setPhoneNumber("123789456");
        leadDto.setEmail("arias@ari.as");
        leadDto.setCompanyName("Cuentame");

        String body = objectMapper.writeValueAsString(leadDto);
        MvcResult result = mockMvc.perform(
                post("/store-contact")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Imanol"));
    }

    @Test
    void storeContact_invalidDto_noStoredContact() throws Exception {
        LeadDto leadDto = new LeadDto();
        leadDto.setPhoneNumber("123789456");
        leadDto.setEmail("arias@ari.as");
        leadDto.setCompanyName("Cuentame");

        String body = objectMapper.writeValueAsString(leadDto);
        MvcResult result = mockMvc.perform(
                post("/store-contact")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();
    }

}