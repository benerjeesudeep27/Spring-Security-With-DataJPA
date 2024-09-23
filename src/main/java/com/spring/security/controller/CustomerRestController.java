package com.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.entity.Customer;
import com.spring.security.repository.CustomerRepository;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public String registerCustomer(@RequestBody Customer customer) {
		String encoderPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encoderPassword);
		customerRepository.save(customer);
		return "Customer Inserted Successfully";
	}

	@PostMapping("/login")
	public ResponseEntity<String> customerLogin(@RequestBody Customer customer) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customer.getUsername(),
				customer.getPassword());
		try {
			Authentication authentication = authenticationManager.authenticate(token);
			if (authentication.isAuthenticated()) {
				return new ResponseEntity<>("Welcome to Spring Security with Data JPA", HttpStatus.OK);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<String>("Invalid Credentials", HttpStatus.BAD_REQUEST);
	}
}
