package com.thiago.abandonedcode.infra.adapters.persistence;

import com.thiago.abandonedcode.domain.entities.Category;
import com.thiago.abandonedcode.domain.ports.output.CategoryRepository;
import com.thiago.abandonedcode.domain.valueobjects.Slug;
import com.thiago.abandonedcode.infra.persistence.entities.CategoryJpaEntity;
import com.thiago.abandonedcode.infra.persistence.mappers.CategoryJpaMapper;
import com.thiago.abandonedcode.infra.persistence.repositories.CategoryJpaRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
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

    @Override
    public Optional<Category> findRootBySlug(Slug slug) {
        return jpaRepository.findBySlugAndParentIdIsNull(slug.value())
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Category> findBySlugAndParentId(Slug slug, Long parentId) {
        return jpaRepository.findBySlugAndParentId(slug.value(), parentId)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsBySlugAndParentId(Slug slug, Long parentId) {
        return jpaRepository.existsBySlugAndParentId(slug.value(), parentId);
    }

    @Override
    public Optional<Category> findByPath(String[] slugs) {
        if(slugs == null || slugs.length == 0) {
            return Optional.empty();
        }

        Category current = findRootBySlug(Slug.fromTitle(slugs[0]))
                .orElse(null);

        if(current == null) return Optional.empty();

        for(int i = 1; i < slugs.length; i++) {
            current = findBySlugAndParentId(Slug.fromTitle(slugs[i]), current.getId())
                    .orElse(null);

            if(current == null) return Optional.empty();
        }

        return Optional.of(current);
    }

    @Override
    public int calculateDepth(Long categoryId) {
        int depth = 0;
        Long currentId = categoryId;

        while(currentId != null) {
            Category category = findById(currentId).orElse(null);
            if(category == null) break;

            currentId = category.getParentId();
            depth++;

            if(depth > 10) {
                throw new IllegalStateException("Possível ciclo detectado");
            }
        }

        return depth;
    }

    @Override
    public String buildFullPath(Long categoryId) {
        List<String> slugs = new ArrayList<>();
        Long currentId = categoryId;

        while(currentId != null) {
            Category category = findById(currentId).orElse(null);
            if(category == null) break;

            slugs.add(0, category.getSlug().value());
            currentId = category.getParentId();
        }

        return "/" + String.join("/", slugs);
    }

    @Override
    public boolean isDescendant(Long descendantId, Long ancestorId) {
        Long currentId = descendantId;
        int safety = 0;

        while(currentId != null && safety < 100) {
            if(currentId.equals(ancestorId)) {
                return true;
            }

            Category parent = findById(currentId).orElse(null);
            currentId = (parent != null) ? parent.getParentId() : null;
            safety++;
        }

        return false;
    }
}
