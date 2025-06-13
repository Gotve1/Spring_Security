package com.example.security.Configration.Exceptions;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String username) {
        super("user with username: " + username + " already registered");
    }
}
