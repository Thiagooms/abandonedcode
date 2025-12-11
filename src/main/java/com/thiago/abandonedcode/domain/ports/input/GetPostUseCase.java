package com.thiago.abandonedcode.domain.ports.input;

import com.thiago.abandonedcode.domain.entities.Post;

public interface GetPostUseCase {
    Post execute(Long id);
}
