package edu.badpals.front.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum InflectionMode {
    START_APPEND("añadir al inicio"),
    START_SUBSTITUTE("substituir al inicio"),
    END_APPEND("añadir al final"),
    END_SUBSTITUTE("substituir al final"),
    @JsonEnumDefaultValue
    NONE("invariante");

    private final String description;

    InflectionMode(String description){
        this.description = description;

    }

    public String getDescription() {
        return description;
    }
}
