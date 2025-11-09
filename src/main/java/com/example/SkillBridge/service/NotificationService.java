package com.example.SkillBridge.service;

import com.example.SkillBridge.dto.response.CareerAdviceResponseDTO;

public interface NotificationService {

    void sendCareerAdviceNotification(Long userId, CareerAdviceResponseDTO advice);
    void sendWelcomeNotification(Long userId);
    void sendSkillAssessmentNotification(Long userId);
}