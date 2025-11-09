package com.example.SkillBridge.dto.request;

import com.example.SkillBridge.model.SkillCategory;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssessmentRequestDTO {

    @NotBlank(message = "{validation.assessment.skillname.required}")
    private String skillName;

    @Min(value = 1, message = "{validation.assessment.skilllevel.min}")
    @Max(value = 5, message = "{validation.assessment.skilllevel.max}")
    private Integer skillLevel;

    private SkillCategory category;
}