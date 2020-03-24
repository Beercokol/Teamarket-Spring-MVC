/*
 * Copyright
 */

package ru.market.model.order;


import ru.market.model.model.Model;
import ru.market.model.position.SalePosition;
import ru.market.model.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static ru.market.util.validator.ObjectValidator.isNotEmpty;
import static ru.market.util.validator.ObjectValidator.isNotNull;

@Entity
@Table(name = "orders")
public final class Order extends Model {
    /**
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    @Column(
            name = "number",
            nullable = false
    )
    private String number = "";

    @Column(
            name = "date",
            nullable = false
    )
    private Date date = new Date();

    @Column(name = "shipping_address")
    private String shippingAddress = "";

    @Column(name = "shipping_details")
    private String shippingDetails = "";

    @Column(name = "description")
    private String description = "";

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

    /**
     * Клиент, оформивший заказ.
     */
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "id"
    )
    private User client;

    /**
     * Менеджер, обработавший заказ. Значение поля (id объекта manager)
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "manager_id",
            referencedColumnName = "id"
    )
    private User manager;

    /**
     * Список торговых позиция текущего заказу.
     */
    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "order",
            cascade = CascadeType.ALL
    )
    private Collection<SalePosition> salePositions = new ArrayList<>();

    protected Order() {
    }

    /**
     * Возвращает описание заказа.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.number).append(", ")
                .append(this.status.name())
                .append(",\n").append(this.date);
        if (isNotNull(this.client)) {
            sb.append("\n\nClient: ")
                    .append(this.client.getName())
                    .append("\ne-mail: ")
                    .append(this.client.getEmail())
                    .append("\nphone: ")
                    .append(this.client.getPhone())
                    .append("\n");
        }
        if (isNotNull(this.manager)) {
            sb.append("\n")
                    .append(this.manager.getRole().name())
                    .append(" ")
                    .append(this.manager.getName())
                    .append("\n");
        }
        if (isNotEmpty(this.shippingAddress)) {
            sb.append("\nShipping address: ")
                    .append(this.shippingAddress);
        }
        if (isNotEmpty(this.shippingDetails)) {
            sb.append("\nShipping details: ")
                    .append(this.shippingDetails);
        }
        if (isNotEmpty(this.description)) {
            sb.append("\nDescription: ")
                    .append(this.description);
        }
        if (isNotEmpty(this.salePositions)) {
            sb.append("\nSale Positions: ");
            int count = 1;
            for (SalePosition salePosition : this.salePositions) {
                sb.append("\n")
                        .append(count++)
                        .append(") ")
                        .append(salePosition.getProduct().getTitle())
                        .append(", № ")
                        .append(salePosition.getProduct().getId())
                        .append(",\n")
                        .append(salePosition.getNumber())
                        .append(" x ")
                        .append(salePosition.getProduct().getPrice())
                        .append(" = ")
                        .append(salePosition.getPrice())
                        .append(" UAH;");
            }
            sb.append("\n\nPRICE = ")
                    .append(getPrice())
                    .append(" UAH");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        boolean result = super.equals(object);
        if (result) {
            final Order orderEntity = (Order) object;
            result = this.number.equals(orderEntity.number) &&
                    this.date.equals(orderEntity.date) &&
                    this.shippingAddress.equals(orderEntity.shippingAddress) &&
                    this.shippingDetails.equals(orderEntity.shippingDetails) &&
                    this.description.equals(orderEntity.description);
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = this.number.hashCode();
        result = 31 * result + this.date.hashCode();
        result = 31 * result + this.shippingAddress.hashCode();
        result = 31 * result + this.shippingDetails.hashCode();
        result = 31 * result + this.description.hashCode();
        return result;
    }

    /**
     * Добавляет торговую позицию в текущий заказа.
     */
    public void addSalePosition(final SalePosition position) {
        if (isNotNull(position)) {
            this.salePositions.add(position);
            if (position.getOrder() != this) {
                position.setOrder(this);
            }
        }
    }

    /**
     * Добавляет список торговых позиций в текущий заказ.
     */
    public void addSalePositions(final Collection<SalePosition> positions) {
        if (isNotEmpty(positions)) {
            positions.forEach(this::addSalePosition);
        }
    }

    /**
     * Удаляет торговую позицию из текущего заказа.
     */
    public void removeSalePosition(final SalePosition position) {
        if (isNotNull(position)) {
            this.salePositions.remove(position);
        }
    }

    /**
     * Удаляет список торговых позиция из текущего заказа.
     */
    public void removeSalePositions(final Collection<SalePosition> positions) {
        if (isNotEmpty(positions)) {
            this.salePositions.removeAll(positions);
        }
    }

    /**
     * Очищает список торговых позиция текущего заказа.
     */
    public void clearSalePositions() {
        this.salePositions.clear();
    }

    public Collection<SalePosition> getSalePositions() {
        return new ArrayList<>(this.salePositions);
    }

    public void setSalePositions(final Collection<SalePosition> positions) {
        this.salePositions = positions;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(final OrderStatus status) {
        this.status = status;
    }

    public User getClient() {
        return this.client;
    }

    public void setClient(final User client) {
        this.client = client;
    }

    public User getManager() {
        return this.manager;
    }

    public void setManager(final User manager) {
        this.manager = manager;
    }

    public String getShippingAddress() {
        return this.shippingAddress;
    }

    public void setShippingAddress(final String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingDetails() {
        return this.shippingDetails;
    }

    public void setShippingDetails(final String shippingDetails) {
        this.shippingDetails = shippingDetails;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Возвращает цену заказа - общую стоимость всех торговых позиция.
     */
    public double getPrice() {
        double price = 0;
        for (SalePosition salePosition : this.salePositions) {
            price += salePosition.getPrice();
        }
        return price;
    }

    public static OrderBuilder getBuilder() {
        return new OrderBuilder();
    }
}