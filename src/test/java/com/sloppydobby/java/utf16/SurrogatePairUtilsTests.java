package com.sloppydobby.java.utf16;

import org.junit.Assert;
import org.junit.Test;

public final class SurrogatePairUtilsTests {

    private final char[] chars = { ' ', '\t', '\n', '\r', '\uD800', '\uDBFF', '\uDC00', '\uDFFF' };

    @Test
    public void testIsSurrogatePair() {
        boolean expected;
        boolean result;

        for (final char high : chars) {
            for (final char low : chars) {
                expected = Character.isSurrogatePair(high, low);
                result = SurrogatePairUtils.isSurrogatePair(high, low);

                if (expected != result) {
                    final String error = getAssertionError(high, low, expected, result);
                    Assert.fail(error);
                }
            }
        }
    }

    private String getAssertionError(final char high, final char low, final boolean expected, final boolean result) {
        final String highHex = String.format("U+%04x", (int) high);
        final String lowHex = String.format("U+%04x", (int) low);

        return "Char pair '" + highHex + " " + lowHex + "' test failed, expected " + expected + ", given " + result;
    }

}
