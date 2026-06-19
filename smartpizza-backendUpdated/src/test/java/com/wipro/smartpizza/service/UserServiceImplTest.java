package com.wipro.smartpizza.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wipro.smartpizza.dto.LoginRequestDTO;
import com.wipro.smartpizza.dto.LoginResponseDTO;
import com.wipro.smartpizza.dto.RegisterRequestDTO;
import com.wipro.smartpizza.dto.UserResponseDTO;
import com.wipro.smartpizza.entity.Role;
import com.wipro.smartpizza.entity.User;
import com.wipro.smartpizza.repository.UserRepository;
import com.wipro.smartpizza.security.JwtUtil;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
 
    @Mock
    private UserRepository userRepository;
 
    @Mock
    private PasswordEncoder passwordEncoder;
 
    @Mock
    private JwtUtil jwtUtil;
 
    @InjectMocks
    private UserServiceImpl userService;
 
    @Test
    void testRegisterSuccess() {
 
        RegisterRequestDTO request = new RegisterRequestDTO();
        request.setName("John");
        request.setEmail("john@gmail.com");
        request.setPassword("12345");
 
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode("12345")).thenReturn("encoded123");
 
        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("John");
        savedUser.setEmail("john@gmail.com");
 
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
 
        UserResponseDTO response = userService.register(request);
 
        assertEquals("John", response.getName());
        assertEquals("john@gmail.com", response.getEmail());
    }
 
    @Test
    void testLoginSuccess() {
 
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setEmail("john@gmail.com");
        dto.setPassword("12345");
 
        User user = new User();
        user.setName("John");
        user.setEmail("john@gmail.com");
        user.setPassword("encoded123");
        user.setRole(Role.ADMIN);
 
        when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("12345", "encoded123")).thenReturn(true);
        when(jwtUtil.generateToken(user.getEmail(), user.getRole().name()))
                .thenReturn("jwt-token");
 
        LoginResponseDTO response = userService.login(dto);
 
        assertEquals("jwt-token", response.getToken());
        assertEquals("John", response.getName());
    }
}
