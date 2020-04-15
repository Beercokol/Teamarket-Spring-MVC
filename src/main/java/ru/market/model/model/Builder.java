/*
 * Copyright
 */

package ru.market.model.model;

/**
 * Interface for assigning each parameter and returning a new builder.
 */
public interface Builder<T> {

    T build();
}