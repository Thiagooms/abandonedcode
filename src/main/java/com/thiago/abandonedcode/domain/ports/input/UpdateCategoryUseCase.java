package com.thiago.abandonedcode.domain.ports.input;

import com.thiago.abandonedcode.domain.entities.Category;

public interface UpdateCategoryUseCase {
    Category execute(Long id, String name, Long parenteId);
}

