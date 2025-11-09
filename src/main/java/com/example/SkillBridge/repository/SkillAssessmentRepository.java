package com.example.SkillBridge.repository;

import com.example.SkillBridge.model.SkillAssessment;
import com.example.SkillBridge.model.User;
import com.example.SkillBridge.model.SkillCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillAssessmentRepository extends JpaRepository<SkillAssessment, Long> {

    List<SkillAssessment> findByUser(User user);

    Page<SkillAssessment> findByUser(User user, Pageable pageable);

    List<SkillAssessment> findByUserAndCategory(User user, SkillCategory category);

    @Query("SELECT sa FROM SkillAssessment sa WHERE sa.user.id = :userId AND sa.skillLevel >= :minLevel")
    List<SkillAssessment> findStrongSkillsByUser(@Param("userId") Long userId, @Param("minLevel") Integer minLevel);
}
