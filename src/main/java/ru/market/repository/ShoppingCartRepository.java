/*
 * Copyright
 */

package ru.market.repository;

import ru.market.model.basket.ShoppingCart;
import ru.market.model.position.SalePosition;

import java.util.Collection;
import java.util.List;

public interface ShoppingCartRepository {
    /**
     * Возвращает список всех торговых позиций в корзине.
     */
    Collection<SalePosition> getSalePositions();

    /**
     * Добавляет торговую позицию в список корзины.
     */
    void addSalePosition(SalePosition salePosition);

    /**
     * Удаляет торговую позицию из корзины.
     */
    void removeSalePosition(SalePosition salePosition);

    /**
     * Очищает корзину.
     * Удаляет все торговые позиции в корзине.
     */
    void clearSalePositions();

    /**
     * Возвращает объект-корзину целиком.
     */
    ShoppingCart get();

    /**
     * Возвращает размер корзины, то есть количество товаров в корзине.
     */
    int getSize();

    /**
     * Возвращает цену корзины - цена всех торговых позиций.
     */
    double getPrice();
}