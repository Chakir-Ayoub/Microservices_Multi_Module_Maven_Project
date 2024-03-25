package com.amigoscode.customer;

import com.amigoscode.FraudCheckResponse;
import com.amigoscode.FraudClient;
import com.amigoscode.notification.NotificationClient;
import com.amigoscode.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	private final RestTemplate restTemplate;
	private final FraudClient fraudClient;
	private final NotificationClient notificationClient;
	public void regiqterCustomer(CustomerRequest customerRequest) {

		Customer customer=new Customer();
		customer.setFirstName(customerRequest.getFistname());
		customer.setLastName(customerRequest.getLastname());
		customer.setEmail(customerRequest.getEmail());
		customerRepository.saveAndFlush(customer);
		FraudCheckResponse fraudCheckResponse= fraudClient.isFraudster(customer.getId());

		  if(fraudCheckResponse.isFraudster()) { throw new
		  IllegalStateException("Fraudster"); }
notificationClient.sendNotification(
		new NotificationRequest(
				customer.getId(),
				customer.getEmail(),
				String.format("Hi %s, welcome to Amigoscode...",
						customer.getFirstName())
		)
);
	}



}
