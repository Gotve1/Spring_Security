package com.example.security.Controller.User;

import com.example.security.DTO.*;
import com.example.security.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users/all/")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long ID) {
        return ResponseEntity.ok(userService.findUserById(ID));
    }

    @GetMapping("/users/add/")
    @PostMapping
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody @Valid UserRequestDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long ID, @RequestBody @Valid UserRequestDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(ID, userDTO));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long ID) {
        return ResponseEntity.ok(userService.deleteUser(ID));
    }

}
