package com.thiago.abandonedcode.domain.ports.input;

import com.thiago.abandonedcode.domain.entities.Category;

public interface CreateCategoryUseCase {
    Category execute(String name, Long parentId);
}
