/*
 * Copyright
 */

package ru.market.service.interfaces;

import ru.market.model.basket.ShoppingCart;
import ru.market.model.position.SalePosition;

import java.util.Collection;

public interface ShoppingCartService {

    ShoppingCart getShoppingCart();

    void add(SalePosition salePosition);

    Collection<SalePosition> getSalePositions();

    void remove(SalePosition salePosition);

    void clear();

    double getPrice();

    int getSize();
}