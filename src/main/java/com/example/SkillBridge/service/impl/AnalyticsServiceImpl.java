package com.example.SkillBridge.service.impl;

import com.example.SkillBridge.ai.SkillGapAnalyzer;
import com.example.SkillBridge.repository.SkillAssessmentRepository;
import com.example.SkillBridge.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final SkillAssessmentRepository skillAssessmentRepository;
    private final SkillGapAnalyzer skillGapAnalyzer;

    @Override
    @Transactional(readOnly = true)
    public Map<String, Double> getSkillSummary(Long userId) {
        // Implementação simplificada - você pode expandir isso
        Map<String, Double> summary = new HashMap<>();

        // Aqui você calcularia métricas reais baseadas nos dados
        summary.put("technicalSkills", 3.5);
        summary.put("softSkills", 4.2);
        summary.put("greenSkills", 2.8);
        summary.put("overallScore", 3.5);

        return summary;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> identifySkillGaps(Long userId) {
        return skillGapAnalyzer.analyzeSkillGaps(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getCareerProgress(Long userId) {
        Map<String, Object> progress = new HashMap<>();

        // Métricas de progresso de carreira
        progress.put("skillsAssessed", 15);
        progress.put("trainingsCompleted", 3);
        progress.put("careerPathsExplored", 2);
        progress.put("readinessLevel", "Intermediate");
        progress.put("nextMilestone", "Complete 2 more trainings");

        return progress;
    }
}