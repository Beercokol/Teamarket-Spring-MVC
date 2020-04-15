/*
 * Copyright
 */

package ru.market.model.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static ru.market.util.validator.ObjectValidator.isNotEmpty;
import static ru.market.util.validator.ObjectValidator.isNotNull;


@MappedSuperclass
public abstract class Model implements Serializable {
    /**
     * The class version number required for deserialization and serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Set of default characters for use.
     */
    private static final char[] CODE_PATTERN = "TEAMARKET1234567890".toCharArray();

    /**
     * The default length of the returned string is 6.
     */
    private static final int CODE_LENGTH = 6;


    private static final String DATE_PATTERN = "EEE, d MMM yyyy, HH:mm:ss";


    private static final String TIME_ZONE = "GMT+3";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    protected Model() {
    }


    @Override
    public String toString() {
        return "Model{id=" + this.id + '}';
    }

    @Override
    public boolean equals(Object object) {
        return isNotNull(object) && (super.equals(object) || (getClass() == object.getClass()));
    }

    @Override
    public abstract int hashCode();

    /**
     * Converts a date of type Date to a string using input parameters to work
     * date format {@value DATE_PATTERN} and time zone (@value TIME_ZONE} by default.
     */
    protected String dateToString(final Date date) {
        return dateToStringWithFormat(date,
                new SimpleDateFormat(DATE_PATTERN),
                TimeZone.getTimeZone(TIME_ZONE)
        );
    }


    public long getId() {
        return this.id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Converts an inbound list;
     * returns a read-only list.
     */
    public <T extends Model> List<T> getUnmodifiableList(final Collection<T> collection) {
        final List<T> result;
        if (isNotEmpty(collection)) {
            result = Collections.unmodifiableList(new ArrayList<>(collection));
        } else {
            result = new ArrayList<>();
        }
        return result;
    }

    /**
     * Converts a date of type Date
     * Option for date format and time zone.
     */
    private static String dateToStringWithFormat(
            final Date date,
            final DateFormat dateFormat,
            final TimeZone timeZone
    ) {
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }
}