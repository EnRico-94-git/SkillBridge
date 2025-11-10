package com.example.SkillBridge.service;

import java.util.Map;

public interface AnalyticsService {

    Map<String, Double> getSkillSummary(Long userId);
    Map<String, Object> identifySkillGaps(Long userId);
    Map<String, Object> getCareerProgress(Long userId);
}