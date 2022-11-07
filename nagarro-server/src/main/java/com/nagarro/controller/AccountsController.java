package com.nagarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.domain.Account;
import com.nagarro.domain.payload.request.AccountFilterRequest;
import com.nagarro.service.AccountsService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/rest/api/")
@Slf4j
public class AccountsController {
	
	@Autowired
	private AccountsService accountsService;
	
	@GetMapping("fetch-all-accounts")
	public ResponseEntity<?> fetchAllAccounts() {
		return ResponseEntity.ok(accountsService.fetchAllAccounts());
		
	}
	
	@PostMapping("fetchByFilterParams")
	public ResponseEntity<?> fetchByFilterParams(@Validated @RequestBody AccountFilterRequest request) {
		log.info("Fetching by parameters", request);
		
		List<Account> accountList = accountsService.fetchDataWithParams(request);

		return ResponseEntity.ok(accountList);
	}

}
