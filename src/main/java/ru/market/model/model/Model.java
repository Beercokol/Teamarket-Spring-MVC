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
     * Номер версии класса необходимый для десериализации и сериализации.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Набор вожможных для использованния символов по-умолчанию.
     */
    private static final char[] CODE_PATTERN = "ALEXCOFFEE1234567890".toCharArray();

    /**
     * Длина возвращаемой строки по-умолчанию 6.
     */
    private static final int CODE_LENGTH = 6;


    private static final String DATE_PATTERN = "EEE, d MMM yyyy, HH:mm:ss";


    private static final String TIME_ZONE = "GMT+3";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    protected Model() {
    }

    /**
     * Возвращает описание категории.
     * Переопределенный метод родительского класса {@link Object}.
     */
    @Override
    public String toString() {
        return "Model{id=" + this.id + '}';
    }

    /**
     * Сравнивает текущий объект с объектом переданым как параметр.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @param object объект для сравнения с текущим объектом.
     * @return Значение типа boolean - результат сравнения текущего объекта
     * с переданным объектом.
     */
    @Override
    public boolean equals(Object object) {
        return isNotNull(object) && (super.equals(object) || (getClass() == object.getClass()));
    }

    /**
     * Возвращает хеш код объекта.
     * Переопределенный метод родительского класса {@link Object}.
     *
     * @return Значение типа int - уникальный номер объекта.
     */
    @Override
    public abstract int hashCode();

    /**
     * Конвертирует дату типа Date в строку используя для работы входящими параметрами
     * формат даты {@value DATE_PATTERN} и часовой пояс (@value TIME_ZONE} по-умолчанию.
     *
     * @param date Значение даты типа Date для обработки.
     * @return Значение типа {@link String} - дата в виде строки.
     */
    protected String dateToString(final Date date) {
        return dateToStringWithFormat(date,
                new SimpleDateFormat(DATE_PATTERN),
                TimeZone.getTimeZone(TIME_ZONE)
        );
    }

    /**
     * Возвращает номер версии класса необходимый для десериализации
     * и сериализации.
     *
     * @return Значение типа {@link Long} - значение поля serialVersionUID.
     */
    public long getId() {
        return this.id;
    }

    /**
     * Устанавливает номер версии класса необходимый
     * для десериализации и сериализации.
     *
     * @param id Значение параметра будет записано в поле id объекта.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Конвертирует входящий список возращает лист только для чтений.
     * Если входной параметер - лист равен null или пустой,
     * тогда метод возвращает пустой список.
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
     * Конвертирует дату типа Date в строку используя для работы входящими
     * параметрами формат даты и часовой пояс.
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