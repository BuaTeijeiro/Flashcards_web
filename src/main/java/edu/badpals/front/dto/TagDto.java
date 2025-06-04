package edu.badpals.front.dto;

import java.util.Objects;

public class TagDto {
    private long id;
    private String tag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagDto tagDto)) return false;
        return id == tagDto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
