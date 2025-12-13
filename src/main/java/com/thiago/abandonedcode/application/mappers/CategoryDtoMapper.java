package com.thiago.abandonedcode.application.mappers;

import com.thiago.abandonedcode.domain.entities.Category;
import com.thiago.abandonedcode.presentation.dto.CategoryRequest;
import com.thiago.abandonedcode.presentation.dto.CategoryResponse;

public class CategoryDtoMapper {
    public static Category toDomain(CategoryRequest request) {
        if(request.parentId() != null) {
            return new Category(request.name(), request.parentId());
        }
        return new Category(request.name());
    }

    public static CategoryResponse toResponse(Category category, String parentName) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getSlug().value(),
                category.getParentId(),
                parentName,
                category.getCreatedAt(),
                category.getUpdatedAt()
        );
    }

    public static CategoryResponse toResponse(Category category) {
        return toResponse(category, null);
    }
}
