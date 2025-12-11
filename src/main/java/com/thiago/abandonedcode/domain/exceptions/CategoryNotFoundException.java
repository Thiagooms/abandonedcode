package com.thiago.abandonedcode.domain.exceptions;

public class CategoryNotFoundException extends DomainException {

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public static CategoryNotFoundException withId(Long id) {
        return new CategoryNotFoundException("Categoria com ID " + id + "não encontrada");
    }

    public static CategoryNotFoundException withSlug(String slug) {
        return new CategoryNotFoundException("Categoria com ID '" + slug + "' não encontrada");
    }
}
