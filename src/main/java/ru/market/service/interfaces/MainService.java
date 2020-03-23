package ru.market.service.interfaces;

import ru.market.model.model.Model;

import java.util.Collection;
import java.util.List;

public interface MainService<T extends Model> {
    /**
     * Добавление модели.
     *
     * @param model Модель для добавления.
     */
    void add(T model);

    /**
     * Добавление список моделей.
     *
     * @param models Список моделей для добавления.
     */
    void add(Collection<T> models);

    /**
     * Обновление существующей модели.
     *
     * @param model Обновленная модель.
     */
    void update(T model);

    /**
     * Получение модели по уникальному коду id.
     *
     * @param id Уникальный код модели.
     * @return Объект класса {@link Model} - модель с кодом id.
     */
    T get(long id);

    /**
     * Получение всех моделей.
     *
     * @return Объект типа {@link List} - список всех моделей.
     */
    Collection<T> getAll();

    /**
     * Удаление модели.
     *
     * @param model Модель для удаления.
     */
    void remove(T model);

    /**
     * Удаление модели по уникальному коду.
     *
     * @param id Код модели.
     */
    void remove(long id);

    /**
     * Удаление коллекции моделей.
     *
     * @param models Коллекция моделей для удаления.
     */
    void remove(Collection<T> models);

    /**
     * Удаление всех моделей.
     */
    void removeAll();
}