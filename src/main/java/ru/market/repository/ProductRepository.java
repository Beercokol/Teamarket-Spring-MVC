/*
 * Copyright
 */

package ru.market.repository;

import ru.market.model.product.Product;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends MainRepository<Product> {
    /**
     * Возвращает товар из базы данных, у которого совпадает параметр url.
     */
    Product findByUrl(String url);

    /**
     * Возвращает товар из базы даных, у которого совпадает уникальный
     * артикль с значением входящего параметра.
     */
    Product findByArticle(int article);

    /**
     * Удаляет товар из базы данных, у которого совпадает параметр url.
     */
    void deleteByUrl(String url);

    /**
     * Удаляет товар из базы данных, у которого совпадает параметр article.
     */
    void deleteByArticle(int article);

    /**
     * Удаляет товар из базы данных, которые пренадлежат категории
     * с уникальным URL - входным параметром.
     */
    void deleteByCategoryUrl(String url);

    /**
     * Удаляет товар из базы данных, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     */
    void deleteByCategoryId(long id);

    /**
     * Возвращает список товаров, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     */
    Collection<Product> findByCategoryId(long id);

    /**
     * Возвращает список товаров, которые пренадлежат категории
     * с уникальным URL - входным параметром.
     */
    Collection<Product> findByCategoryUrl(String url);
}
