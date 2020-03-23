package ru.market.service.impl;
import org.springframework.transaction.annotation.Transactional;
import ru.market.model.model.Model;
import ru.market.repository.MainRepository;
import ru.market.service.interfaces.MainService;

import java.util.Collection;

import static ru.market.util.validator.ObjectValidator.*;

public abstract class MainServiceImpl<T extends Model>
        implements MainService<T> {
    /**
     * Реализация интерфейса {@link MainRepository}
     * для работы моделей с базой данных.
     */
    private final MainRepository<T> repository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     */
    public MainServiceImpl(final MainRepository<T> repository) {
        super();
        this.repository = repository;
    }

    /**
     * Добавление модели в базу данных.
     */
    @Override
    @Transactional
    public void add(final T model) {
        if (isNotNull(model)) {
            this.repository.save(model);
        }
    }

    /**
     * Добавление коллекции моделей в базу данных.
     */
    @Override
    @Transactional
    public void add(final Collection<T> models) {
        if (isNotEmpty(models)) {
            this.repository.save(models);
        }
    }

    /**
     * Обновление существующей модели в базе данных.
     */
    @Override
    @Transactional
    public void update(final T model) {
        add(model);
    }

    /**
     * Получение модели по уникальному коду id в базе данных.
     * Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public T get(final long id) throws NullPointerException {
        final T model = this.repository.findOne(id);
        if (isNull(model)) {
            throw new NullPointerException("Can't find model by id " + id + "!");
        }
        return model;
    }

    /**
     * Получение всех моделей из базы данных.
     * Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<T> getAll() {
        return this.repository.findAll();
    }

    /**
     * Удаление модели из базы данных.
     */
    @Override
    @Transactional
    public void remove(final T model) {
        if (isNotNull(model)) {
            this.repository.delete(model);
        }
    }

    /**
     * Удаление модели из базы данных по уникальному коду.
     */
    @Override
    @Transactional
    public void remove(final long id) {
        this.repository.delete(id);
    }

    /**
     * Удаление коллекции моделей из базы данных.
     */
    @Override
    @Transactional
    public void remove(final Collection<T> models) {
        if (isNotEmpty(models)) {
            this.repository.delete(models);
        }
    }

    /**
     * Удаление всех моделей из базы данных.
     */
    @Override
    @Transactional
    public void removeAll() {
        this.repository.deleteAll();
    }
}