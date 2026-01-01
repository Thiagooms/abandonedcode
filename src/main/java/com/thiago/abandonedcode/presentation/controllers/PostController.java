package com.thiago.abandonedcode.presentation.controllers;

import com.thiago.abandonedcode.application.mappers.PostDtoMapper;
import com.thiago.abandonedcode.domain.entities.Post;
import com.thiago.abandonedcode.domain.ports.input.CreatePostUseCase;
import com.thiago.abandonedcode.domain.ports.input.DeletePostUseCase;
import com.thiago.abandonedcode.domain.ports.input.GetPostUseCase;
import com.thiago.abandonedcode.domain.ports.input.ListPostsUseCase;
import com.thiago.abandonedcode.domain.ports.input.PublishPostUseCase;
import com.thiago.abandonedcode.domain.ports.input.UpdatePostUseCase;
import com.thiago.abandonedcode.presentation.dto.PostRequest;
import com.thiago.abandonedcode.presentation.dto.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "Posts", description = "Gerenciamento de posts do blog")
public class PostController {

    private final CreatePostUseCase createPostUseCase;
    private final ListPostsUseCase listPostsUseCase;
    private final GetPostUseCase getPostUseCase;
    private final UpdatePostUseCase updatePostUseCase;
    private final DeletePostUseCase deletePostUseCase;
    private final PublishPostUseCase publishPostUseCase;

    public PostController(
            CreatePostUseCase createPostUseCase,
            ListPostsUseCase listPostsUseCase,
            GetPostUseCase getPostUseCase,
            UpdatePostUseCase updatePostUseCase,
            DeletePostUseCase deletePostUseCase,
            PublishPostUseCase publishPostUseCase
    ) {
        this.createPostUseCase = createPostUseCase;
        this.listPostsUseCase = listPostsUseCase;
        this.getPostUseCase = getPostUseCase;
        this.updatePostUseCase = updatePostUseCase;
        this.deletePostUseCase = deletePostUseCase;
        this.publishPostUseCase = publishPostUseCase;
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@Valid @RequestBody PostRequest request) {
        Post post = createPostUseCase.execute(
                request.title(),
                request.content(),
                request.categoryId()
        );

        PostResponse response = PostDtoMapper.toResponse(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> listAll() {
        List<Post> posts = listPostsUseCase.execute();
        List<PostResponse> responses = posts.stream()
                .map(PostDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getById(@PathVariable Long id) {
        Post post = getPostUseCase.execute(id);
        PostResponse response = PostDtoMapper.toResponse(post);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody PostRequest request
    ) {
        Post post = updatePostUseCase.execute(
                id,
                request.title(),
                request.content()
        );
        PostResponse response = PostDtoMapper.toResponse(post);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deletePostUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<PostResponse> publish(@PathVariable Long id) {
        Post post = publishPostUseCase.publish(id);
        PostResponse response = PostDtoMapper.toResponse(post);
        return ResponseEntity.ok(response);
    }
}