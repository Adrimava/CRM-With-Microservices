package com.ironhack.emofcrmmicroservices.accountmicroservice.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.ironhack.emofcrmmicroservices.accountmicroservice.controller.dto.*;
import com.ironhack.emofcrmmicroservices.accountmicroservice.enums.*;
import com.ironhack.emofcrmmicroservices.accountmicroservice.model.*;
import com.ironhack.emofcrmmicroservices.accountmicroservice.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ContactIdRepository contactIdRepository;

    @Autowired
    private OpportunityIdRepository opportunityIdRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private Account account1;
    private Account account2;
    private Account account3;
    private ContactId contactId1;
    private ContactId contactId2;
    private ContactId contactId3;
    private ContactId contactId4;
    private OpportunityId opportunityId1;
    private OpportunityId opportunityId2;
    private OpportunityId opportunityId3;
    private OpportunityId opportunityId4;
    private OpportunityId opportunityId5;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        account1 = new Account();
        account1.setIndustry(Industry.ECOMMERCE);
        account1.setEmployeeCount(40);
        account1.setCity("Madrid");
        account1.setCountry("Spain");

        account2 = new Account();
        account2.setIndustry(Industry.MEDICAL);
        account2.setEmployeeCount(100);
        account2.setCity("Málaga");
        account2.setCountry("Spain");

        account3 = new Account();
        account3.setIndustry(Industry.MANUFACTURING);
        account3.setEmployeeCount(80);
        account3.setCity("Sevilla");
        account3.setCountry("Spain");
        accountRepository.saveAll(List.of(account1, account2, account3));

        contactId1 = new ContactId(1, account1);
        contactId2 = new ContactId(2, account2);
        contactId3 = new ContactId(3, account3);
        contactId4 = new ContactId(4, account3);
        contactIdRepository.saveAll(List.of(contactId1, contactId2, contactId3, contactId4));

        opportunityId1 = new OpportunityId(1, account1);
        opportunityId2 = new OpportunityId(2, account2);
        opportunityId3 = new OpportunityId(3, account3);
        opportunityId4 = new OpportunityId(4, account3);
        opportunityId5 = new OpportunityId(5, account3);
        opportunityIdRepository.saveAll(List.of(opportunityId1, opportunityId2, opportunityId3, opportunityId4, opportunityId5));

        account1.setContactList(List.of(contactId1));
        account1.setOpportunityList(List.of(opportunityId1));

        account2.setContactList(List.of(contactId2));
        account2.setOpportunityList(List.of(opportunityId2));

        account3.setContactList(List.of(contactId3, contactId4));
        account3.setOpportunityList(List.of(opportunityId3, opportunityId4, opportunityId5));

        accountRepository.saveAll(List.of(account1, account2, account3));
    }

    @AfterEach
    void tearDown() {
        contactIdRepository.deleteAll();
        opportunityIdRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void showAccount_ValidId_CorrectAccount() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/get-account/" + account1.getId())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Madrid"));
    }

    @Test
    void showAccount_InvalidId_NotFound() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/get-account/10")
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void showAccounts_NoInput_AllAccounts() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/get-account")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(3))).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Madrid"));
        assertTrue(result.getResponse().getContentAsString().contains("100"));
    }

    @Test
    void storeAccount_ValidAccountDTO_Created() throws Exception {
        AccountDto accountDTO = new AccountDto();
        accountDTO.setIndustry("MEDICAL");
        accountDTO.setEmployeeCount(40);
        accountDTO.setCity("Oviedo");
        accountDTO.setCountry("Spain");
        accountDTO.setContactList(List.of(2, 4, 6));
        accountDTO.setOpportunityList(List.of(1, 3, 5));

        String body = objectMapper.writeValueAsString(accountDTO);

        MvcResult result = this.mockMvc.perform(
                post("/store-account")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();

        MvcResult result2 = this.mockMvc.perform(
                get("/get-account")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result2.getResponse().getContentAsString().contains("Oviedo"));

    }

    @Test
    void storeAccount_InvalidAccountDTO_Error() throws Exception {
        AccountDto accountDTO = new AccountDto();
        accountDTO.setIndustry("MEDICAL");
        accountDTO.setEmployeeCount(40);
        accountDTO.setCity("");
        accountDTO.setCountry("");
        accountDTO.setContactList(List.of(2, 4, 6));
        accountDTO.setOpportunityList(List.of(1, 3, 5));

        String body = objectMapper.writeValueAsString(accountDTO);

        MvcResult result = this.mockMvc.perform(
                post("/store-account")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest()).andReturn();

        MvcResult result2 = this.mockMvc.perform(
                get("/get-account")
        ).andExpect(status().isOk()).andReturn();

    }

    @Test
    void updateAccount_ValidId_AccountUpdated() throws Exception {
        UpdateAccountDto updateAccountDTO = new UpdateAccountDto(account1.getId(),123,456);

        String body = objectMapper.writeValueAsString(updateAccountDTO);

        MvcResult result = this.mockMvc.perform(
                put("/update-account")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent()).andReturn();

        MvcResult result2 = this.mockMvc.perform(
                get("/get-account/" + account1.getId())
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result2.getResponse().getContentAsString().contains("456"));

    }

    @Test
    void updateAccount_InvalidId_NotFound() throws Exception {
        UpdateAccountDto updateAccountDTO = new UpdateAccountDto(1000,123,456);

        String body = objectMapper.writeValueAsString(updateAccountDTO);

        MvcResult result = this.mockMvc.perform(
                put("/update-account")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();

    }

    @Test
    void getMeanOppsPerAccount_Valid_Mean() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/report/opps-per-account/mean")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("1.6"));

    }

    @Test
    void getMeanOppsPerAccount_Empty_NotFound() throws Exception {

        contactIdRepository.deleteAll();
        opportunityIdRepository.deleteAll();
        accountRepository.deleteAll();

        MvcResult result = this.mockMvc.perform(
                get("/report/opps-per-account/mean")
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getMaxOppsPerAccount_Valid_Max() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/report/opps-per-account/max")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("3"));

    }

    @Test
    void getMaxOppsPerAccount_Empty_NotFound() throws Exception {

        contactIdRepository.deleteAll();
        opportunityIdRepository.deleteAll();
        accountRepository.deleteAll();

        MvcResult result = this.mockMvc.perform(
                get("/report/opps-per-account/max")
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getMinOppsPerAccount_Valid_Min() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/report/opps-per-account/min")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("1"));

    }

    @Test
    void getMinOppsPerAccount_Empty_NotFound() throws Exception {

        contactIdRepository.deleteAll();
        opportunityIdRepository.deleteAll();
        accountRepository.deleteAll();

        MvcResult result = this.mockMvc.perform(
                get("/report/opps-per-account/min")
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getMedianOppsPerAccount_Valid_Median() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/report/opps-per-account/median")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("1.0"));

    }

    @Test
    void getMedianOppsPerAccount_Empty_NotFound() throws Exception {

        contactIdRepository.deleteAll();
        opportunityIdRepository.deleteAll();
        accountRepository.deleteAll();

        MvcResult result = this.mockMvc.perform(
                get("/report/opps-per-account/median")
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getMeanEmployeeCount_Valid_Mean() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/report/employee-count/mean")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("73.33"));

    }

    @Test
    void getMeanEmployeeCount_Empty_NotFound() throws Exception {

        contactIdRepository.deleteAll();
        opportunityIdRepository.deleteAll();
        accountRepository.deleteAll();

        MvcResult result = this.mockMvc.perform(
                get("/report/employee-count/mean")
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getMaxEmployeeCount_Valid_Max() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/report/employee-count/max")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("100"));

    }

    @Test
    void getMaxEmployeeCount_Empty_NotFound() throws Exception {

        contactIdRepository.deleteAll();
        opportunityIdRepository.deleteAll();
        accountRepository.deleteAll();

        MvcResult result = this.mockMvc.perform(
                get("/report/employee-count/max")
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getMinEmployeeCount_Valid_Min() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/report/employee-count/min")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("40"));

    }

    @Test
    void getMinEmployeeCount_Empty_NotFound() throws Exception {

        contactIdRepository.deleteAll();
        opportunityIdRepository.deleteAll();
        accountRepository.deleteAll();

        MvcResult result = this.mockMvc.perform(
                get("/report/employee-count/min")
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    void getMedianEmployeeCount_Valid_Median() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/report/employee-count/median")
        ).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("80"));

    }

    @Test
    void getMedianEmployeeCount_Empty_NotFound() throws Exception {

        contactIdRepository.deleteAll();
        opportunityIdRepository.deleteAll();
        accountRepository.deleteAll();

        MvcResult result = this.mockMvc.perform(
                get("/report/employee-count/median")
        ).andExpect(status().isNotFound()).andReturn();
    }



}