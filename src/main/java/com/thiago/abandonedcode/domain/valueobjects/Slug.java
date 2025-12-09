package com.thiago.abandonedcode.domain.valueobjects;

import java.text.Normalizer;
import java.util.Locale;

public record Slug(String value){
    public static Slug fromTitle(String title){
        String normalized = Normalizer.normalize(title,Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase(Locale.ROOT)
                .trim()
                .replaceAll("\\s+", "-")
                .replaceAll("[^a-z0-9-]", "");

            return new Slug(normalized);
    }
}
