package com.thiago.abandonedcode.domain.ports.output;

import com.thiago.abandonedcode.domain.entities.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findById(Long id);
    Optional<Category> findBySlug(String slug);

    List<Category> findAll();
    List<Category> findByParentId(Long parentId);

    Category save(Category category);

    void deleteById(Long id);
    boolean existsBySlug(String slug);
}
