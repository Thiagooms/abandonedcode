package com.thiago.abandonedcode.presentation.dto;

import java.time.LocalDateTime;

public record CategoryResponse(
        Long id,
        String name,
        String slug,
        Long parentId,
        String parentName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
