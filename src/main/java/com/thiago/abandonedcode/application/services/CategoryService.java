package com.thiago.abandonedcode.application.services;

import com.thiago.abandonedcode.domain.entities.Category;
import com.thiago.abandonedcode.domain.exceptions.CategoryNotFoundException;
import com.thiago.abandonedcode.domain.exceptions.DuplicateSlugException;
import com.thiago.abandonedcode.domain.ports.input.CreateCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.output.CategoryRepository;

public class CategoryService implements CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category execute(String name, Long parentId) {
        if (parentId != null) {
            categoryRepository.findById(parentId)
                    .orElseThrow(() -> CategoryNotFoundException.withId(parentId));
        }

        Category category;
        if (parentId != null) {
            category = new Category(name, parentId);
        } else {
            category = new Category(name);
        }

        if (categoryRepository.existsBySlug(category.getSlug().value())) {
            throw DuplicateSlugException.forSlug(category.getSlug().value());
        }

        return categoryRepository.save(category);
    }
}