package com.example.security.Service;

import com.example.security.Configration.Exceptions.UserAlreadyRegisteredException;
import com.example.security.DTO.UserRequestDTO;
import com.example.security.DTO.UserResponseDTO;
import com.example.security.Model.Roles;
import com.example.security.Model.UserEntity;
import com.example.security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserResponseDTO toResponseDTO(UserEntity userEntity) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setID(userEntity.getID());
        dto.setUsername(userEntity.getUsername());
        dto.setPassword(userEntity.getPassword());
        dto.setRole(userEntity.getRole());
        dto.setLatitude(userEntity.getLatitude());
        dto.setLongitude(userEntity.getLongitude());
        dto.setDate(userEntity.getDate());
        return dto;
    }

    public UserResponseDTO createUser(UserRequestDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new UserAlreadyRegisteredException(dto.getUsername());
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
        userEntity.setRole(Roles.ROLE_USER);
        userEntity.setLatitude(dto.getLatitude());
        userEntity.setLongitude(dto.getLongitude());
        userEntity.setDate(LocalDate.now());
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
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));

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
        userEntity.setUsername(dto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));

        userEntity = userRepository.save(userEntity);
        return toResponseDTO(userEntity);
    }
}
