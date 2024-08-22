package com.yaacreations.OrderMicroServices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.yaacreations.OrderMicroServices.entity.Users;
 


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	
	Users findByEmail(String email);

}
