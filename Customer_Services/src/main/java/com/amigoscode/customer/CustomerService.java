package com.amigoscode.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	public void regiqterCustomer(CustomerRequest customerRequest) {
		Customer customer=Customer.builder()
				.firstName(customerRequest.getFistname())
				.lastName(customerRequest.getFistname())
				.email(customerRequest.getEmail())
				.build();
		customerRepository.save(customer);
	}
}
