package ru.practicum.category;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.exceptions.ConflictException;
import ru.practicum.exceptions.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto getCategoryById(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Не найдена категория с id=%d.", id)));
        return Mapper.toDto(category);
    }

    @Override
    public List<CategoryDto> getCategories(int from, int size) {
        PageRequest pageRequest = PageRequest.of(from, size);
        return Mapper.toListDto(categoryRepository.findAll(pageRequest).toList());
    }

    @Override
    public CategoryDto create(AddingCategoryDto addingCategoryDto) {
        try {
            return Mapper.toDto(
                    categoryRepository.save(Mapper.toAddDto(addingCategoryDto)));
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Ошибка");
        }
    }

    @Override
    public CategoryDto update(long id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new NotFoundException(String.format("Не найдена категория с id=%d.", id));
        }
        Category updatedCategory = optionalCategory.get();
        updatedCategory.setName(categoryDto.getName());
        try {
            categoryRepository.save(updatedCategory);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Ошибка при обновлении: нарушение ограничений целостности данных");
        } catch (Exception e) {
            throw new RuntimeException("Не удалось обновить категорию", e);
        }
        return Mapper.toDto(updatedCategory);
    }

    @Override
    public void delete(long id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException(String.format("Не найдена категория с id=%d.", id));
        }
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new ConflictException("Ошибка");
        }
    }
}

