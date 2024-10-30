package com.boss.model;

import java.util.Map;

public class QuestionAnalysis {
    private String question;
    private Map<String, Double> bloomLevelPercentage;  // Store Bloom's level and percentage

    public QuestionAnalysis(String question, Map<String, Double> bloomLevelPercentage) {
        this.question = question;
        this.bloomLevelPercentage = bloomLevelPercentage;
    }

    public String getQuestion() {
        return question;
    }

    public Map<String, Double> getBloomLevelPercentage() {
        return bloomLevelPercentage;
    }
}
