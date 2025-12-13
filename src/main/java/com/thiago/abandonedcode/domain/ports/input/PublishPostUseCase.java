package com.thiago.abandonedcode.domain.ports.input;

import com.thiago.abandonedcode.domain.entities.Post;

public interface PublishPostUseCase {
    Post publish(Long id);
}
