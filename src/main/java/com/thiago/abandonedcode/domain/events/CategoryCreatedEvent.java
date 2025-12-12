package com.thiago.abandonedcode.domain.events;

import java.time.LocalDateTime;

public record CategoryCreatedEvent(
       Long categoryId,
       String name,
       String slug,
       LocalDateTime occurredOn
)   implements DomainEvent {

    public static CategoryCreatedEvent of(Long categoryId, String name, String slug) {
        return new CategoryCreatedEvent(
            categoryId,
            name,
            slug,
            LocalDateTime.now()
        );
    }
}
