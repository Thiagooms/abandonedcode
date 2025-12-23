package com.thiago.abandonedcode.presentation.controllers;

import com.thiago.abandonedcode.application.mappers.PostDtoMapper;
import com.thiago.abandonedcode.domain.entities.Post;
import com.thiago.abandonedcode.domain.ports.input.CreatePostUseCase;
import com.thiago.abandonedcode.presentation.dto.PostRequest;
import com.thiago.abandonedcode.presentation.dto.PostResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final CreatePostUseCase createPostUseCase;

    public PostController(CreatePostUseCase createPostUseCase) {
        this.createPostUseCase = createPostUseCase;
    }

    @PostMapping
    public ResponseEntity<PostResponse> create(@Valid @RequestBody PostRequest request) {
        Post post = createPostUseCase.execute(
                request.title(),
                request.content(),
                request.excerpt(),
                request.categoryId()
        );

        PostResponse response = PostDtoMapper.toResponse(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}