package com.example.SkillBridge.ai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    private final ChatClient chatClient;

    public String generateCareerAdvice(String skills, String interests, String goals) {
        String prompt = String.format(AIConstants.CAREER_ADVICE_PROMPT_TEMPLATE,
                skills, interests, goals);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    public String analyzeSkillGaps(String currentSkills) {
        String prompt = String.format(AIConstants.SKILL_GAP_PROMPT_TEMPLATE, currentSkills);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    public String generateTrainingRecommendations(String skillsToDevelop, String currentLevel, String goals) {
        String prompt = String.format(AIConstants.TRAINING_RECOMMENDATION_PROMPT_TEMPLATE,
                skillsToDevelop, currentLevel, goals);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    public String generateCustomPrompt(String promptTemplate, Map<String, Object> variables) {
        String prompt = promptTemplate;
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            prompt = prompt.replace("{" + entry.getKey() + "}", String.valueOf(entry.getValue()));
        }

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}