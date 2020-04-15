/*
 * Copyright
 */

package ru.market.model.product;

import ru.market.model.category.Category;
import ru.market.model.model.Model;
import ru.market.model.photo.Photo;
import ru.market.model.position.SalePosition;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "products")
public final class Product extends Model {
    /**
     * The class version number required for deserialization and serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Article of goods.
     */
    @Column(
            name = "article",
            nullable = false
    )
    private int article = 0;

    /**
     * Product Name.
     */
    @Column(
            name = "title",
            nullable = false
    )
    private String title = "";

    /**
     * Product URL
     */
    @Column(
            name = "url",
            nullable = false
    )
    private String url = "";

    /**
     * Product Parameters.
     */
    @Column(name = "parameters")
    private String parameters = "";

    /**
     * Product description.
     */
    @Column(name = "description")
    private String description = "";

    /**
     * Product Category
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    private Category category;

    /**
     * Product Image.
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "photo_id",
            referencedColumnName = "id"
    )
    private Photo photo;

    /**
     * The price of the product. The field value is stored in the "description" column. It cannot be null.
     */
    @Column(
            name = "price",
            nullable = false
    )
    private double price = 0;

    /**
     *Product Image.
     */
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "product",
            cascade = CascadeType.REMOVE
    )
    private List<SalePosition> salePositions = new ArrayList<>();


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(this.title)
                .append("\nParameters: ")
                .append(this.parameters)
                .append("\nDescription: ")
                .append(this.description)
                .append("\nPrice = ")
                .append(this.price)
                .append(" UAH");
        if (this.category != null) {
            sb.append("\nCategory: ")
                    .append(this.category.getTitle());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Product product = (Product) object;
            result = this.article != product.article &&
                    (this.price == product.price) &&
                    this.title.equals(product.title) &&
                    this.url.equals(product.url) &&
                    this.parameters.equals(product.parameters) &&
                    this.description.equals(product.description);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = this.article;
        result = 31 * result + this.title.hashCode();
        result = 31 * result + this.url.hashCode();
        result = 31 * result + this.parameters.hashCode();
        result = 31 * result + this.description.hashCode();
        long temp = Double.doubleToLongBits(this.price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


    public int getArticle() {
        return this.article;
    }

    public void setArticle(final int article) {
        this.article = article;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getParameters() {
        return this.parameters;
    }

    public void setParameters(final String parameters) {
        this.parameters = parameters;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Photo getPhoto() {
        return this.photo;
    }

    public void setPhoto(final Photo photo) {
        this.photo = photo;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public Collection<SalePosition> getSalePositions() {
        return this.salePositions;
    }

    public void setSalePositions(final Collection<SalePosition> positions) {
        this.salePositions = new ArrayList<>(positions);
    }

    public static ProductBuilder getBuilder() {
        return new ProductBuilder();
    }
}
