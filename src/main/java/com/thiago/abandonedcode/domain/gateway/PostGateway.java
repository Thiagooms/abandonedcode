package com.thiago.abandonedcode.domain.gateway;

import com.thiago.abandonedcode.domain.entities.Post;
import com.thiago.abandonedcode.domain.enums.PostStatus;
import com.thiago.abandonedcode.domain.valueobjects.Slug;

import java.util.List;
import java.util.Optional;

public interface PostGateway {

    Post save(Post post);

    Optional<Post> findById(Long id);
    Optional<Post> findBySlug(Slug slug);

    List<Post> findAll();
    List<Post> findByStatus(PostStatus status);
    List<Post> findByCategoryId(Long categoryId);

    void deleteById(Long id);

    boolean existsById(Long id);
    boolean existsBySlug(Slug slug);

    long count();

}
