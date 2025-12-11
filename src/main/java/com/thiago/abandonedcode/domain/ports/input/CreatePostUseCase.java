package com.thiago.abandonedcode.domain.ports.input;

import com.thiago.abandonedcode.domain.entities.Post;

public interface CreatePostUseCase {
    Post execute(String title, String content, String excerpt, Long categoryId);
}
