/*
 * Copyright
 */

package ru.market.service.interfaces;

import ru.market.model.basket.ShoppingCart;
import ru.market.model.position.SalePosition;

import java.util.Collection;

public interface ShoppingCartService {
    /**
     * Получение корзины
     */
    ShoppingCart getShoppingCart();
    /**
     * Добавление в корзину
     */
    void add(SalePosition salePosition);

    Collection<SalePosition> getSalePositions();
    /**
     * Удаление из корзины
     */
    void remove(SalePosition salePosition);
    /**
     * Очистить корзину
     */
    void clear();

    double getPrice();

    int getSize();
}