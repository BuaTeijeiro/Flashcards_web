package edu.badpals.front.dto;

public class InflectionDto {
    private long id;

    private String inflection;
    private String affix;
    private InflectionMode mode;

    public long getId() {
        return id;
    }

    public String getInflection() {
        return inflection;
    }

    public String getAffix() {
        return affix;
    }

    public InflectionMode getMode() {
        return mode;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setInflection(String inflection) {
        this.inflection = inflection;
    }

    public void setAffix(String affix) {
        this.affix = affix;
    }

    public void setMode(InflectionMode mode) {
        this.mode = mode;
    }
}
