/*
 * Copyright
 */

package ru.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.market.model.product.Product;
import ru.market.repository.ProductRepository;
import ru.market.service.interfaces.ProductService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ru.market.util.validator.ObjectValidator.*;

@Service
@ComponentScan(basePackages = "ru.market.repository")
public  class ProductServiceImpl extends MainServiceImpl<Product> implements ProductService {

    private final ProductRepository repository;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public ProductServiceImpl(final ProductRepository repository) {
        super(repository);
        this.repository = repository;
    }

    /**
     * Возвращает товар, у которого совпадает параметр url. Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public Product getByUrl(final String url) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(url)) {
            throw new IllegalArgumentException("No product URL!");
        }
        final Product product = this.repository.findByUrl(url);
        if (isNull(product)) {
            throw new NullPointerException("Can't find product by url " + url + "!");
        }
        return product;
    }

    /**
     * Возвращает товар, у которого совпадает уникальный
     * артикль с значением входящего параметра. Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public Product getByArticle(final int article) throws NullPointerException {
        final Product product = this.repository.findByArticle(article);
        if (isNull(product)) {
            throw new NullPointerException("Can't find product by article " + article + "!");
        }
        return product;
    }

    /**
     * Возвращает список товаров, которые относятся к категории
     * с уникальным URL - входным параметром.
     * Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Product> getByCategoryUrl(final String url)
            throws IllegalArgumentException {
        if (isEmpty(url)) {
            throw new IllegalArgumentException("No category URL!");
        }
        return this.repository.findByCategoryUrl(url);
    }

    /**
     * Возвращает список товаров, которые относятся к категории
     * с уникальным кодом id - входным параметром.
     * Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Product> getByCategoryId(final long id) {
        return this.repository.findByCategoryId(id);
    }

    /**
     * Возвращает список рандомных товаров, которые относятся к категории
     * с уникальным кодом id - входным параметром.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandomByCategoryId(final int size, final long id) {
        return getRandomByCategoryId(size, id, -1L);
    }

    /**
     * Возвращает список рандомных товаров, которые относятся к категории
     * с уникальным кодом id - входным параметром.
     * Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> getRandomByCategoryId(
            final int size,
            final long categoryId,
            final long differentProductId
    ) throws IllegalArgumentException {
        final Collection<Product> products = this.repository.findByCategoryId(categoryId);
        if (isEmpty(products)) {
            return new ArrayList<>();
        }
        products.remove(this.repository.findOne(differentProductId));
        return getShuffleSubList(new ArrayList<>(products), 0, size);
    }

    /**
     * Возвращает список рандомных товаров.
     * Режим только для чтения.
     */
    @Override
    @Transactional(readOnly = true)
    public Collection<Product> getRandom(final int size) {
        final Collection<Product> products = this.repository.findAll();
        if (isNotEmpty(products)) {
            return getShuffleSubList(new ArrayList<>(products), 0, size);
        }
        return new ArrayList<>(products);
    }

    /**
     * Удаляет товар, у которого совпадает параметр url.
     */
    @Override
    @Transactional
    public void removeByUrl(final String url) {
        if (isNotEmpty(url)) {
            this.repository.deleteByUrl(url);
        }
    }

    /**
     * Удаляет товар, у которого совпадает параметр article.
     */
    @Override
    @Transactional
    public void removeByArticle(final int article) {
        this.repository.deleteByArticle(article);
    }

    /**
     * Удаляет товары, которые пренадлежат категории
     * с уникальным URL - входным параметром.
     */
    @Override
    @Transactional
    public void removeByCategoryUrl(final String url) {
        if (isNotEmpty(url)) {
            this.repository.deleteByCategoryUrl(url);
        }
    }

    /**
     * Удаляет товары, которые пренадлежат категории
     * с уникальным кодом - входным параметром.
     */
    @Override
    @Transactional
    public void removeByCategoryId(final long id) throws NullPointerException {
        this.repository.deleteByCategoryId(id);
    }

    /**
     * Возвращает список перемешаных товаров
     * начиная с позиции start и заканчиваю позицеей end.
     */
    private static List<Product> getShuffleSubList(
            final Collection<Product> products,
            final int start,
            final int end
    ) {
        if (isEmpty(products) || (start > products.size()) || (start > end) || (start < 0) || (end < 0)) {
            return new ArrayList<>();
        }
        final List<Product> result = new ArrayList<>(products);
        Collections.shuffle(result);
        return result.subList(start, end <= result.size() ? end : result.size());
    }
}