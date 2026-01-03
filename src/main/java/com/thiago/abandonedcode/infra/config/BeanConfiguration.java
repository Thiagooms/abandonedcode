package com.thiago.abandonedcode.infra.config;

import com.thiago.abandonedcode.application.services.CategoryService;
import com.thiago.abandonedcode.application.services.PostService;
import com.thiago.abandonedcode.domain.ports.input.CreateCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.input.CreatePostUseCase;
import com.thiago.abandonedcode.domain.ports.input.DeleteCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.input.DeletePostUseCase;
import com.thiago.abandonedcode.domain.ports.input.GetCategoryByPathUseCase;
import com.thiago.abandonedcode.domain.ports.input.GetCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.input.GetPostUseCase;
import com.thiago.abandonedcode.domain.ports.input.ListCategoriesUseCase;
import com.thiago.abandonedcode.domain.ports.input.ListPostsUseCase;
import com.thiago.abandonedcode.domain.ports.input.PublishPostUseCase;
import com.thiago.abandonedcode.domain.ports.input.UpdateCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.input.UpdatePostUseCase;
import com.thiago.abandonedcode.domain.ports.output.CategoryRepository;
import com.thiago.abandonedcode.domain.ports.output.PostRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository) {
        return new CategoryService(categoryRepository);
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(CategoryService categoryService) {
        return categoryService;
    }

    @Bean
    public PostService postService(
            PostRepository postRepository,
            CategoryRepository categoryRepository
    ) {
        return new PostService(postRepository, categoryRepository);
    }

    @Bean
    public CreatePostUseCase createPostUseCase(PostService postService) {
        return postService;
    }

    @Bean
    public ListPostsUseCase listPostsUseCase(PostService postService) {
        return postService;
    }

    @Bean
    public GetPostUseCase getPostUseCase(PostService postService) {
        return postService;
    }

    @Bean
    public UpdatePostUseCase updatePostUseCase(PostService postService) {
        return postService;
    }

    @Bean
    public DeletePostUseCase deletePostUseCase(PostService postService) {
        return postService;
    }

    @Bean
    public PublishPostUseCase publishPostUseCase(PostService postService) {
        return postService;
    }

    @Bean
    public ListCategoriesUseCase listCategoriesUseCase(CategoryService categoryService) {
        return categoryService;
    }

    @Bean
    public GetCategoryUseCase getCategoryUseCase(CategoryService categoryService) {
        return categoryService;
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase(CategoryService categoryService) {
        return categoryService;
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase(CategoryService categoryService) {
        return categoryService;
    }

    @Bean
    public GetCategoryByPathUseCase getCategoryByPathUseCase(CategoryService categoryService) {
        return categoryService;
    }
}
