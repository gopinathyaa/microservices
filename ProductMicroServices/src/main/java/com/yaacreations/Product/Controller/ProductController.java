package com.yaacreations.Product.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yaacreations.Product.Model.ProductItemModel;
import com.yaacreations.Product.Model.ProductModel;
import com.yaacreations.Product.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@PostMapping("/add")	
	public String addproduct(@RequestBody ProductModel productModel) {
		String output=service.addproduct(productModel);
		return output;
	}
	
	@GetMapping("/get/{id}")
	public ProductModel getproductbyid(@PathVariable (value = "id")  int id) {
		return service.getproduct(id);
	}
	
	@GetMapping("/item/get/{id}")
	public ProductItemModel getproductitembyid(@PathVariable (value = "id")  int id) {
		return service.getproductitem(id);
	}
	

}
