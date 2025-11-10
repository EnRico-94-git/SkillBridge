package com.example.SkillBridge.repository;

import com.example.SkillBridge.model.SkillAssessment;
import com.example.SkillBridge.model.SkillCategory;

import java.util.List;
import java.util.Map;

public interface CustomAssessmentRepository {

    Map<SkillCategory, Double> calculateAverageSkillLevelByCategory(Long userId);
    List<SkillAssessment> findTopSkillsByUser(Long userId, int limit);
    List<SkillAssessment> findSkillsWithLowestLevel(Long userId, int limit);
}