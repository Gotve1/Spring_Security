package com.example.security.Service;

import com.example.security.Configration.Exceptions.UserAlreadyRegisteredException;
import com.example.security.Model.UserEntity;
import com.example.security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);

        UserEntity user = userEntityOptional.orElseThrow(() ->
                new UsernameNotFoundException("User with username: " + username + " not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name().replace("ROLE_", "")) // Remove ROLE_ prefix if present
                //.roles(String.valueOf(user.getRole()))
                .build();
    }
}
