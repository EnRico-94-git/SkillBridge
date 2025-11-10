package com.example.SkillBridge.dto.response;

import com.example.SkillBridge.model.TrainingLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TrainingResponseDTO {

    private Long id;
    private String title;
    private String description;
    private TrainingLevel level;
    private String category;
    private Integer durationHours;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}