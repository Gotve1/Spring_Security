package com.example.security.Service;

import com.example.security.DTO.UserRequestDTO;
import com.example.security.DTO.UserResponseDTO;
import com.example.security.Model.Roles;
import com.example.security.Model.UserEntity;
import com.example.security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new com.example.security.Configration.Exceptions.UserAlreadyRegisteredException(dto.getUsername());
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(dto.getPassword());
        userEntity.setRole(Roles.ROLE_USER); // Default role set to USER
        userEntity = userRepository.save(userEntity);

        return toResponseDTO(userEntity);
    }

    public UserResponseDTO findUserById(Long ID) {
        UserEntity userEntity = userRepository.findById(ID)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return toResponseDTO(userEntity);
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public UserEntity getUserEntity(Long ID) {
        return userRepository.findById(ID)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserResponseDTO updateUser(Long ID, UserRequestDTO dto) {
        UserEntity userEntity = getUserEntity(ID);
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(dto.getPassword());

        return toResponseDTO(userRepository.save(userEntity));
    }

    public UserResponseDTO deleteUser(Long ID) {
        UserEntity userEntity = getUserEntity(ID);
        userRepository.delete(userEntity);
        return toResponseDTO(userEntity);
    }

    public UserResponseDTO updateUserByUsername(String username, UserRequestDTO dto) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Only allow updating certain fields (e.g., password)
        userEntity.setPassword(dto.getPassword());
        userEntity.setUsername(dto.getUsername());
        // save the updated user entity
        userEntity = userRepository.save(userEntity);
        return toResponseDTO(userEntity);
    }

    private UserResponseDTO toResponseDTO(UserEntity userEntity) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setID(userEntity.getID());
        dto.setUsername(userEntity.getUsername());
        dto.setPassword(userEntity.getPassword());
        dto.setRole(userEntity.getRole());
        return dto;
    }
}
