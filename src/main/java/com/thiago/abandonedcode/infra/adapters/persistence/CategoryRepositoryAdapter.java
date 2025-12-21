package com.thiago.abandonedcode.infra.adapters.persistence;

import com.thiago.abandonedcode.domain.entities.Category;
import com.thiago.abandonedcode.domain.ports.output.CategoryRepository;
import com.thiago.abandonedcode.infra.persistence.entities.CategoryJpaEntity;
import com.thiago.abandonedcode.infra.persistence.mappers.CategoryJpaMapper;
import com.thiago.abandonedcode.infra.persistence.repositories.CategoryJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryRepositoryAdapter implements CategoryRepository{

    private final CategoryJpaRepository jpaRepository;
    private final CategoryJpaMapper mapper;

    public CategoryRepositoryAdapter(CategoryJpaRepository jpaRepository, CategoryJpaMapper mapper){
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Category> findBySlug(String slug) {
        return jpaRepository.findBySlug(slug)
                .map(mapper::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Category> findByParentId(Long parentId) {
        return jpaRepository.findByParentId(parentId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Category save(Category category) {
        CategoryJpaEntity entity = mapper.toJpaEntity(category);
        CategoryJpaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsBySlug(String slug) {
        return jpaRepository.existsBySlug(slug);
    }
}
