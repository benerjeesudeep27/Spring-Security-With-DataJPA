package com.spring.security.service;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.entity.Customer;
import com.spring.security.repository.CustomerRepository;

@Service
public class MySecurityService implements UserDetailsService{

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = repository.findByUsername(username);

		return new User(customer.getUsername(), customer.getPassword(), Collections.emptyList());
	}
	
}
