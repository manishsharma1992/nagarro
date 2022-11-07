package com.nagarro.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.Application;
import com.nagarro.domain.payload.request.AccountFilterRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class AccountControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void givenAccountDetails_whenSucessfull_thenStatus200() throws Exception {
		
		mvc.perform(
				get("/rest/api/fetch-all-accounts")
					.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void givenAccountDetails_whenNoParamsProvided_thenStatus200() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String requestJSON = mapper.writeValueAsString(AccountFilterRequest.builder()
				.acctId(null)
				.amountRange(new ArrayList<Double>())
				.dateRange(new ArrayList<Date>())
				.build());
		
		mvc.perform(
				post("/rest/api/fetchByFilterParams")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJSON))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void givenAccountDetails_whenAcctIdProvided_thenStatus200() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String requestJSON = mapper.writeValueAsString(AccountFilterRequest.builder()
				.acctId(1L)
				.amountRange(new ArrayList<Double>())
				.dateRange(new ArrayList<Date>())
				.build());
		
		mvc.perform(
				post("/rest/api/fetchByFilterParams")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJSON))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void givenAccountDetails_whenAcctDateRangeProvided_thenStatus200() throws Exception {
		List<Date> dateRange = new ArrayList<>();
		dateRange.add(new Date());
		dateRange.add(DateUtils.addMonths(new Date(), 3));
		ObjectMapper mapper = new ObjectMapper();
		String requestJSON = mapper.writeValueAsString(AccountFilterRequest.builder()
				.acctId(null)
				.amountRange(new ArrayList<Double>())
				.dateRange(dateRange)
				.build());
		
		mvc.perform(
				post("/rest/api/fetchByFilterParams")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJSON))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void givenAccountDetails_whenAmountRangeProvided_thenStatus200() throws Exception {
		List<Double> amountRange = new ArrayList<>();
		amountRange.add(101.0d);
		amountRange.add(202.0d);
		ObjectMapper mapper = new ObjectMapper();
		String requestJSON = mapper.writeValueAsString(AccountFilterRequest.builder()
				.acctId(null)
				.amountRange(amountRange)
				.dateRange(new ArrayList<Date>())
				.build());
		
		mvc.perform(
				post("/rest/api/fetchByFilterParams")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJSON))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@Test
	public void givenAccountDetails_whenAllParamsProvided_thenStatus200() throws Exception {
		List<Double> amountRange = new ArrayList<>();
		amountRange.add(101.0d);
		amountRange.add(202.0d);
		
		List<Date> dateRange = new ArrayList<>();
		dateRange.add(new Date());
		dateRange.add(DateUtils.addMonths(new Date(), 3));
		ObjectMapper mapper = new ObjectMapper();
		String requestJSON = mapper.writeValueAsString(AccountFilterRequest.builder()
				.acctId(1L)
				.amountRange(amountRange)
				.dateRange(dateRange)
				.build());
		
		mvc.perform(
				post("/rest/api/fetchByFilterParams")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestJSON))
		.andDo(print())
		.andExpect(status().isOk());
		
	}

}
