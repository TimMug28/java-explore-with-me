package ru.practicum.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddingCategoryDto {
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
}
