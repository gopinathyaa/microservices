package com.yaacreations.OrderMicroServices.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	
	private String name;
	@Column(nullable = false )
	private String password;
	
	@Column(name = "email",nullable = false,unique = true)
	private String email;
	
	private int status;

}
