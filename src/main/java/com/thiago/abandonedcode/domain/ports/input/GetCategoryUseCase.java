package com.thiago.abandonedcode.domain.ports.input;

import com.thiago.abandonedcode.domain.entities.Category;

public interface GetCategoryUseCase {
    Category execute(Long id);
}
