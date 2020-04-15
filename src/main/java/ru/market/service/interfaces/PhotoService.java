/*
 * Copyright
 */

package ru.market.service.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.market.model.photo.Photo;

public interface PhotoService extends MainService<Photo> {
    /**
     * Получает объект-изображение, у которого совпадает уникальное
     * название с значением входящего параметра.
     */

    Photo get(String title);

    /**
     * Удаляет объект-изображение, у которого совпадает уникальное
     * название с значением входящего параметра.
     */
    void remove(String title);


    void saveFile(MultipartFile photo);

    /**
     * Удаляет объект-изображение, у которого совпадает URL
     *  с значением входящего параметра.
     */

    void deleteFile(String url);
}