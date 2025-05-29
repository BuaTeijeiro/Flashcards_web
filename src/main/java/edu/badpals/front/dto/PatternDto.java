package edu.badpals.front.dto;

import java.util.List;
import java.util.Objects;

public class PatternDto {
    private Long Id;
    private String name;
    private String pattern;
    private List<InflectionDto> inflections;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public List<InflectionDto> getInflections() {
        return inflections;
    }

    public void setInflections(List<InflectionDto> inflections) {
        this.inflections = inflections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatternDto that)) return false;
        return Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }
}
