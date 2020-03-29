/*
 * Copyright
 */

package ru.market.repository;

import ru.market.model.order.Order;

public interface OrderRepository extends MainRepository<Order> {
    /**
     * Возвращает заказ из базы даных, у которого совпадает
     * уникальный номером с значением входящего параметра.
     */
    Order findByNumber(String number);

    /**
     * Удаляет заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     */
    void deleteByNumber(String number);
}