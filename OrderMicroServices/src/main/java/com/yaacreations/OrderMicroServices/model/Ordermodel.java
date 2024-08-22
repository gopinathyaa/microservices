package com.yaacreations.OrderMicroServices.model;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class Ordermodel {

	private int orderid;
	
	private int ordernumber;
	
    private int userid;
    
    private LocalDateTime orderDateTime;
    
    private Double subtotal;
    
    private float gst;
    
    private float discount;
    
    private Double totalamount;
    
    private int status;
     
    private  List<Orderitemmodel> orderitemsmodel;
}
