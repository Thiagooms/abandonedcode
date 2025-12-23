package com.thiago.abandonedcode.application.services;

import com.thiago.abandonedcode.domain.entities.Category;
import com.thiago.abandonedcode.domain.entities.Post;
import com.thiago.abandonedcode.domain.exceptions.CategoryNotFoundException;
import com.thiago.abandonedcode.domain.exceptions.DuplicateSlugException;
import com.thiago.abandonedcode.domain.ports.input.CreatePostUseCase;
import com.thiago.abandonedcode.domain.ports.output.CategoryRepository;
import com.thiago.abandonedcode.domain.ports.output.PostRepository;
import com.thiago.abandonedcode.domain.valueobjects.Content;
import com.thiago.abandonedcode.domain.valueobjects.Excerpt;

public class PostService implements CreatePostUseCase {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Post execute(String title, String content, String excerpt, Long categoryId) {
        Category category = null;
        if (categoryId != null) {
            category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> CategoryNotFoundException.withId(categoryId));
        }

        Content contentVo = new Content(content);

        Excerpt excerptVo;
        if (excerpt != null && !excerpt.isBlank()) {
            excerptVo = new Excerpt(excerpt);
        } else {
            excerptVo = Excerpt.fromContent(contentVo);
        }

        Post post = new Post(title, contentVo, excerptVo, category);

        if (postRepository.existsBySlug(post.getSlug())) {
            throw DuplicateSlugException.forSlug(post.getSlug().value());
        }

        return postRepository.save(post);
    }
}
