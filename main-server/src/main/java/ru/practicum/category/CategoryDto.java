package ru.practicum.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class CategoryDto {
    private Long id;
    @Size(min = 1, max = 50)
    private String name;
}
