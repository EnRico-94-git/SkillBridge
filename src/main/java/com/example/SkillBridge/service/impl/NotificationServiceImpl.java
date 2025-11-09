package com.example.SkillBridge.service.impl;

import com.example.SkillBridge.dto.response.CareerAdviceResponseDTO;
import com.example.SkillBridge.messaging.producers.NotificationProducer;
import com.example.SkillBridge.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationProducer notificationProducer;

    @Override
    public void sendCareerAdviceNotification(Long userId, CareerAdviceResponseDTO advice) {
        // Envia notificação assíncrona
        notificationProducer.sendCareerAdviceNotification(userId, advice);
    }

    @Override
    public void sendWelcomeNotification(Long userId) {
        notificationProducer.sendWelcomeNotification(userId);
    }

    @Override
    public void sendSkillAssessmentNotification(Long userId) {
        notificationProducer.sendSkillAssessmentNotification(userId);
    }
}