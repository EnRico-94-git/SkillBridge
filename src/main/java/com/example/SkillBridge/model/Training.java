package com.example.SkillBridge.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "SB_TRAININGS")
@Getter
@Setter
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "training_seq")
    @SequenceGenerator(name = "training_seq", sequenceName = "SB_TRAINING_SEQ", allocationSize = 1)
    @Column(name = "TRAINING_ID")
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "CLOB")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "LEVEL", length = 20)
    private TrainingLevel level;

    @Column(name = "CATEGORY", length = 50)
    private String category;

    @Column(name = "DURATION_HOURS")
    private Integer durationHours;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    public Training() {}

    public Training(String title, String description, TrainingLevel level, String category, Integer durationHours) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.category = category;
        this.durationHours = durationHours;
    }
}