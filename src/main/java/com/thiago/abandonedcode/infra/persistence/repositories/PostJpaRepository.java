package com.thiago.abandonedcode.infra.persistence.repositories;

import com.thiago.abandonedcode.domain.enums.PostStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import com.thiago.abandonedcode.infra.persistence.entities.PostJpaEntity;

import java.util.List;
import java.util.Optional;

public interface PostJpaRepository extends JpaRepository<PostJpaEntity, Long> {

    Optional<PostJpaEntity> findBySlug(String slug);

    List<PostJpaEntity> findByStatus(PostStatus status);
    List<PostJpaEntity> findByCategoryId(Long categoryId);

    boolean existsBySlug(String slug);

    List<PostJpaEntity> id(Long id);
}
