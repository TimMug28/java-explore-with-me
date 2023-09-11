package ru.practicum.category;

import java.util.List;

public interface CategoryService {
    CategoryDto create(AddingCategoryDto addingCategoryDto);

    CategoryDto update(long categoryId,CategoryDto categoryDto);

    void delete(long id);

    CategoryDto getCategoryById(long id);

    List<CategoryDto> getCategories(int form, int size);


}
