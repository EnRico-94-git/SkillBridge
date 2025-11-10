package com.example.SkillBridge.util;

public class Constants {

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    public static class CacheNames {
        public static final String USERS = "users";
        public static final String CAREER_ADVICE = "careerAdvice";
        public static final String ASSESSMENTS = "assessments";
    }

    public static class Security {
        public static final long JWT_EXPIRATION_MS = 86400000; // 24 hours
    }
}