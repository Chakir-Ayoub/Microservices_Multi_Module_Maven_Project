package com.amigoscode;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FraudCheckService {
	private final FraudCheckHistoryRepository checkHistoryRepository;
	


	public boolean FraudulentCustomer(Integer customerid) {
		FraudCheckHistory fraudCheckHistory=new FraudCheckHistory();
		fraudCheckHistory.setIsFraudster(false);
		fraudCheckHistory.setCustomerId(customerid);
		fraudCheckHistory.setCreatedAt(LocalDateTime.now());
		checkHistoryRepository.save(fraudCheckHistory);
		
		return false;
	}
}
