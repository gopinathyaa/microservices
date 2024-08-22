package com.yaacreations.OrderMicroServices.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yaacreations.OrderMicroServices.entity.Users;
import com.yaacreations.OrderMicroServices.repository.UserRepository;
 

@Service
public class AuthService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private  UsersService userService;
    
    public String saveUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return "user added to the system";
    }
    public String generateToken(String username) {
    	String token = jwtService.generateToken(repository.findByEmail(username));
        return token;
    }
    
    public Boolean validateToken(String token) {
    	String userEmail = jwtService.extractUsername(token);
		UserDetails userDetails = userService.loadUserByUsername(userEmail );
        return jwtService.isTokenValid(token, userDetails);
    }
}




