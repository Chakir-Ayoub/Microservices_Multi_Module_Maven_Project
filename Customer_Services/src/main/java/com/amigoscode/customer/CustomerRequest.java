package com.amigoscode.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
	
	private String fistname;
	private String lastname;
	private String email;
}
