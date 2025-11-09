package com.example.SkillBridge.service;

import com.example.SkillBridge.dto.request.AssessmentRequestDTO;
import com.example.SkillBridge.dto.response.AssessmentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SkillAssessmentService {

    AssessmentResponseDTO createAssessment(Long userId, AssessmentRequestDTO assessmentRequestDTO);
    List<AssessmentResponseDTO> getAssessmentsByUser(Long userId);
    Page<AssessmentResponseDTO> getAssessmentsByUser(Long userId, Pageable pageable);
    AssessmentResponseDTO updateAssessment(Long assessmentId, AssessmentRequestDTO assessmentRequestDTO);
    void deleteAssessment(Long assessmentId);
}