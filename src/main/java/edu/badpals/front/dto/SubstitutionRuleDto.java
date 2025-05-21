package edu.badpals.front.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class SubstitutionRuleDto {

    private long id;
    private String word;
    private List<TagDto> tags;
    private CategoryDto category;
    private String inflectionName;

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

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public String getInflectionName() {
        return inflectionName;
    }

    public void setInflectionName(String inflectionName) {
        this.inflectionName = inflectionName;
    }
}
