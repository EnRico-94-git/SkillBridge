package com.example.SkillBridge.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "SB_CAREER_PATHS")
@Getter
@Setter
public class CareerPath {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "career_path_seq")
    @SequenceGenerator(name = "career_path_seq", sequenceName = "SB_CAREER_PATH_SEQ", allocationSize = 1)
    @Column(name = "PATH_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "RECOMMENDED_ROLE", length = 100)
    private String recommendedRole;

    @Column(name = "CONFIDENCE_SCORE")
    private Double confidenceScore;

    @Column(name = "AI_RECOMMENDATION", columnDefinition = "CLOB")
    private String aiRecommendation;

    @CreationTimestamp
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    public CareerPath() {}

    public CareerPath(User user, String recommendedRole, Double confidenceScore, String aiRecommendation) {
        this.user = user;
        this.recommendedRole = recommendedRole;
        this.confidenceScore = confidenceScore;
        this.aiRecommendation = aiRecommendation;
    }
}