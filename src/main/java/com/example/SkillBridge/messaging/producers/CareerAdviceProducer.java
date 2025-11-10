package com.example.SkillBridge.messaging.producers;


import com.example.SkillBridge.config.RabbitMQConfig;
import com.example.SkillBridge.dto.response.CareerAdviceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CareerAdviceProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendCareerAdvice(Long userId, CareerAdviceResponseDTO advice) {
        CareerAdviceMessage message = new CareerAdviceMessage(userId, advice);
        rabbitTemplate.convertAndSend(RabbitMQConfig.CAREER_ADVICE_QUEUE, message);
    }

    public record CareerAdviceMessage(Long userId, CareerAdviceResponseDTO advice) {}
}