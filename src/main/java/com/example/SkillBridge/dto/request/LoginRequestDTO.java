package com.example.SkillBridge.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NotBlank(message = "{validation.user.email.required}")
    private String email;

    @NotBlank(message = "{validation.user.password.required}")
    private String password;
}