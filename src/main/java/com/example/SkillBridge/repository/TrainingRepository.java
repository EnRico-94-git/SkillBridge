package com.example.SkillBridge.repository;

import com.example.SkillBridge.model.Training;
import com.example.SkillBridge.model.TrainingLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

    Page<Training> findByLevel(TrainingLevel level, Pageable pageable);
    Page<Training> findByCategory(String category, Pageable pageable);

    @Query("SELECT t FROM Training t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Training> findByTitleContainingIgnoreCase(@Param("title") String title, Pageable pageable);

    @Query("SELECT t FROM Training t WHERE t.level = :level AND t.category = :category")
    List<Training> findByLevelAndCategory(@Param("level") TrainingLevel level, @Param("category") String category);

    @Query("SELECT t FROM Training t WHERE t.isActive = true")
    Page<Training> findActiveTrainings(Pageable pageable);

    @Query("SELECT DISTINCT t.category FROM Training t WHERE t.isActive = true")
    List<String> findDistinctCategories();
}