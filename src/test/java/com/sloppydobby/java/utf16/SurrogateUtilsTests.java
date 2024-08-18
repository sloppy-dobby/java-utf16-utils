package com.sloppydobby.java.utf16;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public final class SurrogateUtilsTests {

    private final char[] chars = { ' ', '\t', '\n', '\r', '\uD800', '\uDBFF', '\uDC00', '\uDFFF' };

    @Test
    public void testIsSurrogate() {
        boolean expected;

        for (final char ch : chars) {
            expected = Character.isSurrogate(ch);
            test(ch, expected, SurrogateUtils::isSurrogate);
        }
    }

    @Test
    public void testIsHighSurrogate() {
        boolean expected;

        for (final char ch : chars) {
            expected = Character.isHighSurrogate(ch);
            test(ch, expected, SurrogateUtils::isHighSurrogate);
        }
    }

    @Test
    public void testIsLowSurrogate() {
        boolean expected;

        for (final char ch : chars) {
            expected = Character.isLowSurrogate(ch);
            test(ch, expected, SurrogateUtils::isLowSurrogate);
        }
    }

    private void test(final char ch, final boolean expected, final Function<Character, Boolean> target) {
        final boolean result = target.apply(ch);

        if (expected != result) {
            final String error = getAssertionError(ch, expected, result);
            Assert.fail(error);
        }
    }

    private String getAssertionError(final char ch, final boolean expected, final boolean result) {
        final String hex = String.format("U+%04x", (int) ch);
        return "Char '" + hex + "' test failed, expected " + expected + ", given " + result;
    }

}
