/*
 * Copyright
 */

package ru.market.model.user;

public enum UserRole {
    CLIENT,
    ADMIN,
    MANAGER;
    public String getDescription() {
        return toString();
    }
}