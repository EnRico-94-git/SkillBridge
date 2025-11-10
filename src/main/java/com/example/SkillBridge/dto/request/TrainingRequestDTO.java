package com.example.SkillBridge.dto.request;

import com.example.SkillBridge.model.TrainingLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingRequestDTO {

    @NotBlank(message = "{validation.training.title.required}")
    private String title;

    private String description;

    @NotNull(message = "{validation.training.level.required}")
    private TrainingLevel level;

    private String category;

    private Integer durationHours;

    private Boolean isActive = true;
}