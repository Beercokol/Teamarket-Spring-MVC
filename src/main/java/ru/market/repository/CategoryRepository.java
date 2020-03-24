/*
 * Copyright
 */

package ru.market.repository;

import ru.market.model.category.Category;

public interface CategoryRepository extends MainRepository<Category> {
    /**
     * Возвращает категорию из базы данных, у которой совпадает параметр url.
     */
    Category findByUrl(String url);
    /**
     * Удаляет категрию из базы даных, у которой совпадает поле url.
     */
    void deleteByUrl(String url);
}
