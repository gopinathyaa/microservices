package com.yaacreations.OrderMicroServices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yaacreations.OrderMicroServices.entity.Users;
import com.yaacreations.OrderMicroServices.model.Signin;
import com.yaacreations.OrderMicroServices.service.AuthService;
 
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @PostMapping("/register")
    public String addNewUser(@RequestBody Users user) {
        return service.saveUser(user);
    }
    
    @PostMapping("/token")
    public String getToken(@RequestBody Signin user) {
    	System.out.println(user);
        try {
        	Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
			if (authenticate.isAuthenticated()) {
			    return service.generateToken(user.getUsername());
			} else {
				return "Bad credentials";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return "Bad credentials";
		}
    }
    @GetMapping("/validate")
    public Boolean validateToken(@RequestParam("token") String token) {
        return service.validateToken(token);
    }
}

 









