package com.yaacreations.Gateway.config;

import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.yaacreations.Gateway.util.Jwtutil;
 
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator validator;
//    @Autowired
//    private RestTemplate template;
    @Autowired
    private Jwtutil jwtUtil;
    
    
    public AuthenticationFilter() {
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
        	System.out.println(exchange.getRequest());
            if (validator.isSecured.test(exchange.getRequest())) {
            	
            	
             
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                    
                    System.out.println("done"+  authHeader);
                }
                try {
//                    //REST call to AUTH service
//                    template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
                    if (!jwtUtil.validateToken(authHeader)) {
                        System.out.println("invalid access...!");
                        throw new RuntimeException("un authorized access to application");
					}
                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("un authorized access to application");
                }
            }
            
            return chain.filter(exchange);
        });
    }
    public static class Config {
    }
}






