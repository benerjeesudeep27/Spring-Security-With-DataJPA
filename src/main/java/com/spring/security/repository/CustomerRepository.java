package com.spring.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	Customer findByUsername(String username);
}
