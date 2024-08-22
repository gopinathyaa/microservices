package com.yaacreations.Product.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yaacreations.Product.Entity.ProductDto;
import com.yaacreations.Product.Entity.ProductItemsDto;


@Repository
public interface ProductItemReppo extends JpaRepository<ProductItemsDto,Integer> {
	
	List<ProductItemsDto> findByProduct(ProductDto product);

}
