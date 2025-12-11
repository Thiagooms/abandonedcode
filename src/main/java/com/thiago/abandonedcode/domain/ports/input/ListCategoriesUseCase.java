package com.thiago.abandonedcode.domain.ports.input;

import com.thiago.abandonedcode.domain.entities.Category;
import java.util.List;

public interface ListCategoriesUseCase {
    List<Category> execute();
}
