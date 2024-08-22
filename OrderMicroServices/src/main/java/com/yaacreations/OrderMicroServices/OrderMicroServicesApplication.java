package com.yaacreations.OrderMicroServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.*"})
@EntityScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.*"})
@EnableFeignClients
@EnableDiscoveryClient
public class OrderMicroServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderMicroServicesApplication.class, args);
	}

}
