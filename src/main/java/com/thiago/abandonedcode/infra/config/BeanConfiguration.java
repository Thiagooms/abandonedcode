package com.thiago.abandonedcode.infra.config;

import com.thiago.abandonedcode.application.services.CategoryService;
import com.thiago.abandonedcode.application.services.PostService;
import com.thiago.abandonedcode.domain.ports.input.CreateCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.input.CreatePostUseCase;
import com.thiago.abandonedcode.domain.ports.output.CategoryRepository;
import com.thiago.abandonedcode.domain.ports.output.PostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(CategoryRepository categoryRepository) {
        return new CategoryService(categoryRepository);
    }

    @Bean
    public CreatePostUseCase createPostUseCase(
            PostRepository postRepository,
            CategoryRepository categoryRepository
    ) {
        return new PostService(postRepository, categoryRepository);
    }
}
