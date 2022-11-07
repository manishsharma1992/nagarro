package com.nagarro.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.domain.ERole;
import com.nagarro.domain.Role;
import com.nagarro.domain.User;

@Configuration
public class UserDetailCache {

	@Bean
	public List<User> userList() {
		List<User> userList = new ArrayList<>();
		
		User admin = User.builder()
						.username("admin")
						.password("$2a$12$CwTE8PGManB.IAp6vn9yEeomDKOCW0ua2QWTdlVZ4r/rEM0G34uY.")
						.roles(Stream.of(Role.builder().name(ERole.ROLE_ADMIN).build()).collect(Collectors.toSet()))
					.build();
		
		User user = User.builder()
				.username("user")
				.password("$2a$12$9WnGQlEK6f85dgLUsiaRKuJgxvbnD5jMNlu3DvNZHKT8d3lP7IfTq")
				.roles(Stream.of(Role.builder().name(ERole.ROLE_USER).build()).collect(Collectors.toSet()))
			.build();
		
		userList.add(admin);
		userList.add(user);
		
		return userList;
	}

}
