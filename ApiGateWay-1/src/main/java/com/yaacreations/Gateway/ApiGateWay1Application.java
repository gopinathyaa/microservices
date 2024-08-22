package com.yaacreations.Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.*"})
@EnableDiscoveryClient
public class ApiGateWay1Application {

	public static void main(String[] args) {
		SpringApplication.run(ApiGateWay1Application.class, args);
	}

}
