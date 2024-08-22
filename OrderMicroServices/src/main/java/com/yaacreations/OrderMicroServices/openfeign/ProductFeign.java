package com.yaacreations.OrderMicroServices.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yaacreations.OrderMicroServices.model.ProductItemModel;
import com.yaacreations.OrderMicroServices.model.ProductModel; 

@FeignClient(name = "ProductMicroServices",path ="/product")
public interface ProductFeign {
	
	@GetMapping("/get/{id}")
	public ProductModel getproductbyid(@PathVariable (value = "id")  int id);

	@GetMapping("/item/get/{id}")
	public ProductItemModel getproductitembyid(@PathVariable (value = "id")  int id);
	
}
