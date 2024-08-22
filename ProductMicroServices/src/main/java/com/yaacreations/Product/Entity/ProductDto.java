package com.yaacreations.Product.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "Product")
public class ProductDto {
	
	public ProductDto(String productname, String productdescription, int status) {
		 this.productname=productname;
		 this.productdescription=productdescription;
		 this.status=status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productid;
	
	@Column(unique = true,nullable = false)
	private String productname;
	
	private String productdescription;
	
	private int status;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<ProductItemsDto> productItemsDto;

}
