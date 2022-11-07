package com.nagarro.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	
	private String username;
	
	private String password;
	
	@Builder.Default
	private Set<Role> roles = new HashSet<>();

}
