package com.thiago.abandonedcode.domain.events;

import java.time.LocalDateTime;

public record PostPublishedEvent(
        Long postId,
        String title,
        String slug,
        Long categoryId,
        LocalDateTime occurredOn
) implements DomainEvent {

    public static PostPublishedEvent of(Long postId, String title, String slug, Long categoryId) {
        return new PostPublishedEvent(
                postId,
                title,
                slug,
                categoryId,
                LocalDateTime.now()
        );
    }
}
