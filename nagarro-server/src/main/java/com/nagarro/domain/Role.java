package com.nagarro.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {
	
	@Enumerated(EnumType.STRING)
	private ERole name;
}
