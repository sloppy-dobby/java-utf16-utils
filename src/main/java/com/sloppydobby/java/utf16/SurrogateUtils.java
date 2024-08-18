package com.sloppydobby.java.utf16;

/**
 * UTF-16 surrogates utils class.
 *
 * @author Sergey Grigorov
 *
 * @since Added in 0.0.1
 */
public final class SurrogateUtils {

    /**
     * This method can be used to check whether a char is a UTF-16 surrogate.
     * This method does not use comparison with the boundaries of the range of surrogates in UTF-16.
     * This method checks whether a character belongs to surrogates by the specific high bits <code>11011</code>.
     *
     * @param ch Char
     *
     * @return <code>true</code> if given char is surrogate or <code>false</code> if not
     *
     * @since Added in 0.0.1
     */
    public static boolean isSurrogate(final char ch) {
        return ch >> 11 == 0b11011;
    }

    /**
     * This method can be used to check whether a char is a UTF-16 high surrogate.
     * This method does not use comparison with the boundaries of the range of surrogates in UTF-16.
     * This method checks whether a character belongs to surrogates by the specific high bits <code>110110</code>.
     *
     * @param ch Char
     *
     * @return <code>true</code> if given char is high surrogate or <code>false</code> if not
     *
     * @since Added in 0.0.1
     */
    public static boolean isHighSurrogate(final char ch) {
        return ch >> 10 == 0b110110;
    }

    /**
     * This method can be used to check whether a char is a UTF-16 low surrogate.
     * This method does not use comparison with the boundaries of the range of surrogates in UTF-16.
     * This method checks whether a character belongs to surrogates by the specific high bits <code>110111</code>.
     *
     * @param ch Char
     *
     * @return True if given char is low surrogate or false if not
     *
     * @since Added in 0.0.1
     */
    public static boolean isLowSurrogate(final char ch) {
        return ch >> 10 == 0b110111;
    }

    /**
     * Private constructor to prevent utilities class instantiation.
     *
     * @since Added in 0.0.1
     */
    private SurrogateUtils() {}

}
