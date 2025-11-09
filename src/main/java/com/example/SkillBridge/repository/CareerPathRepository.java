package com.example.SkillBridge.repository;

import com.example.SkillBridge.model.User;
import com.example.SkillBridge.model.CareerPath;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerPathRepository extends JpaRepository<CareerPath, Long> {

    List<CareerPath> findByUser(User user);
    Page<CareerPath> findByUser(User user, Pageable pageable);

    @Query("SELECT cp FROM CareerPath cp WHERE cp.user.id = :userId ORDER BY cp.confidenceScore DESC")
    List<CareerPath> findTopRecommendationsByUser(@Param("userId") Long userId, Pageable pageable);
}