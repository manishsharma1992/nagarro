package com.nagarro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.domain.Account;

@Repository
public interface AccountsRepo extends JpaRepository<Account, Long> {
	
	public List<Account> findByAccountNumber(String accountNumber);
	
}
