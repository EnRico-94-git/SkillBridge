package com.example.SkillBridge.messaging.producers;

import com.example.SkillBridge.config.RabbitMQConfig;
import com.example.SkillBridge.dto.response.CareerAdviceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendCareerAdviceNotification(Long userId, CareerAdviceResponseDTO advice) {
        NotificationMessage message = new NotificationMessage(userId, "CAREER_ADVICE", advice);
        rabbitTemplate.convertAndSend(RabbitMQConfig.NOTIFICATION_QUEUE, message);
    }

    public void sendWelcomeNotification(Long userId) {
        NotificationMessage message = new NotificationMessage(userId, "WELCOME", null);
        rabbitTemplate.convertAndSend(RabbitMQConfig.NOTIFICATION_QUEUE, message);
    }

    public void sendSkillAssessmentNotification(Long userId) {
        NotificationMessage message = new NotificationMessage(userId, "SKILL_ASSESSMENT", null);
        rabbitTemplate.convertAndSend(RabbitMQConfig.NOTIFICATION_QUEUE, message);
    }

    public record NotificationMessage(Long userId, String type, Object data) {}
}