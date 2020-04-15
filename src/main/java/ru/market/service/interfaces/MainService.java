/*
 * Copyright
 */

package ru.market.service.interfaces;

import ru.market.model.model.Model;

import java.util.Collection;
import java.util.List;

public interface MainService<T extends Model> {
    /**
     * Добавление модели.
     */
    void add(T model);

    /**
     * Добавление список моделей.
     */
    void add(Collection<T> models);

    /**
     * Обновление существующей модели.
     */
    void update(T model);

    /**
     * Получение модели по уникальному коду id.
     */
    T get(long id);

    /**
     * Получение всех моделей.
     */
    Collection<T> getAll();

    /**
     * Удаление модели.
     */
    void remove(T model);

    /**
     * Удаление модели по уникальному коду.
     */
    void remove(long id);

    /**
     * Удаление коллекции моделей.
     */
    void remove(Collection<T> models);

    /**
     * Удаление всех моделей.
     */
    void removeAll();
}