package com.thiago.abandonedcode.presentation.controllers;

import com.thiago.abandonedcode.application.mappers.CategoryDtoMapper;
import com.thiago.abandonedcode.domain.entities.Category;
import com.thiago.abandonedcode.domain.ports.input.CreateCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.input.DeleteCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.input.GetCategoryByPathUseCase;
import com.thiago.abandonedcode.domain.ports.input.GetCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.input.ListCategoriesUseCase;
import com.thiago.abandonedcode.domain.ports.input.UpdateCategoryUseCase;
import com.thiago.abandonedcode.domain.ports.output.CategoryRepository;
import com.thiago.abandonedcode.presentation.dto.CategoryRequest;
import com.thiago.abandonedcode.presentation.dto.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final ListCategoriesUseCase listCategoriesUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final GetCategoryByPathUseCase getCategoryByPathUseCase;
    private final CategoryRepository categoryRepository;

    public CategoryController(
            CreateCategoryUseCase createCategoryUseCase,
            ListCategoriesUseCase listCategoriesUseCase,
            GetCategoryUseCase getCategoryUseCase,
            UpdateCategoryUseCase updateCategoryUseCase,
            DeleteCategoryUseCase deleteCategoryUseCase,
            GetCategoryByPathUseCase getCategoryByPathUseCase,
            CategoryRepository categoryRepository
    ) {
        this.createCategoryUseCase = createCategoryUseCase;
        this.listCategoriesUseCase = listCategoriesUseCase;
        this.getCategoryUseCase = getCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
        this.getCategoryByPathUseCase = getCategoryByPathUseCase;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest request) {
        Category category = createCategoryUseCase.execute(
                request.name(),
                request.parentId()
        );

        CategoryResponse response = CategoryDtoMapper.toResponse(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> listAll() {
        List<Category> categories = listCategoriesUseCase.execute();
        List<CategoryResponse> responses = categories.stream()
                .map(CategoryDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id) {
        Category category = getCategoryUseCase.execute(id);
        CategoryResponse response = CategoryDtoMapper.toResponse(category);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryRequest request
    ) {
        Category category = updateCategoryUseCase.execute(
                id,
                request.name(),
                request.parentId()
        );
        CategoryResponse response = CategoryDtoMapper.toResponse(category);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteCategoryUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{path:**}")
    public ResponseEntity<CategoryResponse> getByPath(@PathVariable String path) {
        Category category = getCategoryByPathUseCase.execute(path);
        String fullPath = categoryRepository.buildFullPath(category.getId());
        CategoryResponse response = CategoryDtoMapper.toResponse(category, null, fullPath);
        return ResponseEntity.ok(response);
    }
}
