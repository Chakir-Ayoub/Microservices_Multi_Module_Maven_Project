package com.amigoscode.customer;

import com.amigoscode.FraudCheckResponse;
import com.amigoscode.FraudClient;
import com.amigoscode.notification.NotificationClient;
import com.amigoscode.notification.NotificationRequest;
import com.amqp.RabbitMQMessageProducer;
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
	private final RabbitMQMessageProducer rabbitMQMessageProducer;
	public void regiqterCustomer(CustomerRequest customerRequest) {

		Customer customer=new Customer();
		customer.setFirstName(customerRequest.getFistname());
		customer.setLastName(customerRequest.getLastname());
		customer.setEmail(customerRequest.getEmail());
		customerRepository.saveAndFlush(customer);
		FraudCheckResponse fraudCheckResponse= fraudClient.isFraudster(customer.getId());

		  if(fraudCheckResponse.isFraudster()) { throw new
		  IllegalStateException("Fraudster"); }

		  NotificationRequest notificationRequest=new NotificationRequest(
				  customer.getId(),
				  customer.getEmail(),
				  String.format("Hi %s, welcome to Amigoscode...",
						  customer.getFirstName())
);
		  rabbitMQMessageProducer.publish(
				  notificationRequest,
				  "internal.exchange",
				  "internal.notification.routing-key"
		  );
	}



}
