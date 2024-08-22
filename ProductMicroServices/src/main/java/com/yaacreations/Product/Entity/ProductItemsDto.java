package com.yaacreations.Product.Entity;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "Productitems")
public class ProductItemsDto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productitemid;
	
	private String producttype;
	
	private Double productprice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productid",nullable = false)
	@JsonBackReference
	private   ProductDto  product;
	

}
