package com.example.SkillBridge.controller;

import com.example.SkillBridge.dto.request.TrainingRequestDTO;
import com.example.SkillBridge.dto.response.ApiResponseDTO;
import com.example.SkillBridge.dto.response.TrainingResponseDTO;
import com.example.SkillBridge.service.TrainingService;
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
@RequestMapping("/api/trainings")
@RequiredArgsConstructor
@Tag(name = "Trainings", description = "Training management endpoints")
public class TrainingController {

    private final TrainingService trainingService;

    @PostMapping
    @Operation(summary = "Create a new training")
    public ResponseEntity<ApiResponseDTO<TrainingResponseDTO>> createTraining(@Valid @RequestBody TrainingRequestDTO trainingRequestDTO) {
        TrainingResponseDTO trainingResponseDTO = trainingService.createTraining(trainingRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDTO.success(trainingResponseDTO, "Training created successfully"));
    }

    @GetMapping
    @Operation(summary = "Get all trainings with pagination")
    public ResponseEntity<ApiResponseDTO<Page<TrainingResponseDTO>>> getAllTrainings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

        Page<TrainingResponseDTO> trainings = trainingService.getAllTrainings(pageable);
        return ResponseEntity.ok(ApiResponseDTO.success(trainings, "Trainings retrieved successfully"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get training by ID")
    public ResponseEntity<ApiResponseDTO<TrainingResponseDTO>> getTrainingById(@PathVariable Long id) {
        TrainingResponseDTO trainingResponseDTO = trainingService.getTrainingById(id);
        return ResponseEntity.ok(ApiResponseDTO.success(trainingResponseDTO, "Training retrieved successfully"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update training")
    public ResponseEntity<ApiResponseDTO<TrainingResponseDTO>> updateTraining(
            @PathVariable Long id, @Valid @RequestBody TrainingRequestDTO trainingRequestDTO) {
        TrainingResponseDTO trainingResponseDTO = trainingService.updateTraining(id, trainingRequestDTO);
        return ResponseEntity.ok(ApiResponseDTO.success(trainingResponseDTO, "Training updated successfully"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete training")
    public ResponseEntity<ApiResponseDTO<Void>> deleteTraining(@PathVariable Long id) {
        trainingService.deleteTraining(id);
        return ResponseEntity.ok(ApiResponseDTO.success(null, "Training deleted successfully"));
    }

    @GetMapping("/categories")
    @Operation(summary = "Get all training categories")
    public ResponseEntity<ApiResponseDTO<List<String>>> getTrainingCategories() {
        List<String> categories = trainingService.getTrainingCategories();
        return ResponseEntity.ok(ApiResponseDTO.success(categories, "Categories retrieved successfully"));
    }

    @GetMapping("/level/{level}")
    @Operation(summary = "Get trainings by level")
    public ResponseEntity<ApiResponseDTO<Page<TrainingResponseDTO>>> getTrainingsByLevel(
            @PathVariable String level,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TrainingResponseDTO> trainings = trainingService.getTrainingsByLevel(level, pageable);
        return ResponseEntity.ok(ApiResponseDTO.success(trainings, "Trainings retrieved successfully"));
    }
}