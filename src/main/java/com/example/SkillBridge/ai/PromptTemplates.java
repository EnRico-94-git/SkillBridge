package com.example.SkillBridge.ai;

import org.springframework.stereotype.Component;

@Component
public class PromptTemplates {

    public String getCareerAdvicePrompt(String skills, String interests, String goals) {
        return String.format(
                "Atue como um consultor de carreira especializado em economia verde e digital. " +
                        "Analise o seguinte perfil:\n" +
                        "Habilidades: %s\n" +
                        "Interesses: %s\n" +
                        "Objetivos de Carreira: %s\n\n" +
                        "Forneça uma recomendação detalhada incluindo:\n" +
                        "1. Papel recomendado\n" +
                        "2. Pontuação de confiança (0-1)\n" +
                        "3. Recomendação personalizada\n" +
                        "4. Caminho de aprendizado\n" +
                        "5. Tendências de mercado relevantes\n" +
                        "6. Tempo estimado de preparação\n\n" +
                        "Formate a resposta em JSON.",
                skills, interests, goals
        );
    }

    public String getSkillGapAnalysisPrompt(String currentSkills, String targetRole) {
        return String.format(
                "Analise as lacunas de habilidades para transição de %s para %s. " +
                        "Liste as principais habilidades faltantes e um plano de desenvolvimento.",
                currentSkills, targetRole
        );
    }
}