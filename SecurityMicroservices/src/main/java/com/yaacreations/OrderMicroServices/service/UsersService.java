package com.yaacreations.OrderMicroServices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yaacreations.OrderMicroServices.entity.Users;
import com.yaacreations.OrderMicroServices.repository.UserRepository;

@Service
public class UsersService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=userRepository.findByEmail(username);
		 if (null != user) {
		        var userBuilder = user;
		        return User.builder()
		        		.username(userBuilder.getEmail())
		        		.password(userBuilder.getPassword())
//		        		.authorities(userBuilder.getUserType())  //If needed
		        		.build();
		      }else {
				throw new UsernameNotFoundException("User is not found");
			}	
	}
	

}
