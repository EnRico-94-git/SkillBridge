package com.example.SkillBridge.ai;


import com.example.SkillBridge.model.SkillAssessment;
import com.example.SkillBridge.model.SkillCategory;
import com.example.SkillBridge.repository.SkillAssessmentRepository;
import com.example.SkillBridge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SkillGapAnalyzer {

    private final SkillAssessmentRepository skillAssessmentRepository;
    private final UserRepository userRepository;

    public Map<String, Object> analyzeSkillGaps(Long userId) {
        // Verifica se o usuário existe
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        // Busca todas as avaliações do usuário
        List<SkillAssessment> userAssessments = skillAssessmentRepository.findByUser(
                userRepository.findById(userId).get()
        );

        // Agrupa por categoria e calcula a média
        Map<SkillCategory, Double> averageByCategory = userAssessments.stream()
                .collect(Collectors.groupingBy(SkillAssessment::getCategory,
                        Collectors.averagingInt(SkillAssessment::getSkillLevel)));

        // Identifica categorias com média baixa (skill gap)
        double threshold = 3.0; // Nível médio mínimo desejado
        List<String> skillGaps = averageByCategory.entrySet().stream()
                .filter(entry -> entry.getValue() < threshold)
                .map(entry -> entry.getKey().name())
                .collect(Collectors.toList());

        // Recomendações baseadas nas lacunas
        List<String> recommendations = generateRecommendations(skillGaps);

        Map<String, Object> result = new HashMap<>();
        result.put("skillGaps", skillGaps);
        result.put("averageSkillLevels", averageByCategory);
        result.put("recommendations", recommendations);
        result.put("totalAssessments", userAssessments.size());
        result.put("analysisDate", new Date());

        return result;
    }

    private List<String> generateRecommendations(List<String> skillGaps) {
        List<String> recommendations = new ArrayList<>();
        for (String gap : skillGaps) {
            switch (gap) {
                case "TECHNICAL":
                    recommendations.add("Considere cursos de programação e ferramentas técnicas.");
                    recommendations.add("Pratique projetos práticos para fortalecer habilidades técnicas.");
                    break;
                case "SOFT_SKILLS":
                    recommendations.add("Pratique comunicação, trabalho em equipe e resolução de problemas.");
                    recommendations.add("Participe de workshops de desenvolvimento interpessoal.");
                    break;
                case "GREEN_SKILLS":
                    recommendations.add("Explore cursos sobre sustentabilidade e práticas verdes.");
                    recommendations.add("Aprenda sobre economia circular e energias renováveis.");
                    break;
                case "DIGITAL":
                    recommendations.add("Aprenda sobre transformação digital e ferramentas online.");
                    recommendations.add("Desenvolva competências em marketing digital e análise de dados.");
                    break;
                case "LEADERSHIP":
                    recommendations.add("Desenvolva habilidades de liderança e gestão de projetos.");
                    recommendations.add("Participe de programas de mentoria e liderança.");
                    break;
                case "SUSTAINABILITY":
                    recommendations.add("Estude sobre desenvolvimento sustentável e ESG.");
                    recommendations.add("Aprenda sobre relatórios de sustentabilidade e métricas ESG.");
                    break;
                default:
                    recommendations.add("Busque cursos e práticas na área de " + gap + ".");
            }
        }
        return recommendations;
    }
}