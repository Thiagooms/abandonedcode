package com.thiago.abandonedcode.presentation.controllers;

import com.thiago.abandonedcode.application.mappers.CategoryDtoMapper;
import com.thiago.abandonedcode.domain.entities.Category;
import com.thiago.abandonedcode.domain.ports.input.CreateCategoryUseCase;
import com.thiago.abandonedcode.presentation.dto.CategoryRequest;
import com.thiago.abandonedcode.presentation.dto.CategoryResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;

    public CategoryController(CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
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
}
