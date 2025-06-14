package com.example.security.Configration.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String username) {
        super("user with username: " + username + " already registered");
    }
}
