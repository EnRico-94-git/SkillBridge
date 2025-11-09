package com.example.SkillBridge.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank(message = "{validation.user.name.required}")
    @Size(min = 2, max = 100, message = "{validation.user.name.size}")
    private String name;

    @Email(message = "{validation.user.email.invalid}")
    @NotBlank(message = "{validation.user.email.required}")
    private String email;

    @NotBlank(message = "{validation.user.password.required}")
    @Size(min = 6, message = "{validation.user.password.size}")
    private String password;

    private String role;
}