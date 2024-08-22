package com.yaacreations.Product.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yaacreations.Product.Entity.ProductDto;

@Repository
public interface ProductReppo extends JpaRepository<ProductDto,Integer> {

}
