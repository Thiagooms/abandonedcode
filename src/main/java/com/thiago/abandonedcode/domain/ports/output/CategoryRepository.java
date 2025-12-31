package com.thiago.abandonedcode.domain.ports.output;

import com.thiago.abandonedcode.domain.entities.Category;
import com.thiago.abandonedcode.domain.valueobjects.Slug;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findById(Long id);
    Optional<Category> findBySlug(String slug);
    Optional<Category> findRootBySlug(Slug slug);
    Optional<Category> findBySlugAndParentId(Slug slug, Long parentId);
    Optional<Category> findByPath(String[] slugs);

    List<Category> findAll();
    List<Category> findByParentId(Long parentId);

    Category save(Category category);

    String buildFullPath(Long categoryId);
    int calculateDepth(Long categoryId);
    boolean existsBySlugAndParentId(Slug slug, Long parentId);
    boolean isDescendant(Long descendantId, Long ancestorId);

    void deleteById(Long id);
    boolean existsBySlug(String slug);
}
