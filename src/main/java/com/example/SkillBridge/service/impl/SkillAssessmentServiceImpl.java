package com.example.SkillBridge.service.impl;



import com.example.SkillBridge.dto.request.AssessmentRequestDTO;
import com.example.SkillBridge.dto.response.AssessmentResponseDTO;
import com.example.SkillBridge.model.SkillAssessment;
import com.example.SkillBridge.model.User;
import com.example.SkillBridge.repository.SkillAssessmentRepository;
import com.example.SkillBridge.repository.UserRepository;
import com.example.SkillBridge.service.SkillAssessmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillAssessmentServiceImpl implements SkillAssessmentService {

    private final SkillAssessmentRepository skillAssessmentRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public AssessmentResponseDTO createAssessment(Long userId, AssessmentRequestDTO assessmentRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        SkillAssessment assessment = modelMapper.map(assessmentRequestDTO, SkillAssessment.class);
        assessment.setUser(user);

        SkillAssessment savedAssessment = skillAssessmentRepository.save(assessment);
        return convertToDTO(savedAssessment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssessmentResponseDTO> getAssessmentsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        return skillAssessmentRepository.findByUser(user).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssessmentResponseDTO> getAssessmentsByUser(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        return skillAssessmentRepository.findByUser(user, pageable)
                .map(this::convertToDTO);
    }

    @Override
    @Transactional
    public AssessmentResponseDTO updateAssessment(Long assessmentId, AssessmentRequestDTO assessmentRequestDTO) {
        SkillAssessment assessment = skillAssessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new RuntimeException("Assessment not found with id: " + assessmentId));

        modelMapper.map(assessmentRequestDTO, assessment);
        SkillAssessment updatedAssessment = skillAssessmentRepository.save(assessment);
        return convertToDTO(updatedAssessment);
    }

    @Override
    @Transactional
    public void deleteAssessment(Long assessmentId) {
        SkillAssessment assessment = skillAssessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new RuntimeException("Assessment not found with id: " + assessmentId));
        skillAssessmentRepository.delete(assessment);
    }

    private AssessmentResponseDTO convertToDTO(SkillAssessment assessment) {
        AssessmentResponseDTO dto = modelMapper.map(assessment, AssessmentResponseDTO.class);
        dto.setUserId(assessment.getUser().getId());
        dto.setUserName(assessment.getUser().getName());
        return dto;
    }
}