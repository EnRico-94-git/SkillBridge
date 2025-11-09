package com.example.SkillBridge.service;

import com.example.SkillBridge.dto.request.CareerAdviceRequestDTO;
import com.example.SkillBridge.dto.response.CareerAdviceResponseDTO;

public interface CareerAdvisorService {

    CareerAdviceResponseDTO generateCareerAdvice(Long userId, CareerAdviceRequestDTO request);
    CareerAdviceResponseDTO generateCareerAdvice(String skills, String interests, String goals);
}