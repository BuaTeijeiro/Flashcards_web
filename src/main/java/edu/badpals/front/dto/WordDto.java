package edu.badpals.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class WordDto {

    private long id;
    private DeckDto deck;
    private String word;
    private String meaning;
    private CategoryDto category;
    private PatternDto pattern;
    private List<TagDto> tags;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public DeckDto getDeck() {
        return deck;
    }

    public void setDeck(DeckDto deck) {
        this.deck = deck;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public PatternDto getPattern() {
        return pattern;
    }

    public void setPattern(PatternDto pattern) {
        this.pattern = pattern;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }
}
