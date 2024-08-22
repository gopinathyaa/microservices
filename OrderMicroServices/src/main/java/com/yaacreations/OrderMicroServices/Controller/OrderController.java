package com.yaacreations.OrderMicroServices.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yaacreations.OrderMicroServices.Service.OrderService;
import com.yaacreations.OrderMicroServices.model.Ordermodel;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	
	@Autowired
	OrderService service;
	
	
	@PostMapping("/add")
	public String addorder(@RequestBody Ordermodel ordermodel) {
		
		String output=service.addorder(ordermodel);
		
		return output;
		
	}

}
