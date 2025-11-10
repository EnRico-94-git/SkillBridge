package com.example.SkillBridge.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    private final ChatClient chatClient;

    public String generateCareerAdvice(String skills, String interests, String goals) {
        PromptTemplate promptTemplate = new PromptTemplate(AIConstants.CAREER_ADVICE_PROMPT_TEMPLATE);
        Prompt prompt = promptTemplate.create(Map.of(
                "skills", skills,
                "interests", interests,
                "goals", goals
        ));

        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    public String analyzeSkillGaps(String currentSkills) {
        PromptTemplate promptTemplate = new PromptTemplate(AIConstants.SKILL_GAP_PROMPT_TEMPLATE);
        Prompt prompt = promptTemplate.create(Map.of("currentSkills", currentSkills));

        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    public String generateTrainingRecommendations(String skillsToDevelop, String currentLevel, String goals) {
        PromptTemplate promptTemplate = new PromptTemplate(AIConstants.TRAINING_RECOMMENDATION_PROMPT_TEMPLATE);
        Prompt prompt = promptTemplate.create(Map.of(
                "skills", skillsToDevelop,
                "currentLevel", currentLevel,
                "goals", goals
        ));

        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }

    public String generateCustomPrompt(String promptTemplate, Map<String, Object> variables) {
        PromptTemplate template = new PromptTemplate(promptTemplate);
        Prompt prompt = template.create(variables);

        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }
}