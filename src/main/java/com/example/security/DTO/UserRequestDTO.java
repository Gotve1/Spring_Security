package com.example.security.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotBlank(message = "Firstname is required")
    @Size(min = 6, max = 16)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 16)
    private String Password;

}
