/*
 * Copyright
 */

package ru.market.model.basket;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.market.model.position.SalePosition;

import static ru.market.util.validator.ObjectValidator.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS
)
public class ShoppingCart implements Serializable {
    /**
     * The class version number required for deserialization and serialization.
     */
    private static final long serialVersionUID = 1L;

    private  final List<SalePosition> salePositions = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Shopping Cart: ");
        if (this.salePositions != null && !this.salePositions.isEmpty()) {
            int count = 1;
            for (SalePosition salePosition : this.salePositions) {
                sb.append("\n")
                        .append(count++)
                        .append(") ").append(salePosition.getProduct().getTitle())
                        .append("\n№ ")
                        .append(salePosition.getProduct().getId())
                        .append(", ")
                        .append(salePosition.getPrice())
                        .append(" UAH");
            }
            sb.append("\nPrice: ")
                    .append(getPrice())
                    .append(" UAH");
        } else {
            sb.append("is empty!");
        }
        return sb.toString();
    }

    /**
     * Adds a trading position to the basket list.
     */
    public void addSalePosition(final SalePosition position) {
        if (isNotNull(position)) {
            if (!this.salePositions.contains(position)) {
                this.salePositions.add(position);
            } else {
                final int index = this.salePositions.indexOf(position);
                this.salePositions.get(index).numberIncrement();
            }
        }
    }

    /**
     * Adds a list of sales items to the basket list.
     */
    public void addSalePositions(final Collection<SalePosition> positions) {
        if (isNotEmpty(positions)) {
            positions.forEach(this::addSalePosition);
        }
    }

    /**
     * Removes a trade item from the basket.
     */
    public void removeSalePosition(final SalePosition position) {
        if (isNotNull(position)) {
            this.salePositions.remove(position);
        }
    }

    /**
     * Deletes a shopping list item from the basket.
     */
    public void removeSalePositions(final Collection<SalePosition> positions) {
        if (isNotEmpty(positions)) {
            this.salePositions.removeAll(positions);
        }
    }

    /**
     * Clears the basket.
     */
    public void clearSalePositions() {
        this.salePositions.clear();
    }

    /**
     * Returns a list of all trading positions in the basket.
     * The method converts a list of trading positions
     * The basket is in the read-only list and returns it.
     */
    public Collection<SalePosition> getSalePositions() {
        final Collection<SalePosition> positions;
        if (isNotEmpty(this.salePositions)) {
            positions = new ArrayList<>(this.salePositions);
        } else {
            positions = new ArrayList<>();
        }
        return positions;
    }

    /**
     * Sets the list of sales items.
     */
    public void setSalePositions(final Collection<SalePosition> positions) {
        clearSalePositions();
        addSalePositions(positions);
    }

    /**
     * Returns the price of the basket - the price of all trading positions.
     */
    public double getPrice() {
        double price = 0;
        for ( final SalePosition salePosition : this.salePositions) {
            price += salePosition.getPrice();
        }
        return price;
    }

    /**
     * Returns the size of the basket - the number of products in the basket.
     */
    public int getSize() {
        int size = 0;
        for ( final SalePosition salePosition : this.salePositions) {
            size += salePosition.getNumber();
        }
        return size;
    }
}