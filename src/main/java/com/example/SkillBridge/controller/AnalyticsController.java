package com.example.SkillBridge.controller;

import com.example.SkillBridge.dto.response.ApiResponseDTO;
import com.example.SkillBridge.service.AnalyticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
@Tag(name = "Analytics", description = "Analytics endpoints")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/user/{userId}/skill-summary")
    @Operation(summary = "Get skill summary for a user")
    public ResponseEntity<ApiResponseDTO<Map<String, Double>>> getSkillSummary(@PathVariable Long userId) {
        Map<String, Double> summary = analyticsService.getSkillSummary(userId);
        return ResponseEntity.ok(ApiResponseDTO.success(summary, "Skill summary retrieved successfully"));
    }

    @GetMapping("/user/{userId}/skill-gaps")
    @Operation(summary = "Get skill gaps for a user")
    public ResponseEntity<ApiResponseDTO<Map<String, Object>>> getSkillGaps(@PathVariable Long userId) {
        Map<String, Object> skillGaps = analyticsService.identifySkillGaps(userId);
        return ResponseEntity.ok(ApiResponseDTO.success(skillGaps, "Skill gaps identified successfully"));
    }

    @GetMapping("/user/{userId}/career-progress")
    @Operation(summary = "Get career progress for a user")
    public ResponseEntity<ApiResponseDTO<Map<String, Object>>> getCareerProgress(@PathVariable Long userId) {
        Map<String, Object> progress = analyticsService.getCareerProgress(userId);
        return ResponseEntity.ok(ApiResponseDTO.success(progress, "Career progress retrieved successfully"));
    }
}