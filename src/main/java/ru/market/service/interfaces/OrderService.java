/*
 * Copyright
 */

package ru.market.service.interfaces;

import ru.market.model.order.Order;

public interface OrderService extends MainService<Order> {
    /**
     * Получение модели по номеру
     */
    Order get (String number);
    /**
     * Удаление модели по номеру
     */
      void remove (String number);
}
