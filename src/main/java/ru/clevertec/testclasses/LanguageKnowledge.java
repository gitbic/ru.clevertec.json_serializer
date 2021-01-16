package ru.clevertec.testclasses;

import java.util.Objects;

public class LanguageKnowledge {
    private String language;
    private int reading;
    private KnowledgeRate knowledgeRate;

    public LanguageKnowledge(String language, int reading, KnowledgeRate knowledgeRate) {
        this.language = language;
        this.reading = reading;
        this.knowledgeRate = knowledgeRate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getReading() {
        return reading;
    }

    public void setReading(int reading) {
        this.reading = reading;
    }

    public KnowledgeRate getKnowledgeRate() {
        return knowledgeRate;
    }

    public void setKnowledgeRate(KnowledgeRate knowledgeRate) {
        this.knowledgeRate = knowledgeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LanguageKnowledge that = (LanguageKnowledge) o;
        return reading == that.reading &&
                Objects.equals(language, that.language) &&
                knowledgeRate == that.knowledgeRate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, reading, knowledgeRate);
    }
}
