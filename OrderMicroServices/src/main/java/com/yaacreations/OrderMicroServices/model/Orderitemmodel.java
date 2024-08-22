package com.yaacreations.OrderMicroServices.model;

import lombok.Data;

@Data
public class Orderitemmodel {
	

	 
	private int orderitemsid;
	
	private int productitemid;
	
	private int quantity;
	
	private Double producttotal;

}
