package com.yaacreations.OrderMicroServices.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Service;

import com.yaacreations.OrderMicroServices.Entity.OrderDto;
import com.yaacreations.OrderMicroServices.Entity.OrderitemsDto;
import com.yaacreations.OrderMicroServices.Reppo.OrderItemsReppo;
import com.yaacreations.OrderMicroServices.Reppo.OrderReppo;
import com.yaacreations.OrderMicroServices.model.Orderitemmodel;
import com.yaacreations.OrderMicroServices.model.Ordermodel;
import com.yaacreations.OrderMicroServices.model.ProductItemModel;
import com.yaacreations.OrderMicroServices.openfeign.ProductFeign;

@Service
public class OrderService {
	
	@Autowired
	OrderReppo  orderreppo;
	
	@Autowired
	OrderItemsReppo orderItemsReppo;
	
	@Autowired
	ProductFeign feign;
	
	private LocalDateTime currenDateTime=LocalDateTime.now();

	public String addorder(Ordermodel ordermodel) {
		
		ordermodel.setOrdernumber(getOrderNumber());
		ordermodel.setSubtotal(getsubtotal(ordermodel));
		ordermodel.setTotalamount( gettotalamount(ordermodel));
		OrderDto orderDto=new OrderDto(ordermodel.getOrdernumber(),ordermodel.getUserid(),ordermodel.getGst(),ordermodel.getDiscount(),ordermodel.getTotalamount(),ordermodel.getSubtotal(),ordermodel.getStatus());
		orderDto.setOrderDateTime(currenDateTime);
	    orderDto=orderreppo.save(orderDto);
	    
	    for (Orderitemmodel  orderitemmodel : ordermodel.getOrderitemsmodel()) {
	    	OrderitemsDto orderitemsDto=new OrderitemsDto();	    	
	    	orderitemsDto.setOrder(orderDto);
	    	orderitemsDto.setProductitemid(orderitemmodel.getProductitemid());
	    	orderitemsDto.setProducttotal(getproducttotal(orderitemmodel.getProductitemid(),orderitemmodel.getQuantity(),ordermodel.getDiscount()));
	    	orderitemsDto.setQuantity(orderitemmodel.getQuantity());
	    	orderItemsReppo.save(orderitemsDto);	
		}	 
		return "order saved";
	}

	private Double gettotalamount(Ordermodel ordermodel) {
		double subTotal=ordermodel.getSubtotal();
		double totalAmt = subTotal +(subTotal*ordermodel.getGst()/100)-(subTotal*ordermodel.getDiscount()/100);
		return totalAmt;
	}

	private Double getsubtotal(Ordermodel ordermodel) {
		 double subtotal=0;
		 
		 for (Orderitemmodel  orderitems : ordermodel.getOrderitemsmodel()) {
			subtotal=subtotal+getproducttotal(orderitems.getProductitemid(), orderitems.getQuantity(),ordermodel.getDiscount());
		}
		return subtotal;
	}

	private Double getproducttotal(int productitemid, int quantity, float f) {
 
		
		  ProductItemModel productItemModel=feign.getproductitembyid(productitemid);
		  double product=0;
		  
		  product= productItemModel.getProductprice()*quantity;
		  double a=  product-(product * ( f / 100));
		 
//		  DecimalFormat df = new DecimalFormat("#.00"); 
//		  String ad=  df.format("%.2f",a);
		  
		  String formattedValue = String.format("%.2f", a); 
		  return Double.parseDouble(formattedValue);
	}

	
	private int getOrderNumber() {	
		Integer orderNum = orderreppo.findMaxOrdernumber();
		if (null == orderNum) {
			return 1;
		}
		return orderNum + 1;
	}
}
