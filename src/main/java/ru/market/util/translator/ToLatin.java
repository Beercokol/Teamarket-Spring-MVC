/*
 * Copyright
 */

package ru.market.util.translator;

public interface ToLatin {

    /**
     * Translates value from cyrillic to latin.
     * If value is blank then returns null.
     *
     * @return The translated string or null.
     */
    String fromCyrillic();
}