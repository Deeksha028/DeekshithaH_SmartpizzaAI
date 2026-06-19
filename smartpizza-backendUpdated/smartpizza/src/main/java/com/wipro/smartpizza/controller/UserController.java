package com.wipro.smartpizza.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.smartpizza.dto.LoginRequestDTO;
import com.wipro.smartpizza.dto.LoginResponseDTO;
import com.wipro.smartpizza.dto.RegisterRequestDTO;
import com.wipro.smartpizza.dto.UserResponseDTO;
import com.wipro.smartpizza.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
 
    private final UserService userService;
 
    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody RegisterRequestDTO request){
    	return userService.register(request);
 
    }
    
    @PostMapping("/login")
    public ResponseEntity <LoginResponseDTO> login(@RequestBody LoginRequestDTO dto){
    	
    	logger.info("LOGIN API HIT");
    	String token = userService.login(dto);
    	
    	return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    
    @GetMapping("/welcome")
    public String welcome() {
    	return "welcome to Smart Pizza";
    }
 
}
