package ru.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.market.model.order.Order;
import ru.market.repository.OrderRepository;
import ru.market.service.interfaces.OrderService;

import static ru.market.util.validator.ObjectValidator.*;

@Service
@ComponentScan(basePackages = "ru.market.repository")
public class OrderServiceImpl extends MainServiceImpl<Order>  implements OrderService{

    private final OrderRepository repository;

    /**
     * Конструктор для инициализации основных переменных сервиса.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public OrderServiceImpl(final OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает заказ из базы даных, у которого совпадает
     * уникальный номером с значением входящего параметра.
     * Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public Order get(final String number) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(number)) {
            throw new IllegalArgumentException("No orderEntity number!");
        }
        final Order orderEntity = this.repository.findByNumber(number);
        if (isNull(orderEntity)) {
            throw new NullPointerException("Can't find orderEntity by number " + number + "!");
        }
        return orderEntity;
    }

    /**
     * Удаляет заказ из базы даных, у которого совпадает
     * уникальный номером с значением входящего параметра.
     */
    @Override
    @Transactional
    public void remove(final String number) {
        if (isNotEmpty(number)) {
            this.repository.deleteByNumber(number);
        }
    }
}