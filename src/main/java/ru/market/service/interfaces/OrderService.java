/*
 * Copyright
 */

package ru.market.service.interfaces;

import ru.market.model.order.Order;

public interface OrderService extends MainService<Order> {
    Order get (String number);

      void remove (String number);
}
