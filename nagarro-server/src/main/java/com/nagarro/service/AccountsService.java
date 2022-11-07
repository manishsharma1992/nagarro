package com.nagarro.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.domain.Account;
import com.nagarro.domain.payload.request.AccountFilterRequest;
import com.nagarro.repository.AccountsRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountsService {
	
	@Autowired
	private AccountsRepo accountsRepo;
	
	public List<Account> fetchAllAccounts() {
		log.info("Fetching account details...");
		return accountsRepo.findAll().stream().map(account -> {
			account.setAccountNumber(account.getAccountNumber().replaceAll("\\w(?=\\w{4})", "*"));
			account.setStatements(account.getStatements().stream().filter(stmt -> checkDefaultDateRange(stmt.getDatefield())).collect(Collectors.toList()));
			return account;
		})
		.filter(account -> !account.getStatements().isEmpty())
		.collect(Collectors.toList());
	}

	public List<Account> fetchDataWithParams(AccountFilterRequest request) {
		log.debug("Fetching accounts");
		List<Account> accountList = new ArrayList<>();
		
		if(checkIfAccountIdIsEmpty(request) 
				&& checkIfDateRangeIsEmpty(request)
				&& (ObjectUtils.isNotEmpty(request.getAmountRange()) && checkRangeIfEmpty(request.getAmountRange()))) {
			List<Account> allAccounts = accountsRepo.findAll();
			accountList = allAccounts.stream().map(account -> {
				account.setAccountNumber(account.getAccountNumber().replaceAll("\\w(?=\\w{4})", "*"));
				account.setStatements(account.getStatements().stream().filter(stmt -> checkDefaultDateRange(stmt.getDatefield())).collect(Collectors.toList()));
				return account;
			})
			.filter(account -> !account.getStatements().isEmpty())
			.collect(Collectors.toList());
		}
		
		if(!checkIfAccountIdIsEmpty(request) 
				&& checkIfDateRangeIsEmpty(request)
				&& (ObjectUtils.isNotEmpty(request.getAmountRange()) && checkRangeIfEmpty(request.getAmountRange()))) {
			List<Account> allAccounts = new ArrayList<>();
			accountsRepo.findById(request.getAcctId()).map(acct -> {
				acct.setAccountNumber(acct.getAccountNumber().replaceAll("\\w(?=\\w{4})", "*"));
				return acct;
			}).ifPresent(allAccounts::add);
			accountList = allAccounts;
		}
		
		if(checkIfAccountIdIsEmpty(request)
				&& (ObjectUtils.isNotEmpty(request.getDateRange())) && !request.getDateRange().isEmpty()
				&& (ObjectUtils.isNotEmpty(request.getAmountRange()) && checkRangeIfEmpty(request.getAmountRange()))) {
			List<Account> allAccounts = accountsRepo.findAll();
			accountList = allAccounts.stream().map(account -> {
				account.setAccountNumber(account.getAccountNumber().replaceAll("\\w(?=\\w{4})", "*"));
				account.setStatements(account.getStatements().stream().filter(stmt -> checkDefaultDateRange(stmt.getDatefield(), request.getDateRange())).collect(Collectors.toList()));
				return account;
			})
			.filter(account -> !account.getStatements().isEmpty())
			.collect(Collectors.toList());
			
		}
		
		if(checkIfAccountIdIsEmpty(request)
				&& checkIfDateRangeIsEmpty(request)
				&& (ObjectUtils.isNotEmpty(request.getAmountRange()) && !checkRangeIfEmpty(request.getAmountRange()))) {
			List<Account> allAccounts = accountsRepo.findAll();
			accountList = allAccounts.stream().map(account -> {
				account.setAccountNumber(account.getAccountNumber().replaceAll("\\w(?=\\w{4})", "*"));
				account.setStatements(account.getStatements().stream().filter(stmt -> checkAmountRange(stmt.getAmount(), request.getAmountRange())).collect(Collectors.toList()));
				return account;
			})
			.filter(account -> !account.getStatements().isEmpty())
			.collect(Collectors.toList());
		}
		
		if(!checkIfAccountIdIsEmpty(request)
				&& (ObjectUtils.isNotEmpty(request.getDateRange())) && !request.getDateRange().isEmpty()
				&& (ObjectUtils.isNotEmpty(request.getAmountRange()) && checkRangeIfEmpty(request.getAmountRange()))) {
			List<Account> allAccounts = new ArrayList<>();
			accountsRepo.findById(request.getAcctId()).ifPresent(allAccounts::add);
			accountList = allAccounts.stream().map(account -> {
				account.setAccountNumber(account.getAccountNumber().replaceAll("\\w(?=\\w{4})", "*"));
				account.setStatements(account.getStatements().stream().filter(stmt -> checkDefaultDateRange(stmt.getDatefield(), request.getDateRange())).collect(Collectors.toList()));
				return account;
			})
			.filter(account -> !account.getStatements().isEmpty())
			.collect(Collectors.toList());
			
		}
		
		if(!checkIfAccountIdIsEmpty(request)
				&& checkIfDateRangeIsEmpty(request)
				&& (ObjectUtils.isNotEmpty(request.getAmountRange()) && !checkRangeIfEmpty(request.getAmountRange()))) {
			List<Account> allAccounts = new ArrayList<>();
			accountsRepo.findById(request.getAcctId()).ifPresent(allAccounts::add);
			accountList = allAccounts.stream().map(account -> {
				account.setAccountNumber(account.getAccountNumber().replaceAll("\\w(?=\\w{4})", "*"));
				account.setStatements(account.getStatements().stream().filter(stmt -> checkAmountRange(stmt.getAmount(), request.getAmountRange())).collect(Collectors.toList()));
				return account;
			})
			.filter(account -> !account.getStatements().isEmpty())
			.collect(Collectors.toList());
		}
		
		if(!checkIfAccountIdIsEmpty(request)
				&& (ObjectUtils.isNotEmpty(request.getDateRange())) && !request.getDateRange().isEmpty()
				&& (ObjectUtils.isNotEmpty(request.getAmountRange()) && !checkRangeIfEmpty(request.getAmountRange()))) {
			List<Account> allAccounts = new ArrayList<>();
			accountsRepo.findById(request.getAcctId()).ifPresent(allAccounts::add);
			accountList = allAccounts.stream().map(account -> {
				account.setAccountNumber(account.getAccountNumber().replaceAll("\\w(?=\\w{4})", "*"));
				account.setStatements(account.getStatements().stream()
						.filter(stmt -> checkDefaultDateRange(stmt.getDatefield(), request.getDateRange()))
						.filter(stmt -> checkAmountRange(stmt.getAmount(), request.getAmountRange()))
						.collect(Collectors.toList()));
				return account;
			})
			.filter(account -> !account.getStatements().isEmpty())
			.collect(Collectors.toList());
		}
		
		return accountList;
	}

	private boolean checkIfAccountIdIsEmpty(AccountFilterRequest request) {
		if(ObjectUtils.isEmpty(request.getAcctId())) {
			return true;
		}
		
		return ObjectUtils.isNotEmpty(request.getAcctId()) && request.getAcctId() == 0L;
	}
	
	private boolean checkIfDateRangeIsEmpty(AccountFilterRequest request) {
		if(ObjectUtils.isEmpty(request.getDateRange())) {
			return true;
		}

		return ObjectUtils.isNotEmpty(request.getDateRange()) && request.getDateRange().isEmpty();
	}
	
	private boolean checkRangeIfEmpty(List<Double> amountRange) {
		Double from = amountRange.get(0);
		Double to = amountRange.get(1);
		
		return Double.compare(from, 0d) == 0 && Double.compare(to, 0d) == 0;
		
	}
	
	private boolean checkDefaultDateRange(String dateStr) {
		String currentDateStr = "01.10.2020";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate localDate = LocalDate.parse(dateStr, dtf);
		LocalDate currentDate = LocalDate.parse(currentDateStr, dtf);
		LocalDate fromDate = currentDate.minusMonths(3);
		
		return localDate.isAfter(fromDate) && localDate.isBefore(currentDate);
	}
	
	private boolean checkDefaultDateRange(String dateStr, List<Date> dateRange) {
		LocalDate fromDate = dateRange.get(0).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate toDate = dateRange.get(1).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		LocalDate localDate = LocalDate.parse(dateStr, dtf);
		
		return localDate.isAfter(fromDate) && localDate.isBefore(toDate);
		
	}
	
	private boolean checkAmountRange(Double amount, List<Double> amountRange) {
		return (amount >= amountRange.get(0) && amount <= amountRange.get(1));
	}

}
