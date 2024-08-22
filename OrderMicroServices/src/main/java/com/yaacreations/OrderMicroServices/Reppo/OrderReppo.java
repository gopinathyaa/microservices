package com.yaacreations.OrderMicroServices.Reppo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yaacreations.OrderMicroServices.Entity.OrderDto;

@Repository
public interface OrderReppo extends JpaRepository<OrderDto, Integer> {

	@Query("SELECT MAX(o.ordernumber) FROM OrderDto o")
    Integer findMaxOrdernumber();
	
}
 