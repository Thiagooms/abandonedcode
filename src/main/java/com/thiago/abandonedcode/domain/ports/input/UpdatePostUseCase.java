package com.thiago.abandonedcode.domain.ports.input;

import com.thiago.abandonedcode.domain.entities.Post;

public interface UpdatePostUseCase {
    Post execute(Long id, String title, String content, String excerpt);
}
