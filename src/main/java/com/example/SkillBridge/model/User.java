package com.example.SkillBridge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SB_USERS")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "SB_USER_SEQ", allocationSize = 1)
    @Column(name = "USER_ID")
    private Long id;

    @NotBlank(message = "{validation.user.name.required}")
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Email(message = "{validation.user.email.invalid}")
    @Column(name = "EMAIL", unique = true, nullable = false, length = 150)
    private String email;

    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", length = 20)
    private UserRole role;

    @CreationTimestamp
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SkillAssessment> assessments = new ArrayList<>();

    // Construtores
    public User() {}

    public User(String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}