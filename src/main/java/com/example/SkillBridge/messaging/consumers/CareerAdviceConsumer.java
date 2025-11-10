package com.example.SkillBridge.messaging.consumers;


import com.example.SkillBridge.messaging.producers.CareerAdviceProducer;
import com.example.SkillBridge.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CareerAdviceConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.career-advice}")
    public void consumeCareerAdvice(CareerAdviceProducer.CareerAdviceMessage message) {
        log.info("Consuming career advice for user ID: {}", message.userId());
        // Processar a mensagem, por exemplo, salvar no banco ou enviar email
        notificationService.sendCareerAdviceNotification(message.userId(), message.advice());
    }
}