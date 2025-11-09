package com.example.SkillBridge.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseDTO<T> {

    private boolean success;
    private String message;
    private T data;
    private String timestamp;

    public ApiResponseDTO(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public static <T> ApiResponseDTO<T> success(T data, String message) {
        return new ApiResponseDTO<>(true, message, data);
    }

    public static <T> ApiResponseDTO<T> error(String message) {
        return new ApiResponseDTO<>(false, message, null);
    }
}