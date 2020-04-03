/*
 * Copyright
 */

package ru.market.service.interfaces;

import ru.market.model.category.Category;

public interface CategoryService extends MainService<Category> {
    /**
     * Возвращает категорию, у которой совпадает параметр url.
     */
    Category get(String url);

    /**
     * Удаляет категoрию, у которого совпадает поле url.
     */
    void remove(String url);
}