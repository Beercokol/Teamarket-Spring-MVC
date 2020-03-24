/*
 * Copyright
 */

package ru.market.repository;

import ru.market.model.order.Order;

public interface OrderRepository extends MainRepository<Order> {
    /**
     * Возвращает заказ из базы даных, у которого совпадает
     * уникальный номером с значением входящего параметра.
     *
     * @param number Номер заказа для возврата.
     * @return Объект класса {@link Order} - заказ с уникальным номером
     * для возвращения.
     */
    Order findByNumber(String number);

    /**
     * Удаляет заказ из базы даных, у которого совпадает уникальный номером
     * с значением входящего параметра.
     *
     * @param number Номер заказа для удаление.
     */
    void deleteByNumber(String number);
}