package com.example.security.Configration;

import com.example.security.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/*").permitAll()
                        .requestMatchers(/*HttpMethod.POST,*/"/users/add").permitAll()
                        .requestMatchers("/update/").authenticated()
                        .requestMatchers("/users/update-self/").authenticated()
                        .requestMatchers("/users/**").hasRole("ADMIN")

                        .requestMatchers("/login/*").authenticated()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .rememberMe()
                .key("uniqueAndSecret")
                .tokenValiditySeconds(1209600)
                .rememberMeParameter("rememberMe")
                .userDetailsService(CustomUserDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance(); //for tests
    }
}

