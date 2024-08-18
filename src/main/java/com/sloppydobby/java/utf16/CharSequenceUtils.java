package com.sloppydobby.java.utf16;

/**
 * UTF-16 utils for char sequence.
 *
 * @author Sergey Grigorov
 *
 * @since Added in 0.0.1
 */
public final class CharSequenceUtils {

    /**
     * Flag for space char target, using for <code>trim</code> method.
     *
     * @since Added in 0.0.1
     */
    public static final int TARGET_SPACE_CHAR = 0b1; // 1

    /**
     * Flag for tab char target, using for <code>trim</code> method.
     *
     * @since Added in 0.0.1
     */
    public static final int TARGET_TAB_CHAR = 0b10; // 2

    /**
     * Flag for line break char target, using for <code>trim</code> method.
     *
     * @since Added in 0.0.1
     */
    public static final int TARGET_LINE_BREAK_CHAR = 0b100; // 4

    /**
     * Flag for return caret char target, using for <code>trim</code> method.
     *
     * @since Added in 0.0.1
     */
    public static final int TARGET_RETURN_CARET_CHAR = 0b1000; // 8

    /**
     * Flag for surrogate char target, using for <code>trim</code> method.
     *
     * @since Added in 0.0.1
     */
    public static final int TARGET_SURROGATE_CHAR = 0b10000; // 16

