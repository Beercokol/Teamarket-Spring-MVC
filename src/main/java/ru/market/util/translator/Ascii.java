package ru.market.util.translator;

public interface Ascii {

    /**
     * Translates value to ASCII.
     * If value is blank then returns empty string.
     *
     * @return The translated string or empty string.
     */
    String to();

    /**
     * Translates value from ASCII.
     * If value is blank then returns empty string.
     *
     * @return The translated string or empty string.
     */
    String from();
}