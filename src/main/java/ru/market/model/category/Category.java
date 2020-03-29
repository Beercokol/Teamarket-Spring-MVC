/*
 * Copyright
 */

package ru.market.model.category;

import ru.market.model.model.Model;
import ru.market.model.photo.Photo;
import ru.market.model.product.Product;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static ru.market.util.validator.ObjectValidator.*;


@Entity
@Table(name = "categories")
public final class Category extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Название категории. Значение поля сохраняется
     * в колонке "title". Не может быть null.
     */
    @Column(name = "title", nullable = false)
    private String title = "";

    /**
     * URL категории. Значение поля сохраняется
     * в колонке "url". Не может быть null.
     */
    @Column(name = "url", nullable = false)
    private String url = "";

    /**
     * Описание категории.
     * Значение поля сохраняется в колонке "description".
     */
    @Column(name = "description")
    private String description = "";

    /**
     * Изображение категории. Значение поля (id объекта photo) сохраняется
     * в колонке "photo_id". Между объектами классов {@link Category} и {@link Photo}
     * связь один-к-одному.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

    /**
     * Список товаров, которые относятся к данной категории. К текущей категории
     * можно добраться через поле "category" в объекте класса {@link Category}.
     */
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "category",
            cascade = CascadeType.ALL
    )
    private Collection<Product> products = new HashSet<>();

    protected Category() {
    }

    /**
     * Возвращает описание категории.
     */
    @Override
    public String toString() {
        return "Category{" + super.toString() +
                ", title: " + this.title +
                ", url: " + this.url +
                ", description: " + this.description +
                '}';
    }

    /**
     * Сравнивает текущий объект с объектом переданым как параметр.
     * Переопределенный метод родительского класса {@link Object}.
     */
    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Category other = (Category) object;
            result = this.title.equalsIgnoreCase(other.title) &&
                    this.url.equalsIgnoreCase(other.url);
        }
        return result;
    }

    /**
     * Возвращает хеш код объекта.
     * Переопределенный метод родительского класса {@link Object}.
     */
    @Override
    public int hashCode() {
        int result = this.title.hashCode();
        result = 31 * result + this.url.hashCode();
        result = 31 * result + this.description.hashCode();
        return result;
    }

    /**
     * Добавляет товар в список текущей категории.
     *
     * @param product Товар, который будет добавлен в текущую категорию.
     */
    public void addProduct(final Product product) {
        if (isNotNull(product)) {
            this.products.add(product);
        }
    }

    /**
     * Добавляет список товаров в список текущей категории.
     *
     * @param products Список товаров, которые будут добавлены
     *                 в текущую категорию.
     */
    public void addProducts(final Collection<Product> products) {
        if (isNotEmpty(products)) {
            this.products.addAll(products);
        }
    }

    /**
     * Удаляет товар из списка текущей категории.
     *
     * @param product Товар, который будет удален из данной категории.
     */
    public void removeProduct(final Product product) {
        if (isNotNull(product)) {
            this.products.remove(product);
        }
    }

    /**
     * Удаляет список товаров из списка текущей категории.
     *
     * @param products Список товаров, которые будут удалены из текущей категории.
     */
    public void removeProducts(final Collection<Product> products) {
        if (isNotEmpty(products)) {
            this.products.removeAll(products);
        }
    }

    /**
     * Очищает список товаров products.
     */
    public void clearProducts() {
        this.products.clear();
    }

    /**
     * Конвертирует список товаров products данной категории  в список только
     * для чтений и возвращает его.
     */
    public Collection<Product> getProducts() {
        return getUnmodifiableList(this.products);
    }

    public void setProducts(final Collection<Product> products) {
        clearProducts();
        addProducts(products);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = isNotEmpty(title) ? title : "";
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = isNotEmpty(url) ? url : "";
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = isNotEmpty(description) ? description : "";
    }

    public Photo getPhoto() {
        return this.photo;
    }

    public void setPhoto(final Photo photo) {
        this.photo = photo;
    }

    public static CategoryBuilder getBuilder() {
        return new CategoryBuilder();
    }
}
