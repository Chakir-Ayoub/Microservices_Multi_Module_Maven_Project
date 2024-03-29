package com.amigoscode.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@PostMapping
	public void registerCustomer(@RequestBody CustomerRequest customerRequest) {
		customerService.regiqterCustomer(customerRequest);
		log.info("new customer registrqtion {} ",customerRequest);
	}
}
