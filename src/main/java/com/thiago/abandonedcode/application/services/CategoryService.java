package com.thiago.abandonedcode.application.services;

import com.thiago.abandonedcode.domain.entities.Category;
import com.thiago.abandonedcode.domain.exceptions.CategoryNotFoundException;
import com.thiago.abandonedcode.domain.exceptions.DuplicateSlugException;
import com.thiago.abandonedcode.domain.exceptions.InvalidDomainStateException;
import com.thiago.abandonedcode.domain.ports.input.CreateCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.input.DeleteCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.input.GetCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.input.ListCategoriesUseCase;
import com.thiago.abandonedcode.domain.ports.input.UpdateCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.output.CategoryRepository;

import java.util.List;

public class CategoryService implements CreateCategoryUseCase, ListCategoriesUseCase, GetCategoryUseCase, UpdateCategoryUseCase, DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category execute(String name, Long parentId) {
        if (parentId != null) {
            categoryRepository.findById(parentId)
                    .orElseThrow(() -> CategoryNotFoundException.withId(parentId));

            int depth = categoryRepository.calculateDepth(parentId);
            if (depth >= 2){
                throw new InvalidDomainStateException("Hierarquia limitada a 3 níveis");
            }
        }

        Category category = parentId != null
                ? new Category(name, parentId)
                : new Category(name);

        if (categoryRepository.existsBySlugAndParentId(category.getSlug(), parentId)) {
            throw DuplicateSlugException.forSlug(category.getSlug().value());
        }

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> execute() {
        return categoryRepository.findAll();
    }

    @Override
    public Category execute(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> CategoryNotFoundException.withId(id));
    }

    @Override
    public Category execute(Long id, String name, Long parenteId) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> CategoryNotFoundException.withId(id));

        if (parenteId != null) {
            categoryRepository.findById(parenteId)
                    .orElseThrow(() -> CategoryNotFoundException.withId(parenteId));

            int depth = categoryRepository.calculateDepth(parenteId);
            if(depth >= 2) {
                throw new InvalidDomainStateException("Hierarquia limitada a 3 níveis");
            }

            if(categoryRepository.isDescendant(parenteId, id)) {
                throw new InvalidDomainStateException("Não pode mover categoria para seu próprio descendente");
            }
        }

        category.update(name);
        category.changeParent(parenteId);

        if (categoryRepository.existsBySlugAndParentId(category.getSlug(), parenteId)) {
            Category existing = categoryRepository
                    .findBySlugAndParentId(category.getSlug(), parenteId)
                    .orElse(null);

            if (existing != null && !existing.getId().equals(id)) {
                throw DuplicateSlugException.forSlug(category.getSlug().value());
            }
        }

        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> CategoryNotFoundException.withId(id));
        categoryRepository.deleteById(id);
    }
}