package com.amigoscode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {
	private final FraudCheckService fraudCheckService;

	@GetMapping(path = "{customerid}")
	public FraudCheckResponse isFraudster(@PathVariable("customerid") Integer customerid ) {
		boolean isfraudulentcustomer= this.fraudCheckService.FraudulentCustomer(customerid);
		log.info("Fraud check request from customer {]",customerid);
		return new FraudCheckResponse(isfraudulentcustomer);
	}
}
