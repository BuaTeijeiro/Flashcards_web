package edu.badpals.front.dto;

import java.util.List;

public class DeckDto {
    private Long id;
    private String name;
    private String language;
    private UserDto owner;
    private List<WordDto> words;
    private List<PhraseDto> phrases;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public List<WordDto> getWords() {
        return words;
    }

    public void setWords(List<WordDto> words) {
        this.words = words;
    }

    public List<PhraseDto> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<PhraseDto> phrases) {
        this.phrases = phrases;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
