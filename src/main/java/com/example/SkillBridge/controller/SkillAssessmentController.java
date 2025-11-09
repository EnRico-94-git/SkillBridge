package com.example.SkillBridge.controller;

import com.example.SkillBridge.dto.request.AssessmentRequestDTO;
import com.example.SkillBridge.dto.response.ApiResponseDTO;
import com.example.SkillBridge.dto.response.AssessmentResponseDTO;
import com.example.SkillBridge.service.SkillAssessmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
@RequiredArgsConstructor
@Tag(name = "Skill Assessments", description = "Skill assessment management")
public class SkillAssessmentController {

    private final SkillAssessmentService skillAssessmentService;

    @PostMapping("/user/{userId}")
    @Operation(summary = "Create a new skill assessment")
    public ResponseEntity<ApiResponseDTO<AssessmentResponseDTO>> createAssessment(
            @PathVariable Long userId, @Valid @RequestBody AssessmentRequestDTO assessmentRequestDTO) {
        AssessmentResponseDTO assessment = skillAssessmentService.createAssessment(userId, assessmentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDTO.success(assessment, "Assessment created successfully"));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all assessments for a user")
    public ResponseEntity<ApiResponseDTO<List<AssessmentResponseDTO>>> getAssessmentsByUser(@PathVariable Long userId) {
        List<AssessmentResponseDTO> assessments = skillAssessmentService.getAssessmentsByUser(userId);
        return ResponseEntity.ok(ApiResponseDTO.success(assessments, "Assessments retrieved successfully"));
    }

    @GetMapping("/user/{userId}/page")
    @Operation(summary = "Get paginated assessments for a user")
    public ResponseEntity<ApiResponseDTO<Page<AssessmentResponseDTO>>> getAssessmentsByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "assessmentDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<AssessmentResponseDTO> assessments = skillAssessmentService.getAssessmentsByUser(userId, pageable);
        return ResponseEntity.ok(ApiResponseDTO.success(assessments, "Assessments retrieved successfully"));
    }

    @PutMapping("/{assessmentId}")
    @Operation(summary = "Update an assessment")
    public ResponseEntity<ApiResponseDTO<AssessmentResponseDTO>> updateAssessment(
            @PathVariable Long assessmentId, @Valid @RequestBody AssessmentRequestDTO assessmentRequestDTO) {
        AssessmentResponseDTO assessment = skillAssessmentService.updateAssessment(assessmentId, assessmentRequestDTO);
        return ResponseEntity.ok(ApiResponseDTO.success(assessment, "Assessment updated successfully"));
    }

    @DeleteMapping("/{assessmentId}")
    @Operation(summary = "Delete an assessment")
    public ResponseEntity<ApiResponseDTO<Void>> deleteAssessment(@PathVariable Long assessmentId) {
        skillAssessmentService.deleteAssessment(assessmentId);
        return ResponseEntity.ok(ApiResponseDTO.success(null, "Assessment deleted successfully"));
    }
}