package com.wipro.smartpizza.service;

import com.wipro.smartpizza.dto.LoginRequestDTO;
import com.wipro.smartpizza.dto.LoginResponseDTO;
import com.wipro.smartpizza.dto.RegisterRequestDTO;
import com.wipro.smartpizza.dto.UserResponseDTO;

public interface UserService {
	
	UserResponseDTO register(RegisterRequestDTO request);
	
	LoginResponseDTO login(LoginRequestDTO dto);
	
	String resetPassword(
		    String email,
		    String otp,
		    String newPassword
		);
	}


