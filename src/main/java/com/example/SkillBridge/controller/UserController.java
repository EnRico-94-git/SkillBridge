package com.example.SkillBridge.controller;

import com.example.SkillBridge.dto.response.ApiResponseDTO;
import com.example.SkillBridge.dto.request.UserRequestDTO;
import com.example.SkillBridge.dto.response.UserResponseDTO;
import com.example.SkillBridge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "User management endpoints")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a new user")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDTO.success(userResponseDTO, "User created successfully"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> getUserById(@PathVariable Long id) {
        UserResponseDTO userResponseDTO = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponseDTO.success(userResponseDTO, "User retrieved successfully"));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get user by email")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> getUserByEmail(@PathVariable String email) {
        UserResponseDTO userResponseDTO = userService.getUserByEmail(email);
        return ResponseEntity.ok(ApiResponseDTO.success(userResponseDTO, "User retrieved successfully"));
    }

    @GetMapping
    @Operation(summary = "Get all users with pagination")
    public ResponseEntity<ApiResponseDTO<Page<UserResponseDTO>>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<UserResponseDTO> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(ApiResponseDTO.success(users, "Users retrieved successfully"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user")
    public ResponseEntity<ApiResponseDTO<UserResponseDTO>> updateUser(
            @PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.updateUser(id, userRequestDTO);
        return ResponseEntity.ok(ApiResponseDTO.success(userResponseDTO, "User updated successfully"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public ResponseEntity<ApiResponseDTO<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponseDTO.success(null, "User deleted successfully"));
    }
}
