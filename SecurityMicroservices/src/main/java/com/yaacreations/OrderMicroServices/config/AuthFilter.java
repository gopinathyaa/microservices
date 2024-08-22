package com.yaacreations.OrderMicroServices.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yaacreations.OrderMicroServices.service.JwtService;
import com.yaacreations.OrderMicroServices.service.UsersService;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter extends OncePerRequestFilter {
	@Autowired
	private JwtService jwtutils;
	@Autowired
	private UsersService ourUserService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwtToken;
		String userEmail = null;
		try {
			if (authHeader == null || authHeader.isBlank()) {
				filterChain.doFilter(request, response);
				return;
			}
			jwtToken = authHeader.substring(7);
			userEmail = jwtutils.extractUsername(jwtToken);
			if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = ourUserService.loadUserByUsername(userEmail);
				if (jwtutils.verifyToken(jwtToken, userDetails)) {
					SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
					UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					token.setDetails(token);
					securityContext.setAuthentication(token);
					SecurityContextHolder.setContext(securityContext);
					System.out.println(SecurityContextHolder.getContext() +"security");
				}
			}
			filterChain.doFilter(request, response);
		} catch (Exception ex) {
			System.out.println(ex);
			throw new RuntimeException("Unauthorized access");
		}
	}
}