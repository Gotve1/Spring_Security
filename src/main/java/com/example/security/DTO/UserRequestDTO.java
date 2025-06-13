package com.example.security.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {

    @NotBlank(message = "Firstname is required")
    @Size(min = 6, max = 16)
    private String Firstname;

    @NotBlank(message = "Lastname is required")
    @Size(min = 6, max = 16)
    private String Lastname;

}
