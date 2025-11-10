package com.example.SkillBridge.model;

public enum AssessmentStatus {
    DRAFT,           // Rascunho - avaliação não finalizada
    PENDING_REVIEW,  // Aguardando revisão
    IN_PROGRESS,     // Em progresso
    COMPLETED,       // Concluída
    EXPIRED,         // Expirada (caso tenha validade)
    CANCELLED        // Cancelada
}