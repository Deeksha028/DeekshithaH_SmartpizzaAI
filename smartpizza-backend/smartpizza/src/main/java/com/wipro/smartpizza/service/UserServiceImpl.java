package com.wipro.smartpizza.service;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wipro.smartpizza.dto.LoginRequestDTO;
import com.wipro.smartpizza.dto.RegisterRequestDTO;
import com.wipro.smartpizza.dto.UserResponseDTO;
import com.wipro.smartpizza.entity.Role;
import com.wipro.smartpizza.entity.User;
import com.wipro.smartpizza.exception.UserAlreadyExistsException;
import com.wipro.smartpizza.repository.UserRepository;
import com.wipro.smartpizza.security.JwtUtil;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
 
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
 
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
 
    @Override
    public UserResponseDTO register(RegisterRequestDTO request) {
 
        logger.info("Registering user: {}", request.getEmail());
 
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(
                    "Email already registered");
        }
 
        User user = new User();
 
        user.setName(request.getName());
        user.setEmail(request.getEmail());
 
        // BCrypt password encryption
        user.setPassword(
                passwordEncoder.encode(request.getPassword()));
 
        user.setRole(Role.CUSTOMER);
 
        User savedUser = userRepository.save(user);
 
        logger.info("User registered successfully");
 
        return new UserResponseDTO(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail());
    }
 
    @Override
    public String login(LoginRequestDTO dto) {
     
        logger.info("Login attempt for {}", dto.getEmail());
     
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
     
        if (!passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword())) {
     
            throw new RuntimeException(
                    "Invalid Credentials");
        }
        logger.info("ROLE = {}",user.getRole());
     
        //System.out.println("ROLE = " + user.getRole());
     
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getRole().name());
     
        //System.out.println("TOKEN = " + token);
        logger.info("TOKEN GENERATED FOR {}",user.getEmail());
     
        logger.info("JWT token generated");
     
        return token;
    }
}