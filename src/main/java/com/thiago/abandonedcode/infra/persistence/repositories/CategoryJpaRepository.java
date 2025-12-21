package com.thiago.abandonedcode.infra.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thiago.abandonedcode.infra.persistence.entities.CategoryJpaEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, Long> {

    Optional<CategoryJpaEntity> findBySlug(String slug);
    List<CategoryJpaEntity> findByParentId(Long parentId);
    boolean existsBySlug(String slug);
}
