package com.example.SkillBridge.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "SB_SKILL_ASSESSMENTS")
@Getter
@Setter
public class SkillAssessment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assessment_seq")
    @SequenceGenerator(name = "assessment_seq", sequenceName = "SB_ASSESSMENT_SEQ", allocationSize = 1)
    @Column(name = "ASSESSMENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "SKILL_NAME", length = 100)
    private String skillName;

    @Column(name = "SKILL_LEVEL")
    private Integer skillLevel; // 1-5

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY", length = 50)
    private SkillCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 20)
    private AssessmentStatus status = AssessmentStatus.DRAFT;

    @CreationTimestamp
    @Column(name = "ASSESSMENT_DATE", updatable = false)
    private LocalDateTime assessmentDate;

    @Column(name = "COMPLETED_AT")
    private LocalDateTime completedAt;

    public SkillAssessment() {}

    public SkillAssessment(User user, String skillName, Integer skillLevel, SkillCategory category) {
        this.user = user;
        this.skillName = skillName;
        this.skillLevel = skillLevel;
        this.category = category;
        this.status = AssessmentStatus.DRAFT;
    }

    public void markAsCompleted() {
        this.status = AssessmentStatus.COMPLETED;
        this.completedAt = LocalDateTime.now();
    }
}