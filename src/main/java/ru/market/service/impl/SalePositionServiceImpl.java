/*
 * Copyright
 */

package ru.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ru.market.model.position.SalePosition;
import ru.market.repository.SalePositionRepository;
import ru.market.service.interfaces.SalePositionService;

@Service
@ComponentScan(basePackages = "ru.market.repository")
public final class SalePositionServiceImpl extends MainServiceImpl<SalePosition> implements SalePositionService {

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public SalePositionServiceImpl(final SalePositionRepository repository) {
        super(repository);
    }
}