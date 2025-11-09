package com.example.SkillBridge.service.impl;



import com.example.SkillBridge.ai.CareerRecommendationEngine;
import com.example.SkillBridge.dto.request.CareerAdviceRequestDTO;
import com.example.SkillBridge.dto.response.CareerAdviceResponseDTO;
import com.example.SkillBridge.messaging.producers.CareerAdviceProducer;
import com.example.SkillBridge.service.CareerAdvisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CareerAdvisorServiceImpl implements CareerAdvisorService {

    private final CareerRecommendationEngine careerRecommendationEngine;
    private final CareerAdviceProducer careerAdviceProducer;

    @Override
    @Transactional
    public CareerAdviceResponseDTO generateCareerAdvice(Long userId, CareerAdviceRequestDTO request) {
        CareerAdviceResponseDTO advice = careerRecommendationEngine.generateCareerAdvice(
                request.getCurrentSkills(),
                request.getInterests(),
                request.getCareerGoals()
        );

        // Envia a recomendação para a fila de mensagens assíncrona
        careerAdviceProducer.sendCareerAdvice(userId, advice);

        return advice;
    }

    @Override
    @Cacheable(value = "careerAdvice", key = "#skills + #interests + #goals")
    public CareerAdviceResponseDTO generateCareerAdvice(String skills, String interests, String goals) {
        return careerRecommendationEngine.generateCareerAdvice(skills, interests, goals);
    }
}