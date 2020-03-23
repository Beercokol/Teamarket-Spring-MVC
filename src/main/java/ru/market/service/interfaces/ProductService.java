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
     *
     * @param size               Количество товаров в списке.
     * @param categoryId         Код категории, товары которой будут возвращены.
     * @param differentProductId Код товара, который точно не будет включен в список.
     * @return Объект типа {@link List} - список товаров.
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
     *
     * @param size Количество товаров в списке.
     * @param id   Код категории, товары которой будут возвращены.
     * @return Объект типа {@link List} - список товаров.
     */
    Collection<Product> getRandomByCategoryId(int size, long id);

    /**
     * Возвращает список рандомных товаров.
     *
     * @param size Количество товаров в списке.
     * @return Объект типа {@link List} - список товаров.
     */
    Collection<Product> getRandom(int size);

    void removeByUrl(String url);

    void removeByArticle(int article);

    void removeByCategoryUrl(String url);

    void removeByCategoryId(long id);
}