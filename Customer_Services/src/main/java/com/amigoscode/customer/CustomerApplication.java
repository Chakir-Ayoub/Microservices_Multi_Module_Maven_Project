package com.amigoscode.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {
		"com.amigoscode.customer",
		"com.amqp"
})
@EnableEurekaClient
@EnableFeignClients(
		basePackages = "com.amigoscode"
)
public class CustomerApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(CustomerApplication.class,args);
	}

}
