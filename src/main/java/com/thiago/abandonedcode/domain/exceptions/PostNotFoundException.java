package com.thiago.abandonedcode.domain.exceptions;

public class PostNotFoundException extends DomainException {

    public PostNotFoundException(String message){
        super(message);
    }

    public static PostNotFoundException withId(Long id) {
        return new PostNotFoundException("Post com ID " + id + " não encontrado!");
    }

    public static PostNotFoundException withSlug(String slug){
        return new PostNotFoundException("Post com slug '" + slug + "' não encontrado");
    }
}
