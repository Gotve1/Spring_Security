package com.example.security.Configration;

import com.example.security.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Autowired
    UserDetailsService CustomUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/*").permitAll()
                        .requestMatchers("/login/*").authenticated()

                        .requestMatchers(HttpMethod.POST,"/users/add").permitAll()
                        .requestMatchers("/update/").authenticated()
                        .requestMatchers("/users", "/users/*").hasRole("ADMIN")
                        .requestMatchers("/users/update-self/").authenticated()
                        .anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults())

                .formLogin(form -> form
                    .loginPage("/login")
                    .permitAll()
                )

                .rememberMe(remember -> remember
                    .key("newSecretTokenRememberMeKey")
                    .tokenValiditySeconds(999999)
                    .rememberMeParameter("rememberMe")
                    .userDetailsService(CustomUserDetailsService)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance(); //for tests
    }
}

