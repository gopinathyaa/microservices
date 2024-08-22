package com.yaacreations.OrderMicroServices.model;

import java.util.List;

import lombok.Data;

@Data
public class ProductModel { 
	private int productid; 
	private String productname;
	
	private String productdescription;
	
	private int status; 
	private List<ProductItemModel> productItemsmodel;
}
