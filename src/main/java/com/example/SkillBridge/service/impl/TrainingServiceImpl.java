package com.example.SkillBridge.service.impl;

import com.example.SkillBridge.dto.request.TrainingRequestDTO;
import com.example.SkillBridge.dto.response.TrainingResponseDTO;
import com.example.SkillBridge.model.Training;
import com.example.SkillBridge.model.TrainingLevel;
import com.example.SkillBridge.repository.TrainingRepository;
import com.example.SkillBridge.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public TrainingResponseDTO createTraining(TrainingRequestDTO trainingRequestDTO) {
        Training training = modelMapper.map(trainingRequestDTO, Training.class);
        Training savedTraining = trainingRepository.save(training);
        return modelMapper.map(savedTraining, TrainingResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public TrainingResponseDTO getTrainingById(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found with id: " + id));
        return modelMapper.map(training, TrainingResponseDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrainingResponseDTO> getAllTrainings(Pageable pageable) {
        return trainingRepository.findActiveTrainings(pageable)
                .map(training -> modelMapper.map(training, TrainingResponseDTO.class));
    }

    @Override
    @Transactional
    public TrainingResponseDTO updateTraining(Long id, TrainingRequestDTO trainingRequestDTO) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found with id: " + id));

        modelMapper.map(trainingRequestDTO, training);
        Training updatedTraining = trainingRepository.save(training);
        return modelMapper.map(updatedTraining, TrainingResponseDTO.class);
    }

    @Override
    @Transactional
    public void deleteTraining(Long id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found with id: " + id));
        trainingRepository.delete(training);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getTrainingCategories() {
        return trainingRepository.findDistinctCategories();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrainingResponseDTO> getTrainingsByLevel(String level, Pageable pageable) {
        TrainingLevel trainingLevel = TrainingLevel.valueOf(level.toUpperCase());
        return trainingRepository.findByLevel(trainingLevel, pageable)
                .map(training -> modelMapper.map(training, TrainingResponseDTO.class));
    }
}