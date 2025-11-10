package com.example.SkillBridge.messaging.consumers;

import com.example.SkillBridge.messaging.producers.NotificationProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationConsumer {

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumeNotification(NotificationProducer.NotificationMessage message) {
        log.info("Consuming notification for user ID: {}, type: {}", message.userId(), message.type());
        // Aqui você pode integrar com serviços de email, SMS, push notification, etc.
        switch (message.type()) {
            case "WELCOME" -> sendWelcomeEmail(message.userId());
            case "CAREER_ADVICE" -> sendCareerAdviceEmail(message.userId(), message.data());
            case "SKILL_ASSESSMENT" -> sendAssessmentNotification(message.userId());
            default -> log.warn("Unknown notification type: {}", message.type());
        }
    }

    private void sendWelcomeEmail(Long userId) {
        log.info("Sending welcome email to user ID: {}", userId);
        // Implementar envio de email
    }

    private void sendCareerAdviceEmail(Long userId, Object data) {
        log.info("Sending career advice email to user ID: {}", userId);
        // Implementar envio de email
    }

    private void sendAssessmentNotification(Long userId) {
        log.info("Sending skill assessment notification to user ID: {}", userId);
        // Implementar envio de notificação
    }
}