package com.yaacreations.OrderMicroServices.service;

import java.security.Key;
import java.util.HashMap;
import java.util.function.Function;

import javax.crypto.SecretKey;

import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.yaacreations.OrderMicroServices.entity.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
//	   @Value("${jwt.secret.key}")
//	    private String secretKey;
	
	private static final String SECRET_KEY = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
	public String generateToken(Users users) {
		Claims claims = Jwts.claims().setIssuer(users.getEmail());
		claims.put("user_id", users.getUserid());
		claims.put("email", users.getEmail());
		claims.put("token_type", "access");
		return Jwts.builder().setClaims(claims).setSubject(users.getEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}
	
	public String generateRefreshToken(HashMap<String, Object> claims, Users users) {
		return Jwts.builder().setClaims(claims).setSubject(users.getEmail())
				.claim("user_id", users.getUserid())
				.claim("email", users.getEmail())
				.claim("type", "refresh")
			.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
	}
	
	private Key getSigningKey() {
		byte[] keybytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keybytes);
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		
		return claimsResolver.apply(claims);
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	 
	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}
	
	public boolean verifyToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
