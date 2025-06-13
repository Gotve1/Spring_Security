package com.example.security.Configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF for testing with tools like Postman or curl
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/**").permitAll() // allow all requests to /users
                        .anyRequest().authenticated() // other requests require auth
                )
                .httpBasic(Customizer.withDefaults()); // enables HTTP Basic Auth (can remove if not needed)

        return http.build();
    }
}
