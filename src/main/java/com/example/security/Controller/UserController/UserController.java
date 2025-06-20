package com.example.security.Controller.UserController;

import com.example.security.DTO.*;
import com.example.security.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/byId/{ID}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long ID) {
        return ResponseEntity.ok(userService.findUserById(ID));
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody @Valid UserRequestDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PutMapping("/update/{ID}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long ID, @RequestBody @Valid UserRequestDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(ID, userDTO));
    }

    @DeleteMapping("/byId/{ID}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long ID) {
        return ResponseEntity.ok(userService.deleteUser(ID));
    }

    @PutMapping("/update-self/")
    public ResponseEntity<UserResponseDTO> updateSelf(@RequestBody @Valid UserRequestDTO userDTO, java.security.Principal principal) {
        String username = principal.getName();
        return ResponseEntity.ok(userService.updateUserByUsername(username, userDTO));
    }
}
