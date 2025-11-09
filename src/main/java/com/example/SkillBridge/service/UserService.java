package com.example.SkillBridge.service;

import com.example.SkillBridge.dto.request.UserRequestDTO;
import com.example.SkillBridge.dto.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO getUserByEmail(String email);
    Page<UserResponseDTO> getAllUsers(Pageable pageable);
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    void deleteUser(Long id);
    boolean existsByEmail(String email);
}