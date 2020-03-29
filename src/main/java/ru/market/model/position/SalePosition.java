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
     * Товар текущей торговой позици.
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id",
            nullable = false
    )
    private Product product;

    /**
     * Количество товаров в текущей торговой позиции.
     */
    @Column(
            name = "number",
            nullable = false
    )
    private int number = 0;

    /**
     * Заказ, к которому относится текущая торговая позиция
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

    /**
     * Возвращает описание торговой позиции.
     */
    @Override
    public String toString() {
        return "SalePosition #" + getId()
                + ":\n" + this.product.getTitle()
                + "\n№ " + this.product.getId()
                + ", " + this.product.getPrice() + " UAH"
                + "\nNumber = " + this.number
                + "\nPrice = " + getPrice();
    }

    /**
     * Сравнивает текущий объект с объектом переданым как параметр.
     */
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

    /**
     * Возвращает хеш код объекта.
     */
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
     * Увеличивает количество товаров в позиции на 1.
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
     * Если входной параметр меньше 0, тогда значение номера будет 0.
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