    /**
     * This method can be used to check whether a char sequence contains UTF-16 surrogates.
     * This method using loop for a sequence of characters from two sides - first from the beginning to the middle,
     * then from the end to the middle.
     * As soon as the first surrogate is found, the method returns the <code>true</code>.
     *
     * @param seq Char sequence
     *
     * @return <code>true</code> if a char sequence contains UTF-16 surrogates or <code>false</code> if not
     *
     * @see SurrogateUtils
     *
     * @since Added in 0.0.1
     */
    public static boolean containsSurrogates(final CharSequence seq) {
        if (seq == null || seq.length() == 0) {
            return false;
        }

        final int len = seq.length();

        // Checking for 1-2 chars will provide a higher speed due to the absence of the need to enter the loop.
        // It will also avoid possible problems when calculating the middle index.

        switch (len) {
            case 1:
                final char ch = seq.charAt(0);
                return SurrogateUtils.isSurrogate(ch);

            case 2:
                final char ch0 = seq.charAt(0);
                final char ch1 = seq.charAt(1);

                return SurrogateUtils.isSurrogate(ch0) || SurrogateUtils.isSurrogate(ch1);

            default:
                break;
        }

        final int maxIdx = len - 1;
        final int midIdx = maxIdx / 2;

        char ch;
        boolean res;

        // Since surrogates are most often found at the beginning or end of a line (or closer to them),
        // it makes sense to expand the reading of the line after the middle.

        for (int i = 0; i < midIdx; i++) {
            ch = seq.charAt(i);
            res = SurrogateUtils.isSurrogate(ch);

            if (res) {
                return true;
            }
        }

        for (int i = maxIdx; i >= midIdx; i--) {
            ch = seq.charAt(i);
            res = SurrogateUtils.isSurrogate(ch);

            if (res) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method can be used to check whether a char sequence contains only UTF-16 surrogates.
     * This method using loop for a sequence of characters from two sides - first from the beginning to the middle,
     * then from the end to the middle.
     * As soon as the first non-surrogate char is found, the method returns the <code>false</code>.
     *
     * @param seq Char sequence
     *
     * @return <code>true</code> if a char sequence contains only UTF-16 surrogates or <code>false</code> if not
     *
     * @see SurrogateUtils
     *
     * @since Added in 0.0.1
     */
    public static boolean containsSurrogatesOnly(final CharSequence seq) {
        if (seq == null || seq.length() == 0) {
            return false;
        }

        final int len = seq.length();

        // Checking for 1-2 chars will provide a higher speed due to the absence of the need to enter the loop.
        // It will also avoid possible problems when calculating the middle index.

        switch (len) {
            case 1:
                final char ch = seq.charAt(0);
                return SurrogateUtils.isSurrogate(ch);

            case 2:
                final char ch0 = seq.charAt(0);
                final char ch1 = seq.charAt(1);

                return SurrogateUtils.isSurrogate(ch0) && SurrogateUtils.isSurrogate(ch1);

            default:
                break;
        }

        final int maxIdx = len - 1;
        final int midIdx = maxIdx / 2;

        char ch;
        boolean res;

        // Since surrogates are most often found at the beginning or end of a line (or closer to them),
        // it makes sense to expand the reading of the line after the middle.

        for (int i = 0; i < midIdx; i++) {
            ch = seq.charAt(i);
            res = SurrogateUtils.isSurrogate(ch);

            if (!res) {
                return false;
            }
        }

        for (int i = maxIdx; i >= midIdx; i--) {
            ch = seq.charAt(i);
            res = SurrogateUtils.isSurrogate(ch);

            if (!res) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method can be used to check whether a char sequence contains UTF-16 high surrogates.
     * This method using loop for a sequence of characters from two sides - first from the beginning to the middle,
     * then from the end to the middle.
     * As soon as the first high surrogate is found, the method returns the <code>true</code>.
     *
     * @param seq Char sequence
     *
     * @return <code>true</code> if a char sequence contains UTF-16 high surrogates or <code>false</code> if not
     *
     * @see SurrogateUtils
     *
     * @since Added in 0.0.1
     */
    public static boolean containsHighSurrogates(final CharSequence seq) {
        if (seq == null || seq.length() == 0) {
            return false;
        }

        final int len = seq.length();

        // Checking for 1-2 chars will provide a higher speed due to the absence of the need to enter the loop.
        // It will also avoid possible problems when calculating the middle index.

        switch (len) {
            case 1:
                final char ch = seq.charAt(0);
                return SurrogateUtils.isHighSurrogate(ch);

            case 2:
                final char ch0 = seq.charAt(0);
                final char ch1 = seq.charAt(1);

                return SurrogateUtils.isHighSurrogate(ch0) || SurrogateUtils.isHighSurrogate(ch1);

            default:
                break;
        }

        final int maxIdx = len - 1;
        final int midIdx = maxIdx / 2;

        char ch;
        boolean res;

        // Since surrogates are most often found at the beginning or end of a line (or closer to them),
        // it makes sense to expand the reading of the line after the middle.

        for (int i = 0; i < midIdx; i++) {
            ch = seq.charAt(i);
            res = SurrogateUtils.isHighSurrogate(ch);

            if (res) {
                return true;
            }
        }

        for (int i = maxIdx; i >= midIdx; i--) {
            ch = seq.charAt(i);
            res = SurrogateUtils.isHighSurrogate(ch);

            if (res) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method can be used to check whether a char sequence contains only UTF-16 high surrogates.
     * This method using loop for a sequence of characters from two sides - first from the beginning to the middle,
     * then from the end to the middle.
     * As soon as the first non-high-surrogate char is found, the method returns the <code>false</code>.
     *
     * @param seq Char sequence
     *
     * @return <code>true</code> if a char sequence contains only UTF-16 high surrogates or <code>false</code> if not
     *
     * @see SurrogateUtils
     *
     * @since Added in 0.0.1
     */
    public static boolean containsHighSurrogatesOnly(final CharSequence seq) {
        if (seq == null || seq.length() == 0) {
            return false;
        }

        final int len = seq.length();

        // Checking for 1-2 chars will provide a higher speed due to the absence of the need to enter the loop.
        // It will also avoid possible problems when calculating the middle index.

        switch (len) {
            case 1:
                final char ch = seq.charAt(0);
                return SurrogateUtils.isHighSurrogate(ch);

            case 2:
                final char ch0 = seq.charAt(0);
                final char ch1 = seq.charAt(1);

                return SurrogateUtils.isHighSurrogate(ch0) && SurrogateUtils.isHighSurrogate(ch1);

            default:
                break;
        }

        final int maxIdx = len - 1;
        final int midIdx = maxIdx / 2;

        char ch;
        boolean res;

        // Since surrogates are most often found at the beginning or end of a line (or closer to them),
        // it makes sense to expand the reading of the line after the middle.

        for (int i = 0; i < midIdx; i++) {
            ch = seq.charAt(i);
            res = SurrogateUtils.isHighSurrogate(ch);

            if (!res) {
                return false;
            }
        }

        for (int i = maxIdx; i >= midIdx; i--) {
            ch = seq.charAt(i);
            res = SurrogateUtils.isHighSurrogate(ch);

            if (!res) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method can be used to check whether a char sequence contains UTF-16 low surrogates.
     * This method using loop for a sequence of characters from two sides - first from the beginning to the middle,
     * then from the end to the middle.
     * As soon as the first low surrogate is found, the method returns the <code>true</code>.
     *
     * @param seq Char sequence
     *
     * @return <code>true</code> if a char sequence contains UTF-16 low surrogates or <code>false</code> if not
     *
     * @see SurrogateUtils
     *
     * @since Added in 0.0.1
     */
    public static boolean containsLowSurrogates(final CharSequence seq) {
        if (seq == null || seq.length() == 0) {
            return false;
        }

        final int len = seq.length();

        // Checking for 1-2 chars will provide a higher speed due to the absence of the need to enter the loop.
        // It will also avoid possible problems when calculating the middle index.

        switch (len) {
            case 1:
                final char ch = seq.charAt(0);
                return SurrogateUtils.isLowSurrogate(ch);

            case 2:
                final char ch0 = seq.charAt(0);
                final char ch1 = seq.charAt(1);

                return SurrogateUtils.isLowSurrogate(ch0) || SurrogateUtils.isLowSurrogate(ch1);

            default:
                break;
        }

        final int maxIdx = len - 1;
        final int midIdx = maxIdx / 2;

        char ch;
        boolean res;

        // Since surrogates are most often found at the beginning or end of a line (or closer to them),
        // it makes sense to expand the reading of the line after the middle.

        for (int i = 0; i < midIdx; i++) {
            ch = seq.charAt(i);
            res = SurrogateUtils.isLowSurrogate(ch);

            if (res) {
                return true;
            }
        }

        for (int i = maxIdx; i >= midIdx; i--) {
            ch = seq.charAt(i);
            res = SurrogateUtils.isLowSurrogate(ch);

            if (res) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method can be used to check whether a char sequence contains only UTF-16 low surrogates.
     * This method using loop for a sequence of characters from two sides - first from the beginning to the middle,
     * then from the end to the middle.
     * As soon as the first non-low-surrogate char is found, the method returns the <code>false</code>.
     *
     * @param seq Char sequence
     *
     * @return <code>true</code> if a char sequence contains only UTF-16 low surrogates or <code>false</code> if not
     *
     * @see SurrogateUtils
     *
     * @since Added in 0.0.1
     */
    public static boolean containsLowSurrogatesOnly(final CharSequence seq) {
        if (seq == null || seq.length() == 0) {
            return false;
        }

        final int len = seq.length();

        // Checking for 1-2 chars will provide a higher speed due to the absence of the need to enter the loop.
        // It will also avoid possible problems when calculating the middle index.

        switch (len) {
            case 1:
                final char ch = seq.charAt(0);
                return SurrogateUtils.isLowSurrogate(ch);

            case 2:
                final char ch0 = seq.charAt(0);
                final char ch1 = seq.charAt(1);

                return SurrogateUtils.isLowSurrogate(ch0) && SurrogateUtils.isLowSurrogate(ch1);

            default:
                break;
        }

        final int maxIdx = len - 1;
        final int midIdx = maxIdx / 2;

        char ch;
        boolean res;

        // Since surrogates are most often found at the beginning or end of a line (or closer to them),
        // it makes sense to expand the reading of the line after the middle.

        for (int i = 0; i < midIdx; i++) {
            ch = seq.charAt(i);
            res = SurrogateUtils.isLowSurrogate(ch);

            if (!res) {
                return false;
            }
        }

        for (int i = maxIdx; i >= midIdx; i--) {
            ch = seq.charAt(i);
            res = SurrogateUtils.isLowSurrogate(ch);

            if (!res) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method can be used to check whether a char sequence contains UTF-16 surrogate pairs.
     * This method using loop for a sequence of characters from two sides - first from the beginning to the middle,
     * then from the end to the middle.
     * As soon as the first surrogate pair is found, the method returns the <code>true</code>.
     *
     * @param seq Char sequence
     *
     * @return <code>true</code> if a char sequence contains UTF-16 surrogate pairs or <code>false</code> if not
     *
     * @see SurrogateUtils
     *
     * @since Added in 0.0.1
     */
    public static boolean containsSurrogatePairs(final CharSequence seq) {
        if (seq == null || seq.length() < 2) {
            return false;
        }

        final int len = seq.length();

        char high;
        char low;

        // Checking for 2 chars will provide a higher speed due to the absence of the need to enter the loop.
        // It will also avoid possible problems when calculating the middle index.

        if (len == 2) {
            high = seq.charAt(0);
            low = seq.charAt(1);

            return SurrogatePairUtils.isSurrogatePair(high, low);
        }

        final int maxIdx = len - 1;
        final int midIdx = maxIdx / 2;

        boolean res;

        // Since surrogates are most often found at the beginning or end of a line (or closer to them),
        // it makes sense to expand the reading of the line after the middle.

        for (int i = 0; i < midIdx; i++) {
            high = seq.charAt(i);
            res = SurrogateUtils.isHighSurrogate(high);

            if (res) {
                low = seq.charAt(i + 1);
                res = SurrogatePairUtils.isSurrogatePair(high, low);

                if (res) {
                    return true;
                }
            }
        }

        for (int i = maxIdx; i >= midIdx; i--) {
            low = seq.charAt(i);
            res = SurrogateUtils.isLowSurrogate(low);

            if (res) {
                high = seq.charAt(i - 1);
                res = SurrogatePairUtils.isSurrogatePair(high, low);

                if (res) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This method can be used to check whether a char sequence contains only UTF-16 surrogate pairs.
     * This method using loop for a sequence of characters from two sides - first from the beginning to the middle,
     * then from the end to the middle.
     * As soon as the first non-surrogate or alone surrogate char is found, the method returns the <code>false</code>.
     *
     * @param seq Char sequence
     *
     * @return <code>true</code> if a char sequence contains only UTF-16 surrogate pairs or <code>false</code> if not
     *
     * @see SurrogateUtils
     *
     * @since Added in 0.0.1
     */
    public static boolean containsSurrogatePairsOnly(final CharSequence seq) {
        if (seq == null || seq.length() < 2) {
            return false;
        }

        final int len = seq.length();

        char high;
        char low;

        // Checking for 2 chars will provide a higher speed due to the absence of the need to enter the loop.
        // It will also avoid possible problems when calculating the middle index.

        if (len == 2) {
            high = seq.charAt(0);
            low = seq.charAt(1);

            return SurrogatePairUtils.isSurrogatePair(high, low);
        }

        final int maxIdx = len - 1;
        final int midIdx = maxIdx / 2;

        boolean res;

        // Since surrogates are most often found at the beginning or end of a line (or closer to them),
        // it makes sense to expand the reading of the line after the middle.

        for (int i = 0; i < midIdx; i++) {
            high = seq.charAt(i);
            res = SurrogateUtils.isSurrogate(high);

            if (res) {
                low = seq.charAt(i + 1);
                res = SurrogatePairUtils.isSurrogatePair(high, low);

                if (res) {
                    i++;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        for (int i = maxIdx; i >= midIdx; i--) {
            low = seq.charAt(i);
            res = SurrogateUtils.isSurrogate(low);

            if (res) {
                high = seq.charAt(i - 1);
                res = SurrogatePairUtils.isSurrogatePair(high, low);

                if (res) {
                    i--;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * This method can be used to check whether a char sequence contains unattached UTF-16 surrogates.
     * Unattached surrogates here are surrogates who are not a part of the surrogate pair.
     * This method using loop for a sequence of characters from two sides - first from the beginning to the middle,
     * then from the end to the middle.
     * As soon as the first unattached surrogate char is found, the method returns the <code>true</code>.
     *
     * @param seq Char sequence
     *
     * @return <code>true</code> if a char sequence contains unattached UTF-16 surrogates or <code>false</code> if not
     *
     * @see SurrogateUtils
     *
     * @since Added in 0.0.1
     */
    public static boolean containsUnattachedSurrogates(final CharSequence seq) {
        if (seq == null || seq.length() == 0) {
            return false;
        }

        final int len = seq.length();

        boolean res;

        // Checking for 1-2 chars will provide a higher speed due to the absence of the need to enter the loop.
        // It will also avoid possible problems when calculating the middle index.

        switch (len) {
            case 1:
                final char ch = seq.charAt(0);
                return SurrogateUtils.isSurrogate(ch);

            case 2:
                final char ch0 = seq.charAt(0);
                final char ch1 = seq.charAt(1);

                res = SurrogatePairUtils.isSurrogatePair(ch0, ch1);

                if (res) {
                    return false;
                } else {
                    return SurrogateUtils.isSurrogate(ch0) || SurrogateUtils.isSurrogate(ch1);
                }

            default:
                break;
        }

        final int maxIdx = len - 1;
        final int midIdx = maxIdx / 2;

        char high;
        char low;

        // Since surrogates are most often found at the beginning or end of a line (or closer to them),
        // it makes sense to expand the reading of the line after the middle.

        for (int i = 0; i < midIdx; i++) {
            high = seq.charAt(i);
            res = SurrogateUtils.isSurrogate(high);

            if (res) {
                low = seq.charAt(i + 1);
                res = SurrogatePairUtils.isSurrogatePair(high, low);

                if (res) {
                    i++;
                } else {
                    return true;
                }
            }
        }

        for (int i = maxIdx; i >= midIdx; i--) {
            low = seq.charAt(i);
            res = SurrogateUtils.isSurrogate(low);

            if (res) {
                high = seq.charAt(i - 1);
                res = SurrogatePairUtils.isSurrogatePair(high, low);

                if (res) {
                    i--;
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This method is used to trim the char sequence for specified target chars.
     * You can set target characters using constants with the <code>TARGET_*</code> prefix.
     * Target constants can be combined using a bitwise <code>OR</code> (<code>|</code>).
     * The method runs from the beginning of the sequence of characters to the first character that does not match the
     * target characters.
     * After that, the reading of the sequence of characters unfolds - the characters begin to iterate from the end of
     * the sequence to the beginning also to the first non-matching character.
     *
     * @param seq Char sequence
     * @param target Target characters bitmask constant
     *
     * @return Trimmed char sequence
     *
     * @since Added in 0.0.1
     */
    public static CharSequence trim(final CharSequence seq, final int target) {
        if (seq == null || seq.length() == 0) {
            return seq;
        }

        final int len = seq.length();

        int begIdx = 0;
        int endIdx = len - 1;

        char ch;
        boolean res;

        // Since surrogates are most often found at the beginning or end of a line (or closer to them),
        // it makes sense to expand the reading of the line after the middle.

        for (int i = 0; i < endIdx ; i++) {
            ch = seq.charAt(i);

            res = (target & TARGET_SPACE_CHAR) != 0 && ch == ' ';

            if (res) {
                begIdx++;
                continue;
            }

            res = (target & TARGET_TAB_CHAR) != 0 && ch == '\t';

            if (res) {
                begIdx++;
                continue;
            }

            res = (target & TARGET_LINE_BREAK_CHAR) != 0 && ch == '\n';

            if (res) {
                begIdx++;
                continue;
            }

            res = (target & TARGET_RETURN_CARET_CHAR) != 0 && ch == '\r';

            if (res) {
                begIdx++;
                continue;
            }

            res = (target & TARGET_SURROGATE_CHAR) != 0 && SurrogateUtils.isSurrogate(ch);

            if (res) {
                begIdx++;
                continue;
            }

            break;
        }

        if (begIdx == endIdx) {
            return "";
        }

        for (int i = endIdx; i >= 0; i--) {
            ch = seq.charAt(i);

            res = (target & TARGET_SPACE_CHAR) != 0 && ch == ' ';

            if (res) {
                endIdx--;
                continue;
            }

            res = (target & TARGET_TAB_CHAR) != 0 && ch == '\t';

            if (res) {
                endIdx--;
                continue;
            }

            res = (target & TARGET_LINE_BREAK_CHAR) != 0 && ch == '\n';

            if (res) {
                endIdx--;
                continue;
            }

            res = (target & TARGET_RETURN_CARET_CHAR) != 0 && ch == '\r';

            if (res) {
                endIdx--;
                continue;
            }

            res = (target & TARGET_SURROGATE_CHAR) != 0 && SurrogateUtils.isSurrogate(ch);

            if (res) {
                endIdx--;
                continue;
            }

            break;
        }

        if (begIdx >= endIdx) {
            return "";
        }

        if (begIdx == 0 && endIdx == len -1) {
            return seq;
        }

        return seq.subSequence(begIdx, endIdx + 1);
    }

    /**
     * This method is used to trim the char sequence for space, tabulation, line break and return caret chars.
     * You can set target characters using constants with the <code>TARGET_*</code> prefix.
     * Target constants can be combined using a bitwise <code>OR</code> (<code>|</code>).
     * The method runs from the beginning of the sequence of characters to the first character that does not match the
     * target characters.
     * After that, the reading of the sequence of characters unfolds - the characters begin to iterate from the end of
     * the sequence to the beginning also to the first non-matching character.
     *
     * @param seq Char sequence
     *
     * @return Trimmed char sequence
     *
     * @since Added in 0.0.1
     */
    public static CharSequence trim(final CharSequence seq) {
        return trim(seq, TARGET_SPACE_CHAR | TARGET_TAB_CHAR | TARGET_LINE_BREAK_CHAR | TARGET_RETURN_CARET_CHAR);
    }

    /**
     * Private constructor to prevent utilities class instantiation.
     */
    private CharSequenceUtils() {}

}
