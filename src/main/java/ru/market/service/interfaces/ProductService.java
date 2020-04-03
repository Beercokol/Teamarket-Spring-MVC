/*
 * Copyright
 */

package ru.market.service.interfaces;

import ru.market.model.product.Product;

import java.util.Collection;

public interface ProductService extends MainService<Product> {

    Product getByUrl(String url);

    Product getByArticle(int article);

    Collection<Product> getByCategoryUrl(String url);

    Collection<Product> getByCategoryId(long id);

    /**
     * Возвращает список рандомных товаров, которые относятся к категории
     * с уникальным кодом id - входным параметром.
     */
    Collection<Product> getRandomByCategoryId(
            int size,
            long categoryId,
            long differentProductId
    );

    /**
     * Возвращает список рандомных товаров,
     * которые относятся к категории с уникальным
     * кодом id - входным параметром.
     */
    Collection<Product> getRandomByCategoryId(int size, long id);

    /**
     * Возвращает список рандомных товаров.
     */
    Collection<Product> getRandom(int size);

    void removeByUrl(String url);

    void removeByArticle(int article);

    void removeByCategoryUrl(String url);

    void removeByCategoryId(long id);
}