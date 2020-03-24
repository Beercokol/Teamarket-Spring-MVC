/*
 * Copyright
 */

package ru.market.util.validator;

import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;

public final class ObjectValidator {

    private ObjectValidator() {
    }

    public static boolean isNull(final Object object) {
        return (object == null);
    }


    public static boolean isNotNull(final Object object) {
        return !isNull(object);
    }


    public static boolean isEmpty(final Collection collection) {
        return isNull(collection) || collection.isEmpty();
    }


    public static boolean isNotEmpty(final Collection collection) {
        return !isEmpty(collection);
    }


    public static boolean isEmpty(final Map map) {
        return isNull(map) || map.isEmpty();
    }


    public static boolean isNotEmpty(final Map map) {
        return !isEmpty(map);
    }


    public static boolean isEmpty(final String string) {
        return isBlank(string);
    }


    public static boolean isNotEmpty(final String string) {
        return !isEmpty(string);
    }


    public static boolean isEmpty(final MultipartFile file) {
        return isNull(file) || file.isEmpty();
    }

    public static boolean isNotEmpty(final MultipartFile file) {
        return !isEmpty(file);
    }

    public static <T> boolean isEmpty(final T[] array) {
        return isNull(array) || (array.length == 0);
    }


    public static <T> boolean isNotEmpty(final T[] array) {
        return !isEmpty(array);
    }


    public static boolean isEmpty(final byte[] array) {
        return isNull(array) || (array.length == 0);
    }


    public static boolean isNotEmpty(final byte[] array) {
        return !isEmpty(array);
    }


    public static boolean isEmpty(final short[] array) {
        return isNull(array) || (array.length == 0);
    }


    public static boolean isNotEmpty(final short[] array) {
        return !isEmpty(array);
    }


    public static boolean isEmpty(final char[] array) {
        return isNull(array) || (array.length == 0);
    }


    public static boolean isNotEmpty(final char[] array) {
        return !isEmpty(array);
    }


    public static boolean isEmpty(final int[] array) {
        return isNull(array) || (array.length == 0);
    }


    public static boolean isNotEmpty(final int[] array) {
        return !isEmpty(array);
    }


    public static boolean isEmpty(final long[] array) {
        return isNull(array) || (array.length == 0);
    }


    public static boolean isNotEmpty(final long[] array) {
        return !isEmpty(array);
    }


    public static boolean isEmpty(final float[] array) {
        return isNull(array) || (array.length == 0);
    }


    public static boolean isNotEmpty(final float[] array) {
        return !isEmpty(array);
    }


    public static boolean isEmpty(final double[] array) {
        return isNull(array) || (array.length == 0);
    }


    public static boolean isNotEmpty(final double[] array) {
        return !isEmpty(array);
    }
}