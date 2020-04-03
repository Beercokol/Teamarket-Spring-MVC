/*
 * Copyright
 */

package ru.market.model.user;

public enum UserRole {
    CLIENT,
    ADMIN;
    public String getDescription() {
        return toString();
    }
}