package com.amigoscode.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	public void regiqterCustomer(CustomerRequest customerRequest) {
		Customer customer=Customer.builder()
				.firstName(customerRequest.getFistname())
				.lastName(customerRequest.getFistname())
				.email(customerRequest.getEmail())
				.build();
	}
}
