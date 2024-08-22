package com.yaacreations.OrderMicroServices.Entity;

 

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orderitems")
public class OrderitemsDto {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderitemsid;
	
	private int productitemid;
	
	private int quantity;
	
	private Double producttotal;
	
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "orderid" ,nullable = false)
    private OrderDto order;
	
}
