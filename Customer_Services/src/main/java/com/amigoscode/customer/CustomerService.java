package com.amigoscode.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	private final RestTemplate restTemplate;
	public void regiqterCustomer(CustomerRequest customerRequest) {
		/*
		 * Customer customer=Customer.builder()
		 * .firstName(customerRequest.getFistname())
		 * .lastName(customerRequest.getFistname()) .email(customerRequest.getEmail())
		 * .build();
		 */
		Customer customer=new Customer();
		customer.setFirstName(customerRequest.getFistname());
		customer.setLastName(customerRequest.getLastname());
		customer.setEmail(customerRequest.getEmail());
		customerRepository.saveAndFlush(customer);
		FraudCheckResponse fraudCheckResponse= restTemplate.getForObject("http://localhost:8081/api/v1/fraud-check/{customerid}",
				FraudCheckResponse.class,
				customer.getId());

		  if(fraudCheckResponse.isFraudster()) { throw new
		  IllegalStateException("Fraudster"); }

	}
	
}
