package com.example.SkillBridge.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponseDTO {

    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String role;

    public JwtResponseDTO(String token, Long id, String email, String role) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.role = role;
    }
}