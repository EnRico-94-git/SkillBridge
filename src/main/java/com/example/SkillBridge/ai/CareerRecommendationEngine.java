package com.example.SkillBridge.ai;


import com.example.SkillBridge.dto.response.CareerAdviceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CareerRecommendationEngine {

    private final ChatClient chatClient;

    public CareerAdviceResponseDTO generateCareerAdvice(String skills, String interests, String goals) {
        String prompt = String.format(
                "Com base nas habilidades: %s, interesses: %s e objetivos: %s, " +
                        "gere recomendações de carreira na economia verde e digital. " +
                        "Inclua: papel recomendado, pontuação de confiança (0-1), recomendação detalhada, " +
                        "caminho de aprendizado sugerido, tendências de mercado e meses estimados de treinamento. " +
                        "Formate a resposta em JSON com campos: recommendedRole, confidenceScore, aiRecommendation, " +
                        "suggestedLearningPath, marketTrends, estimatedTrainingMonths.",
                skills, interests, goals
        );

        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        // Em uma implementação real, você parsearia o JSON da resposta
        // Aqui, vamos simular uma resposta
        return simulateCareerAdviceResponse(skills, interests, goals);
    }

    private CareerAdviceResponseDTO simulateCareerAdviceResponse(String skills, String interests, String goals) {
        CareerAdviceResponseDTO advice = new CareerAdviceResponseDTO();
        advice.setRecommendedRole("Especialista em Sustentabilidade Digital");
        advice.setConfidenceScore(0.85);
        advice.setAiRecommendation("Com suas habilidades em " + skills + " e interesses em " + interests +
                ", recomendamos uma carreira em sustentabilidade digital. Este campo está em crescimento " +
                "e combina tecnologia com práticas sustentáveis.");
        advice.setSuggestedLearningPath("1. Curso de Green IT\n2. Certificação em Gestão Sustentável\n3. Projetos práticos em eficiência energética");
        advice.setMarketTrends("A demanda por profissionais de TI sustentável cresceu 45% nos últimos 2 anos");
        advice.setEstimatedTrainingMonths(6);
        return advice;
    }
}