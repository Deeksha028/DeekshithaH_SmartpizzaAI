package com.wipro.smartpizza.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	
	 private final String SECRET_KEY =
		        "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
		 
		    private Key getSignKey() {
		        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		        return Keys.hmacShaKeyFor(keyBytes);
		    }
		 
		    public String generateToken(String email, String role) {
		        return Jwts.builder()
		                .setSubject(email)
		                .claim("role",role)
		                .setIssuedAt(new Date())
		                .setExpiration(new Date(
		                    System.currentTimeMillis() + 1000 * 60 * 60))
		                .signWith(getSignKey(), SignatureAlgorithm.HS256)
		                .compact();
		    }
		 
		    public String extractUsername(String token) {
		        return Jwts.parserBuilder()
		                .setSigningKey(getSignKey())
		                .build()
		                .parseClaimsJws(token)
		                .getBody()
		                .getSubject();
		    }
		    
		    public String extractRole(String token) {
		    	 
		        return Jwts.parserBuilder()
		                .setSigningKey(getSignKey())
		                .build()
		                .parseClaimsJws(token)
		                .getBody()
		                .get("role", String.class);
		    }
		    
		    public boolean validateToken(String token, String email) {
		    	return extractUsername(token).equals(email);
		}

}
