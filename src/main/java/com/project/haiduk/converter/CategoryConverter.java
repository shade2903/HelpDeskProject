package com.project.haiduk.converter;

import com.project.haiduk.domain.Category;
import com.project.haiduk.dto.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter extends AbstractConverter<Category, CategoryDto> {
    @Override
    Class<CategoryDto> getDomainClass() {
        return CategoryDto.class;
    }

    @Override
    Class<Category> getEntityClass() {
        return Category.class;
    }
}
