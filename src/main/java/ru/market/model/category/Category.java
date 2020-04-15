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
     * The class version number required for deserialization and serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Name of category. Field value is saved
     * in the "title" column. It cannot be null.
     */
    @Column(name = "title", nullable = false)
    private String title = "";

    /**
     * Category URL Field value is saved
     * in the url column. It cannot be null.
     */
    @Column(name = "url", nullable = false)
    private String url = "";

    /**
     * Description of the category.
     * The field value is stored in the "description" column.
     */
    @Column(name = "description")
    private String description = "";

    /**
     *Image category. Field value (id of photo object) is saved
     * in the column "photo_id". Between Class Objects {@link Category} and {@link Photo}
     * one-to-one communication.
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;

    /**
     * List of products that belong to this category. To current category
     * can be accessed via the "category" field in the class object {@link Category}.
     */
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "category",
            cascade = CascadeType.ALL
    )
    private Collection<Product> products = new HashSet<>();

    protected Category() {
    }

    @Override
    public String toString() {
        return "Category{" + super.toString() +
                ", title: " + this.title +
                ", url: " + this.url +
                ", description: " + this.description +
                '}';
    }


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

    @Override
    public int hashCode() {
        int result = this.title.hashCode();
        result = 31 * result + this.url.hashCode();
        result = 31 * result + this.description.hashCode();
        return result;
    }

    /**
     * Adds a product to the list of the current category.
     *
     * @param product Product to be added to the current category.
     */
    public void addProduct(final Product product) {
        if (isNotNull(product)) {
            this.products.add(product);
        }
    }

    /**
     *Adds a list of products to the list of the current category.
     *    
     *  @param products List of products to be added
     * to the current category.
     */
    public void addProducts(final Collection<Product> products) {
        if (isNotEmpty(products)) {
            this.products.addAll(products);
        }
    }

    /**
     *Removes a product from the list of the current category.
     *  
     * @param product Product to be removed from this category.
     */
    public void removeProduct(final Product product) {
        if (isNotNull(product)) {
            this.products.remove(product);
        }
    }

    /**
     * Deletes the list of products from the list of the current category.
     *  
     *  @param products List of products that will be removed from the current category.
     */
    public void removeProducts(final Collection<Product> products) {
        if (isNotEmpty(products)) {
            this.products.removeAll(products);
        }
    }

    /**
     * Clears the product list .
     */
    public void clearProducts() {
        this.products.clear();
    }

    /**
     * Converts a list of products of this category to a list only
     * for readings and returns it.
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
