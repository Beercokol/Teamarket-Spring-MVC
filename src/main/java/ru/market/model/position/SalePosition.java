/*
 * Copyright
 */

package ru.market.model.position;

import ru.market.model.model.Model;
import ru.market.model.order.Order;
import ru.market.model.product.Product;

import javax.persistence.*;

import static ru.market.util.validator.ObjectValidator.*;



@Entity
@Table(name = "sales")
public class SalePosition extends Model {

    /**
     *Product of the current trading position.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Product product;

    /**
     *The number of products in the current trading position.
     */
    @Column(
            name = "number",
            nullable = false
    )
    private int number = 0;

    /**
     * Order to which the current sales item belongs
     */
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Order order;

    @Override
    public String toString() {
        return "SalePosition #" + getId()
                + ":\n" + this.product.getTitle()
                + "\n№ " + this.product.getId()
                + ", " + this.product.getPrice() + " UAH"
                + "\nNumber = " + this.number
                + "\nPrice = " + getPrice();
    }

    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final SalePosition position = (SalePosition) object;
            result = (this.number == position.number);
            if (isNotNull(this.product)) {
                result &= this.product.equals(position.product);
            } else {
                result &= isNull(position.product);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = isNotNull(this.product) ? this.product.hashCode() : 0;
        result = 31 * result + this.number;
        return result;
    }


    public double getPrice() {
        return this.number * this.product.getPrice();
    }

    /**
     * Increases the number of items in the position by 1.
     */
    public void numberIncrement() {
        this.number++;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(final Product product) {
        this.product = product;
        this.number = isNotNull(product) ? 1 : 0;
    }

    public int getNumber() {
        return this.number;
    }

    /**
     * If the input parameter is less than 0, then the number will be 0.
     */
    public void setNumber(final int number) {
        this.number = (number > 0) ? number : 0;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }
}
