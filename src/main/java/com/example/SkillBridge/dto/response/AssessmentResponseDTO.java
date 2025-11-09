package com.example.SkillBridge.dto.response;

import com.example.SkillBridge.model.SkillCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AssessmentResponseDTO {

    private Long id;
    private String skillName;
    private Integer skillLevel;
    private SkillCategory category;
    private LocalDateTime assessmentDate;
    private Long userId;
    private String userName;
}