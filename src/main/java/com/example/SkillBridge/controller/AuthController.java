package com.example.SkillBridge.controller;

import com.example.SkillBridge.dto.request.LoginRequestDTO;
import com.example.SkillBridge.dto.request.UserRequestDTO;
import com.example.SkillBridge.dto.response.ApiResponseDTO;
import com.example.SkillBridge.dto.response.JwtResponseDTO;
import com.example.SkillBridge.security.JwtUtil;
import com.example.SkillBridge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication endpoints")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @PostMapping("/login")
    @Operation(summary = "User login")
    public ResponseEntity<ApiResponseDTO<JwtResponseDTO>> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        String jwt = jwtUtil.generateToken(authentication);

        JwtResponseDTO response = new JwtResponseDTO(jwt, 1L, loginRequest.getEmail(), "USER");
        return ResponseEntity.ok(ApiResponseDTO.success(response, "Login successful"));
    }

    @PostMapping("/register")
    @Operation(summary = "User registration")
    public ResponseEntity<ApiResponseDTO<JwtResponseDTO>> register(@Valid @RequestBody UserRequestDTO userRequest) {
        var user = userService.createUser(userRequest);
        String jwt = jwtUtil.generateToken(user.getEmail());

        JwtResponseDTO response = new JwtResponseDTO(jwt, user.getId(), user.getEmail(), user.getRole());
        return ResponseEntity.ok(ApiResponseDTO.success(response, "Registration successful"));
    }
}