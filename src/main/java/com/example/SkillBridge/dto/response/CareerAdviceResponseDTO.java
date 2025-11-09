package com.example.SkillBridge.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CareerAdviceResponseDTO {

    private String recommendedRole;
    private Double confidenceScore;
    private String aiRecommendation;
    private String suggestedLearningPath;
    private String marketTrends;
    private Integer estimatedTrainingMonths;
}