package edu.badpals.front.dto;

import java.util.List;

public class DeckDto {
    private long id;
    private String name;
    private UserDto owner;
    private List<WordDto> words;
    private List<PhraseDto> phrases;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
