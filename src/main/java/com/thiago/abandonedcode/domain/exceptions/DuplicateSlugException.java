package com.thiago.abandonedcode.domain.exceptions;

public class DuplicateSlugException extends DomainException {

    public DuplicateSlugException(String message) {
        super(message);
    }

    public static DuplicateSlugException
    forSlug(String slug) {
        return new DuplicateSlugException("Já existe um registro com o slug '" + slug + "'");
    }
}
