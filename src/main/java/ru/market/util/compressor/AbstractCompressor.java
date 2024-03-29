/*
 * Copyright
 */

package ru.market.util.compressor;

import com.googlecode.htmlcompressor.compressor.Compressor;

import static ru.market.util.validator.ObjectValidator.isNotEmpty;
abstract class AbstractCompressor implements Compressor {

    /**
     * Compresses the incoming source and returns a compressed result.
     * Compresses the source if it not null and not empty.
     * <pre>
     *     compress(null) = "" (empty string)
     *     compress("") = "" (empty string)
     *     compress(" ") = "" (empty string)
     *     compress("bob") = "bob"
     *     compress("bob") = "bob"
     * </pre>
     *
     * @param source the source to compress.
     * @return Compressed result or empty string (never null).
     */
    @Override
    public String compress(final String source) {
        String result = "";
        if (isNotEmpty(source)) {
            final Compressor compressor = getCompressor();
            result = compressor.compress(source);
        }
        return result;
    }

    /**
     * Returns a compressor object.
     */
    abstract Compressor getCompressor();
}
