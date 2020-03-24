/*
 * Copyright
 */

package ru.market.model.order;

public enum OrderStatus {

    NEW,
    WORK,
    DELIVERY,
    CLOSED,
    REJECTION;

    public String getDescription() {
        return toString();
    }
}
