package com.thiago.abandonedcode.application.services;

import com.thiago.abandonedcode.domain.entities.Category;
import com.thiago.abandonedcode.domain.entities.Post;
import com.thiago.abandonedcode.domain.exceptions.CategoryNotFoundException;
import com.thiago.abandonedcode.domain.exceptions.DuplicateSlugException;
import com.thiago.abandonedcode.domain.exceptions.PostNotFoundException;
import com.thiago.abandonedcode.domain.ports.input.CreatePostUseCase;
import com.thiago.abandonedcode.domain.ports.input.DeletePostUseCase;
import com.thiago.abandonedcode.domain.ports.input.GetPostUseCase;
import com.thiago.abandonedcode.domain.ports.input.ListPostsUseCase;
import com.thiago.abandonedcode.domain.ports.input.PublishPostUseCase;
import com.thiago.abandonedcode.domain.ports.input.UpdatePostUseCase;
import com.thiago.abandonedcode.domain.ports.output.CategoryRepository;
import com.thiago.abandonedcode.domain.ports.output.PostRepository;
import com.thiago.abandonedcode.domain.valueobjects.Content;

import java.util.List;

public class PostService implements CreatePostUseCase, ListPostsUseCase, GetPostUseCase, UpdatePostUseCase, DeletePostUseCase, PublishPostUseCase {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Post execute(String title, String content, Long categoryId) {
        Category category = null;
        if (categoryId != null) {
            category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> CategoryNotFoundException.withId(categoryId));
        }

        Content contentVo = new Content(content);

        Post post = new Post(title, contentVo, category);

        if (postRepository.existsBySlug(post.getSlug())) {
            throw DuplicateSlugException.forSlug(post.getSlug().value());
        }

        return postRepository.save(post);
    }

    @Override
    public List<Post> execute() {
        return postRepository.findAll();
    }

    @Override
    public Post execute(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.withId(id));
    }

    @Override
    public Post execute(Long id, String title, String content) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.withId(id));

        Content contentVo = new Content(content);

        post.update(title, contentVo);

        if (postRepository.existsBySlug(post.getSlug())) {
            Post existingPost = postRepository.findBySlug(post.getSlug()).orElse(null);
            if (existingPost != null && !existingPost.getId().equals(id)) {
                throw DuplicateSlugException.forSlug(post.getSlug().value());
            }
        }

        return postRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.withId(id));
        postRepository.deleteById(id);
    }

    @Override
    public Post publish(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> PostNotFoundException.withId(id));
        post.publish();
        return postRepository.save(post);
    }
}
