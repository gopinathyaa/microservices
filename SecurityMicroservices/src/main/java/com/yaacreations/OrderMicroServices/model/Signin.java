package com.yaacreations.OrderMicroServices.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Signin {
	
	private String username;
	
	private String password;

}
