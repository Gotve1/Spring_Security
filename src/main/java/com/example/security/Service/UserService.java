package com.example.security.Service;

import com.example.security.DTO.UserRequestDTO;
import com.example.security.DTO.UserResponseDTO;
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
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(dto.getFirstname());
        userEntity.setLastName(dto.getLastname());
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
        userEntity.setFirstName(dto.getFirstname());
        userEntity.setLastName(dto.getLastname());

        return toResponseDTO(userRepository.save(userEntity));
    }

    public UserResponseDTO deleteUser(Long ID) {
        UserEntity userEntity = getUserEntity(ID);
        userRepository.delete(userEntity);
        return toResponseDTO(userEntity);
    }

    private UserResponseDTO toResponseDTO(UserEntity userEntity) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setID(userEntity.getID());
        dto.setFirstname(userEntity.getFirstName());
        dto.setLastname(userEntity.getLastName());
        return dto;
    }

}
