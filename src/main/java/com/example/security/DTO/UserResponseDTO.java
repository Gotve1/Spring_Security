package com.example.security.DTO;

import com.example.security.Model.Roles;
import lombok.Data;

@Data
public class UserResponseDTO {

    private Long ID;
    private String username;
    private String Password;
    private Roles Role;

}
