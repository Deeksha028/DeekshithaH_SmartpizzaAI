package com.wipro.smartpizza.security;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
@Configuration
public class SecurityConfig {
 
    @Autowired
    private JwtFilter jwtFilter;
 
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
 
        http
        		.cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
 
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))
 
                .authorizeHttpRequests(auth -> auth
 
                        .requestMatchers(
                                "/api/users/register",
                                "/api/users/login",
                                "/api/users/welcome",
                                "/swagger-ui/**",
                                "/v3/api-docs/**")
                        .permitAll()
 
                        .requestMatchers(HttpMethod.GET,"/api/pizzas/**")
                        .hasAnyRole("CUSTOMER", "ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/pizzas/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/pizzas/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/pizzas/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST,"/api/orders/**")
                        .permitAll()
                        //.hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET,"/api/orders/**")
                        .hasAnyRole("ADMIN","CUSTOMER")
                        .requestMatchers("/api/cart/**")
                        .hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PUT,"/api/orders/cancel/**")
                        .hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.PUT,"/api/orders/*/status")
                        .hasRole("ADMIN").requestMatchers("/api/payments/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/delivery/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/delivery/**")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/delivery/eta/**")
                        .hasAnyRole("ADMIN", "CUSTOMER") 
                        .requestMatchers(HttpMethod.GET, "/api/delivery/**")
                        .hasAnyRole("ADMIN","CUSTOMER")
                        .requestMatchers("/api/recommendations/**")
                        .permitAll()
                        .anyRequest().authenticated())
                		.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);
 
        return http.build();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}