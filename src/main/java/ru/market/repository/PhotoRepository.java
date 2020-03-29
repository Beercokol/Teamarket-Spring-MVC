/*
 * Copyright
 */

package ru.market.repository;

import ru.market.model.photo.Photo;

public interface PhotoRepository extends MainRepository<Photo> {
    /**
     * Возвращает объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     */
    Photo findByTitle(String title);

    /**
     * Удаляет объект-изображение из базы даных, у которого совпадает уникальное
     * название с значением входящего параметра.
     */
    void deleteByTitle(String title);
}
