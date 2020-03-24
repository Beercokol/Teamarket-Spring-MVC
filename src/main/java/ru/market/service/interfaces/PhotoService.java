/*
 * Copyright
 */

package ru.market.service.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.market.model.photo.Photo;

public interface PhotoService extends MainService<Photo> {

    Photo get(String title);

    /**
     * Удаляет объект-изображение, у которого совпадает уникальное
     * название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для удаления.
     */
    void remove(String title);

    void saveFile(MultipartFile photo);

    void deleteFile(String url);
}