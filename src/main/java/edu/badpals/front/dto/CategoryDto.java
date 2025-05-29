package edu.badpals.front.dto;

import java.util.List;
import java.util.Objects;

public class CategoryDto {
    private Long id;
    private String name;
    private String language;
    private List<String> inflectionsNames;
    private List<PatternDto> patterns;

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

    public List<String> getInflectionsNames() {
        return inflectionsNames;
    }

    public void setInflectionsNames(List<String> inflectionsNames) {
        this.inflectionsNames = inflectionsNames;
    }

    public List<PatternDto> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<PatternDto> patterns) {
        this.patterns = patterns;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryDto that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
