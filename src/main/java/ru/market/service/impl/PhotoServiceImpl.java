/*
 * Copyright
 */

package ru.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.market.model.photo.Photo;
import ru.market.repository.PhotoRepository;
import ru.market.service.interfaces.PhotoService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import  static ru.market.util.validator.ObjectValidator.*;
@Service
@ComponentScan(basePackages = "ru.market.repository")
public    class PhotoServiceImpl extends MainServiceImpl<Photo> implements PhotoService {

    /**
     * Реализация интерфейса {@link PhotoRepository}
     * для работы изображений с базой данных.
     */
    private final PhotoRepository repository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     *
     * @param repository Реализация интерфейса {@link PhotoRepository}
     *                   для работы изображений с базой данных.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public PhotoServiceImpl(final PhotoRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает объект-изображение из базы даных, у которого совпадает
     * уникальное название с значением входящего параметра.
     * Режим только для чтения.
     *
     * @param title Название объекта-изображения для возврата.
     * @return Объект класса {@link Photo} - объекта-изображение.
     * @throws IllegalArgumentException Бросает исключение,
     *                                  если пустой входной параметр title.
     * @throws NullPointerException     Бросает исключение,
     *                                  если не найден объект-изображеие с входящим параметром title.
     */
    @Override
    @Transactional(readOnly = true)
    public Photo get(final String title) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(title)) {
            throw new IllegalArgumentException("No photo title!");
        }
        final Photo photo = this.repository.findByTitle(title);
        if (isNull(photo)) {
            throw new NullPointerException("Can't find photo by title " + title + "!");
        }
        return photo;
    }

    /**
     * Удаляет объект-изображение из базы даных, у которого совпадает
     * уникальное название с значением входящего параметра.
     *
     * @param title Название объекта-изображения для удаления.
     */
    @Override
    @Transactional
    public void remove(final String title) {
        if (isNotEmpty(title)) {
            this.repository.deleteByTitle(title);
        }
    }

    /**
     * Сохраняет файл в файловой системе.
     *
     * @param photo Файл для сохранения.
     */
    @Override
    @Transactional
    public void saveFile(final MultipartFile photo) {
        if (isNotEmpty(photo)) {
            final String filePath = Photo.PATH + photo.getOriginalFilename();
            try (OutputStream stream = new FileOutputStream(filePath)) {
                stream.write(photo.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Удаляет файл по url.
     *
     * @param url URL файла для удаления.
     */
    @Override
    @Transactional
    public void deleteFile(final String url) {
        if (isNotEmpty(url)) {
            final File file = new File(Photo.PATH + url);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        }
    }
}