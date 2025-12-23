package com.thiago.abandonedcode.infra.persistence.mappers;

import com.thiago.abandonedcode.domain.entities.Category;
import com.thiago.abandonedcode.infra.persistence.entities.CategoryJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryJpaMapper {

    public Category toDomain(CategoryJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        Long parentId = null;
        if (entity.getParent() != null) {
            parentId = entity.getParent().getId();
        }

        Category category;
        if (parentId != null) {
            category = new Category(entity.getName(), parentId);
        } else {
            category = new Category(entity.getName());
        }

        category.setId(entity.getId());
        category.setCreatedAt(entity.getCreatedAt());
        category.setUpdatedAt(entity.getUpdatedAt());

        return category;
    }

    public CategoryJpaEntity toJpaEntity(Category category) {
        if (category == null) {
            return null;
        }

        CategoryJpaEntity entity = new CategoryJpaEntity();

        if (category.getId() != null) {
            entity.setId(category.getId());
        }
        entity.setName(category.getName());
        entity.setSlug(category.getSlug().value());
        entity.setCreatedAt(category.getCreatedAt());
        entity.setUpdatedAt(category.getUpdatedAt());

        if (category.getParentId() != null) {
            CategoryJpaEntity parentReference = new CategoryJpaEntity();
            parentReference.setId(category.getParentId());
            entity.setParent(parentReference);
        }

        return entity;
    }
}
