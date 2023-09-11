package ru.practicum.category;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mapper {
    public static CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category toAddDto(AddingCategoryDto addingCategoryDto) {
        return Category.builder()
                .name(addingCategoryDto.getName())
                .build();
    }

    public static List<CategoryDto> toListDto(List<Category> categories) {
        return categories.stream()
                .map(Mapper::toDto)
                .collect(Collectors.toList());
    }
}
