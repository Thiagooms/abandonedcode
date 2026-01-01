package com.thiago.abandonedcode.presentation.dto;

import java.time.LocalDateTime;

public record CategoryResponse(
        Long id,
        String name,
        String slug,
        Long parentId,
        String parentName,
        String fullPath,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
