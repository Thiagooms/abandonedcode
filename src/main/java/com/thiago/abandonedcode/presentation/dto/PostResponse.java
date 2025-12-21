package com.thiago.abandonedcode.presentation.dto;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        String title,
        String slug,
        String content,
        String excerpt,
        Long viewCount,
        String featureImage,
        String status,
        CategoryResponse category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime publishedAt
) {}
