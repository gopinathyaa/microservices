package com.yaacreations.OrderMicroServices.Reppo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yaacreations.OrderMicroServices.Entity.OrderitemsDto;

@Repository
public interface OrderItemsReppo extends JpaRepository<OrderitemsDto ,Integer> {

}
