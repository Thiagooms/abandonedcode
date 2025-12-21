package com.thiago.abandonedcode.infra.adapters.persistence;

import com.thiago.abandonedcode.domain.entities.Post;
import com.thiago.abandonedcode.domain.enums.PostStatus;
import com.thiago.abandonedcode.domain.ports.output.PostRepository;
import com.thiago.abandonedcode.domain.valueobjects.Slug;
import com.thiago.abandonedcode.infra.persistence.entities.PostJpaEntity;
import com.thiago.abandonedcode.infra.persistence.mappers.PostJpaMapper;
import com.thiago.abandonedcode.infra.persistence.repositories.PostJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PostRepositoryAdapter implements PostRepository{

    private final PostJpaRepository jpaRepository;
    private final PostJpaMapper mapper;

    public PostRepositoryAdapter(PostJpaRepository jpaRepository, PostJpaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Post save(Post post) {
        PostJpaEntity entity = mapper.toJpaEntity(post);
        PostJpaEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Post> findBySlug(Slug slug) {
        return jpaRepository.findBySlug(slug.value())
                .map(mapper::toDomain);
    }

    @Override
    public List<Post> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByStatus(PostStatus status) {
        return jpaRepository.findByStatus(status)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByCategoryId(Long categoryId) {
        return jpaRepository.findByCategoryId(categoryId)
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public boolean existsBySlug(Slug slug) {
        return jpaRepository.existsBySlug(slug.value());
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }
}
