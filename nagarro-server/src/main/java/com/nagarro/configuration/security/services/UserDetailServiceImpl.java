package com.nagarro.configuration.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.domain.User;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private List<User> userList;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userList
					.stream()
						.filter(usr -> usr.getUsername().equals(username))
						.map(usr -> usr)
						.findFirst()
					.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		
		return UserDetailsImpl.build(user);
	}

}
