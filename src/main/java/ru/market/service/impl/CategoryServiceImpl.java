/*
 * Copyright
 */

package ru.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.market.model.category.Category;
import ru.market.repository.CategoryRepository;
import ru.market.service.interfaces.CategoryService;

import static ru.market.util.validator.ObjectValidator.*;

@Service
@ComponentScan(basePackages = "ru.market.repository")
public class CategoryServiceImpl extends MainServiceImpl<Category> implements CategoryService {

    private final CategoryRepository repository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    //т.к. бин существует
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public CategoryServiceImpl(final CategoryRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает категорию из базы данных, у которой совпадает параметр url.
     * Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public Category get(final String url) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(url)) {
            throw new IllegalArgumentException("No category URL!");
        }
        final Category category = this.repository.findByUrl(url);
        if (isNull(category)) {
            throw new NullPointerException("Can't find category by url " + url + "!");
        }
        return category;
    }

    /**
     * Удаляет категoрию из базы даных, у которого совпадает поле url.
     */
    @Override
    @Transactional
    public void remove(final String url) {
        if (isNotEmpty(url)) {
            this.repository.deleteByUrl(url);
        }
    }
}