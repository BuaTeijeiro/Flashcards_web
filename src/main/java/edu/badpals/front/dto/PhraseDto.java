package edu.badpals.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


public class PhraseDto {
    private long id;
    private String phrase;
    private String meaning;
    private int level;
    private List<SubstitutionRuleDto> substitutionRules;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public List<SubstitutionRuleDto> getSubstitutionRules() {
        return substitutionRules;
    }

    public void setSubstitutionRules(List<SubstitutionRuleDto> substitutionRules) {
        this.substitutionRules = substitutionRules;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
