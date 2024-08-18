package com.sloppydobby.java.utf16;

/**
 * UTF-16 surrogate pairs utils class.
 *
 * @author Sergey Grigorov
 *
 * @since Added in 0.0.1
 */
public final class SurrogatePairUtils {

    /**
     * This method can be used to check whether the two given characters make up a surrogate pair.
     * The 1st char should be a high surrogate and the 2nd char should be is a low surrogate.
     *
     * @param high High surrogate char
     * @param low Low surrogate char
     *
     * @return True if chars make up a surrogate pair or false if not
     *
     * @since Added in 0.0.1
     */
    public static boolean isSurrogatePair(final char high, final char low) {
        return SurrogateUtils.isHighSurrogate(high) && SurrogateUtils.isLowSurrogate(low);
    }

    /**
     * Private constructor to prevent utilities class instantiation.
     *
     * @since Added in 0.0.1
     */
    private SurrogatePairUtils() {}

}
