package com.example.SkillBridge.controller;


import com.example.SkillBridge.dto.request.CareerAdviceRequestDTO;
import com.example.SkillBridge.dto.response.ApiResponseDTO;
import com.example.SkillBridge.dto.response.CareerAdviceResponseDTO;
import com.example.SkillBridge.service.CareerAdvisorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/career")
@RequiredArgsConstructor
@Tag(name = "Career Advisor", description = "AI-powered career recommendations")
public class CareerAdvisorController {

    private final CareerAdvisorService careerAdvisorService;

    @PostMapping("/advice/user/{userId}")
    @Operation(summary = "Generate career advice for a user")
    public ResponseEntity<ApiResponseDTO<CareerAdviceResponseDTO>> generateCareerAdvice(
            @PathVariable Long userId, @RequestBody CareerAdviceRequestDTO request) {
        CareerAdviceResponseDTO advice = careerAdvisorService.generateCareerAdvice(userId, request);
        return ResponseEntity.ok(ApiResponseDTO.success(advice, "Career advice generated successfully"));
    }

    @PostMapping("/advice")
    @Operation(summary = "Generate career advice based on skills and interests")
    public ResponseEntity<ApiResponseDTO<CareerAdviceResponseDTO>> generateCareerAdvice(
            @RequestParam String skills,
            @RequestParam String interests,
            @RequestParam(required = false) String goals) {
        CareerAdviceResponseDTO advice = careerAdvisorService.generateCareerAdvice(skills, interests, goals);
        return ResponseEntity.ok(ApiResponseDTO.success(advice, "Career advice generated successfully"));
    }
}