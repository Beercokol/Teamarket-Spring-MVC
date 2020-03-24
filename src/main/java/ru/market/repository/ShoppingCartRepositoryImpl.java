/*
 * Copyright
 */

package ru.market.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import ru.market.model.basket.ShoppingCart;
import ru.market.model.position.SalePosition;

import java.util.Collection;

@Repository
@ComponentScan(basePackages = "ru.market.model")
public final class ShoppingCartRepositoryImpl implements ShoppingCartRepository {

    private final ShoppingCart shoppingCart;
    /**
     * Конструктор для инициализации основных переменных.
     * Помечаный аннотацией @Autowired, которая позволит Spring
     * автоматически инициализировать объект.
     */
    @Autowired
    public ShoppingCartRepositoryImpl(final ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    /**
     * Возвращает список всех торговых позиций в корзине.
     */
    @Override
    public Collection<SalePosition> getSalePositions() {
        return this.shoppingCart.getSalePositions();
    }

    /**
     * Добавляет торговую позицию в список корзины.
     */
    @Override
    public void addSalePosition(final SalePosition salePosition) {
        this.shoppingCart.addSalePosition(salePosition);
    }

    /**
     * Удаляет торговую позицию из корзины.
     */
    @Override
    public void removeSalePosition(final SalePosition salePosition) {
        this.shoppingCart.removeSalePosition(salePosition);
    }

    /**
     * Очищает корзину.
     * Удаляет все торговые позиции в корзине.
     */
    @Override
    public void clearSalePositions() {
        this.shoppingCart.clearSalePositions();
    }

    /**
     * Возвращает объект-корзину целиком.
     */
    @Override
    public ShoppingCart get() {
        return this.shoppingCart;
    }

    /**
     * Возвращает размер корзины, то есть количество товаров в корзине.
     */
    @Override
    public int getSize() {
        return this.shoppingCart.getSize();
    }

    /**
     * Возвращает цену корзины - цена всех продаж.
     */
    @Override
    public double getPrice() {
        return this.shoppingCart.getPrice();
    }
}