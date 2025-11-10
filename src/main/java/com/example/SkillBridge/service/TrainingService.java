package com.example.SkillBridge.service;

import com.example.SkillBridge.dto.request.TrainingRequestDTO;
import com.example.SkillBridge.dto.response.TrainingResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrainingService {

    TrainingResponseDTO createTraining(TrainingRequestDTO trainingRequestDTO);
    TrainingResponseDTO getTrainingById(Long id);
    Page<TrainingResponseDTO> getAllTrainings(Pageable pageable);
    TrainingResponseDTO updateTraining(Long id, TrainingRequestDTO trainingRequestDTO);
    void deleteTraining(Long id);
    List<String> getTrainingCategories();
    Page<TrainingResponseDTO> getTrainingsByLevel(String level, Pageable pageable);
}