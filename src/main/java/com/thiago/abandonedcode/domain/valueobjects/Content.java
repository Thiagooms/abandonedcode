package com.thiago.abandonedcode.domain.valueobjects;

public record Content(String value){
    public Content {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Conteúdo não pode ser vázio");
        }
    }
}
