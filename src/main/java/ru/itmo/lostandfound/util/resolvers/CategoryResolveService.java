package ru.itmo.lostandfound.util.resolvers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.lostandfound.exceptions.NoSuchCategoryException;
import ru.itmo.lostandfound.model.Category;
import ru.itmo.lostandfound.repositories.CategoryRepository;

/**
 * Сервис для получения сущностей категорий предметов по их названиям
 */
@Service
@RequiredArgsConstructor
public class CategoryResolveService implements ResolveService<String, Category> {

    private final CategoryRepository repository;

    public Category resolve(String categoryName) {
        return repository.findByName(categoryName).orElseThrow(() -> new NoSuchCategoryException(categoryName));
    }
}
