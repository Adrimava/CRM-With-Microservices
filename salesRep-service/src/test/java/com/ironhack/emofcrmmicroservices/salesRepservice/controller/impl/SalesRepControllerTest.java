package com.ironhack.emofcrmmicroservices.salesRepservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.emofcrmmicroservices.salesRepservice.controller.dto.SalesRepDto;
import com.ironhack.emofcrmmicroservices.salesRepservice.model.SalesRep;
import com.ironhack.emofcrmmicroservices.salesRepservice.repository.SalesRepRepository;
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

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
class SalesRepControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private SalesRepRepository salesRepRepository;

	private MockMvc mockMvc;
	private ObjectMapper objectMapper = new ObjectMapper();
	private SalesRep maria;
	private SalesRep luis;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		maria = new SalesRep("Maria");
		luis = new SalesRep("Luis");

		salesRepRepository.saveAll(List.of(maria, luis));
	}

	@AfterEach
	void tearDown() {
		salesRepRepository.deleteAll();
	}

	@Test
	void storeSalesRep_ValidSalesRepDTO_Created() throws Exception {
		SalesRepDto salesRepDTO = new SalesRepDto("Paquito", 10);
		String body = objectMapper.writeValueAsString(salesRepDTO);
		MvcResult result = this.mockMvc.perform(
				post("/store-salesrep")
						.content(body)
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value("Paquito"))
				.andReturn();
		SalesRepDto response = objectMapper.readValue(result.getResponse().getContentAsString(), SalesRepDto.class);
		assertEquals(salesRepRepository.findAll().size(), 3);
	}

	@Test
	void storeSalesRep_InvalidSalesRepDto_Error() throws Exception {
		SalesRepDto salesRepDTO = new SalesRepDto("", 10);
		String body = objectMapper.writeValueAsString(salesRepDTO);
		MvcResult result = this.mockMvc.perform(
				post("/store-salesrep")
						.content(body)
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isBadRequest()).andReturn();
	}

	@Test
	void getSalesRep_ValidId_CorrectSalesRep() throws Exception {

		MvcResult result = this.mockMvc.perform(
				get("/get-salesrep/" + maria.getId())
		).andExpect(status().isOk()).andReturn();

		assertTrue(result.getResponse().getContentAsString().contains("Maria"));
	}

	@Test
	void getSalesRep_InvalidId_NotFound() throws Exception {

		MvcResult result = this.mockMvc.perform(
				get("/get-salesrep/9999999")
		).andExpect(status().isNotFound()).andReturn();
	}

	@Test
	void getAllSalesRep_NoInput_AllSalesReps() throws Exception {

		MvcResult result = this.mockMvc.perform(
				get("/get-salesrep")
		).andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", hasSize(2))).andReturn();

		assertTrue(result.getResponse().getContentAsString().contains("Maria"));
		assertTrue(result.getResponse().getContentAsString().contains("Luis"));
	}

	@Test
	void associateLeadToSalesRep_ValidId_SalesRepUpdated() throws Exception {
		MvcResult result = this.mockMvc.perform(
				put("/salesrep-lead/" + maria.getId() + "/33")
		).andExpect(status().isNoContent()).andReturn();

		result = this.mockMvc.perform(
				get("/get-salesrep/" + maria.getId())
		).andExpect(status().isOk()).andReturn();

		assertTrue(result.getResponse().getContentAsString().contains("33"));
	}

	@Test
	void associateLeadToSalesRep_InvalidId_NotFound() throws Exception {
		MvcResult result = this.mockMvc.perform(
				put("/salesrep-lead/999999/33")
		).andExpect(status().isNotFound()).andReturn();
	}

	@Test
	void associateOppToSalesRep_ValidId_SalesRepUpdated() throws Exception {
		MvcResult result = this.mockMvc.perform(
				put("/salesrep-opp/" + maria.getId() + "/33")
		).andExpect(status().isNoContent()).andReturn();

		result = this.mockMvc.perform(
				get("/get-salesrep/" + maria.getId())
		).andExpect(status().isOk()).andReturn();

		assertTrue(result.getResponse().getContentAsString().contains("33"));
	}

	@Test
	void associateOppToSalesRep_InvalidId_NotFound() throws Exception {
		MvcResult result = this.mockMvc.perform(
				put("/salesrep-opp/999999/33")
		).andExpect(status().isNotFound()).andReturn();
	}
}
