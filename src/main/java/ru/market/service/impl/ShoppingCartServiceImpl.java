/*
 * Copyright
 */

package ru.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.market.model.basket.ShoppingCart;
import ru.market.model.position.SalePosition;
import ru.market.repository.ShoppingCartRepository;
import ru.market.service.interfaces.ShoppingCartService;

import java.util.Collection;
import java.util.List;

import static ru.market.util.validator.ObjectValidator.*;

@Service
@ComponentScan(basePackages = "ru.market.repository")
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;


    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ShoppingCartServiceImpl(final ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    /**
     * Возвращает объект корзину.
     * Режим только для чтения.
     *
     * @return Объект класса {@link ShoppingCart} - торговая корзина.
     * @throws NullPointerException Бросает исключение, если корзина отсутствует.
     */
    @Override
    @Transactional(readOnly = true)
    public ShoppingCart getShoppingCart() throws NullPointerException {
        final ShoppingCart shoppingCart = this.shoppingCartRepository.get();
        if (isNull(shoppingCart)) {
            throw new NullPointerException("Can't find shopping cart!");
        }
        return shoppingCart;
    }

    /**
     * Добавляет торговую позицию в список корзины.
     *
     * @param position Торговая позиция,
     *                 которая будет добавлена в корзину.
     */
    @Override
    @Transactional
    public void add(final SalePosition position) {
        if (isNotNull(position)) {
            this.shoppingCartRepository.addSalePosition(position);
        }
    }

    /**
     * Возвращает список всех торговых позиций в корзине.
     * Режим только для чтения.
     *
     * @return Объект типа {@link List} - список торговых позиций.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<SalePosition> getSalePositions() {
        return this.shoppingCartRepository.getSalePositions();
    }

    /**
     * Удаляет торговую позицию из корзины.
     *
     * @param position Торговая позиция для удаления из корзины.
     */
    @Override
    @Transactional
    public void remove(final SalePosition position) {
        if (isNotNull(position)) {
            this.shoppingCartRepository.removeSalePosition(position);
        }
    }

    /**
     * Очищает корзину.
     * Удаляет все торговые позиции в корзине.
     */
    @Override
    @Transactional
    public void clear() {
        this.shoppingCartRepository.clearSalePositions();
    }

    /**
     * Возвращает цену корзины - цена всех продаж.
     * Режим только для чтения.
     *
     * @return Значение типа double - цена корзины.
     */
    @Override
    @Transactional(readOnly = true)
    public double getPrice() {
        return this.shoppingCartRepository.getPrice();
    }

    /**
     * Возвращает размер корзины, то есть количество товаров в корзине.
     * Режим только для чтения.
     *
     * @return Значение типа int - количество товаров в корзине.
     */
    @Override
    @Transactional(readOnly = true)
    public int getSize() {
        return this.shoppingCartRepository.getSize();
    }
}