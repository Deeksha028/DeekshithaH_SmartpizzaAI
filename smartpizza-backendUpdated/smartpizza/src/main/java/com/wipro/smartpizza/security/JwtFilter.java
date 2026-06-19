package com.wipro.smartpizza.security;
 
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@Component
public class JwtFilter extends OncePerRequestFilter {
 
    @Autowired
    private JwtUtil jwtUtil;
    
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
 
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)
            throws ServletException, IOException {
    	
    	String path = request.getRequestURI();
    	 
    	if (path.startsWith("/api/users/login")
    	        || path.startsWith("/api/users/register")
    	        || path.startsWith("/swagger-ui")
    	        || path.startsWith("/v3/api-docs")) {
    	 
    	    filterChain.doFilter(request, response);
    	    return;
    	}
     
        String authHeader = request.getHeader("Authorization");
     
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
     
        try {
     
            String token = authHeader.substring(7);
     
            String email = jwtUtil.extractUsername(token);
            
            String role = jwtUtil.extractRole(token);
            
            logger.info("EMAIL FROM TOKEN = {}",email);
            logger.info("ROLE FROM TOKEN = {}",role);
            logger.info("Creating Authentication with ROLE_{}",role);
     
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,null,List.of(new SimpleGrantedAuthority("ROLE_"+role)));
            logger.info("Authority = {}","ROLE_"+role);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            
            logger.info("EMAIL = {}",email);
            logger.info("ROLE = {}",role);
     
            SecurityContextHolder.getContext().setAuthentication(authentication);
     
        } catch (Exception e) {
     
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
     
            response.getWriter().write("Invalid JWT Token");
     
            return;
        }
     
        filterChain.doFilter(request, response);
    }
}