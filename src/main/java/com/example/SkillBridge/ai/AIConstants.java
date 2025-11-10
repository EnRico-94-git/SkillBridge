package com.example.SkillBridge.ai;

public class AIConstants {

    public static final String OPENAI_MODEL = "gpt-3.5-turbo";
    public static final double DEFAULT_TEMPERATURE = 0.7;
    public static final int DEFAULT_MAX_TOKENS = 1000;

    // Prompt templates
    public static final String CAREER_ADVICE_PROMPT_TEMPLATE =
            "Baseado nas seguintes habilidades: %s, interesses: %s e objetivos de carreira: %s, " +
                    "gere recomendações de carreira na economia verde e digital. " +
                    "Inclua: papel recomendado, nível de confiança (0-1), justificativa, " +
                    "caminho de aprendizado sugerido e tendências de mercado relevantes.";

    public static final String SKILL_GAP_PROMPT_TEMPLATE =
            "Identifique lacunas de habilidades para um profissional com as seguintes competências: %s. " +
                    "Foque nas áreas de tecnologia, sustentabilidade e habilidades do futuro. " +
                    "Forneça recomendações específicas para preenchimento dessas lacunas.";

    public static final String TRAINING_RECOMMENDATION_PROMPT_TEMPLATE =
            "Recomende treinamentos para desenvolver habilidades em: %s. " +
                    "Considere: nível atual %s, objetivos %s, e disponibilidade de tempo.";

    // Career roles for green economy
    public static final String[] GREEN_ECONOMY_ROLES = {
            "Sustainability Analyst",
            "Renewable Energy Engineer",
            "Green Building Consultant",
            "Environmental Data Scientist",
            "Circular Economy Specialist",
            "ESG Investment Analyst",
            "Climate Risk Manager",
            "Sustainable Supply Chain Manager"
    };

    private AIConstants() {
        // Utility class
    }
}