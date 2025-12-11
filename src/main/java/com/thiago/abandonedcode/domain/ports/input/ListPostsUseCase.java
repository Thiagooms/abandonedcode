package com.thiago.abandonedcode.domain.ports.input;

import com.thiago.abandonedcode.domain.entities.Post;
import java.util.List;

public interface ListPostsUseCase {
    List<Post> execute();
}
