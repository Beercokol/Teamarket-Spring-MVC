package ru.market.repository;

import ru.market.model.photo.Photo;

public interface PhotoRepository extends MainRepository<Photo> {
    /**
     * Возвращает объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для возврата.
     * @return Объект класса {@link Photo} - объект-изображение.
     */
    Photo findByTitle(String title);

    /**
     * Удаляет объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для удаление.
     */
    void deleteByTitle(String title);
}
