package com.thiago.abandonedcode.domain.valueobjects;

public record Excerpt(String value) {
    private static final int MAX_LENGTH = 200;

    public static Excerpt fromContent(Content content) {
        String text = content.value();

        if(text.length() <= MAX_LENGTH){
            return new Excerpt(text);
        }

        return new Excerpt(text.substring(0, MAX_LENGTH) + "...");
    }

    public Excerpt{
        if(value == null || value.isBlank()) {
            throw new IllegalStateException("Excerpt não pode ser vazio");
        }
    }
}
