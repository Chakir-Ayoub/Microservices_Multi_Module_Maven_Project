package com.amigoscode;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
public class FraudController {
	private final FraudCheckService fraudCheckService;

	@GetMapping(path = "{customerid}")
	public FraudCheckResponse isFraudster(@PathVariable("customerid") Integer customerid ) {
		boolean isfraudulentcustomer= this.fraudCheckService.FraudulentCustomer(customerid);
		return new FraudCheckResponse(isfraudulentcustomer);
	}
}
