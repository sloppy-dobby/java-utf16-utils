package com.sloppydobby.java.utf16;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public final class CharSequenceUtilsTests {

    private final char[] chars = { ' ', '\t', '\n', '\r', '\uD800', '\uDBFF', '\uDC00', '\uDFFF' };

    private final int[] targets = {
            CharSequenceUtils.TARGET_SPACE_CHAR,
            CharSequenceUtils.TARGET_TAB_CHAR,
            CharSequenceUtils.TARGET_LINE_BREAK_CHAR,
            CharSequenceUtils.TARGET_RETURN_CARET_CHAR,
            CharSequenceUtils.TARGET_SURROGATE_CHAR
    };

    @Test
    public void testContainsSurrogates() {
        test(null, false, CharSequenceUtils::containsSurrogates);
        test("", false, CharSequenceUtils::containsSurrogates);

        CharSequence seq;
        boolean expected;

        for (final char ch0 : chars) {
            seq = getCharSequenceOf(ch0);
            expected = Character.isSurrogate(ch0);

            test(seq, expected, CharSequenceUtils::containsSurrogates);

            for (final char ch1 : chars) {
                seq = getCharSequenceOf(ch0, ch1);
                expected = Character.isSurrogate(ch0) || Character.isSurrogate(ch1);

                test(seq, expected, CharSequenceUtils::containsSurrogates);

                for (final char ch2 : chars) {
                    seq = getCharSequenceOf(ch0, ch1, ch2);
                    expected = Character.isSurrogate(ch0) || Character.isSurrogate(ch1) || Character.isSurrogate(ch2);

                    test(seq, expected, CharSequenceUtils::containsSurrogates);
                }
            }
        }
    }

    @Test
    public void testContainsSurrogatesOnly() {
        test(null, false, CharSequenceUtils::containsSurrogatesOnly);
        test("", false, CharSequenceUtils::containsSurrogatesOnly);

        CharSequence seq;
        boolean expected;

        for (final char ch0 : chars) {
            seq = getCharSequenceOf(ch0);
            expected = Character.isSurrogate(ch0);

            test(seq, expected, CharSequenceUtils::containsSurrogatesOnly);

            for (final char ch1 : chars) {
                seq = getCharSequenceOf(ch0, ch1);
                expected = Character.isSurrogate(ch0) && Character.isSurrogate(ch1);

                test(seq, expected, CharSequenceUtils::containsSurrogatesOnly);

                for (final char ch2 : chars) {
                    seq = getCharSequenceOf(ch0, ch1, ch2);
                    expected = Character.isSurrogate(ch0) && Character.isSurrogate(ch1) && Character.isSurrogate(ch2);

                    test(seq, expected, CharSequenceUtils::containsSurrogatesOnly);
                }
            }
        }
    }

    @Test
    public void testContainsHighSurrogates() {
        test(null, false, CharSequenceUtils::containsHighSurrogates);
        test("", false, CharSequenceUtils::containsHighSurrogates);

        CharSequence seq;
        boolean expected;

        for (final char ch0 : chars) {
            seq = getCharSequenceOf(ch0);
            expected = Character.isHighSurrogate(ch0);

            test(seq, expected, CharSequenceUtils::containsHighSurrogates);

            for (final char ch1 : chars) {
                seq = getCharSequenceOf(ch0, ch1);
                expected = Character.isHighSurrogate(ch0) || Character.isHighSurrogate(ch1);

                test(seq, expected, CharSequenceUtils::containsHighSurrogates);

                for (final char ch2 : chars) {
                    seq = getCharSequenceOf(ch0, ch1, ch2);
                    expected = Character.isHighSurrogate(ch0) || Character.isHighSurrogate(ch1) || Character.isHighSurrogate(ch2);

                    test(seq, expected, CharSequenceUtils::containsHighSurrogates);
                }
            }
        }
    }

    @Test
    public void testContainsHighSurrogatesOnly() {
        test(null, false, CharSequenceUtils::containsHighSurrogatesOnly);
        test("", false, CharSequenceUtils::containsHighSurrogatesOnly);

        CharSequence seq;
        boolean expected;

        for (final char ch0 : chars) {
            seq = getCharSequenceOf(ch0);
            expected = Character.isHighSurrogate(ch0);

            test(seq, expected, CharSequenceUtils::containsHighSurrogatesOnly);

            for (final char ch1 : chars) {
                seq = getCharSequenceOf(ch0, ch1);
                expected = Character.isHighSurrogate(ch0) && Character.isHighSurrogate(ch1);

                test(seq, expected, CharSequenceUtils::containsHighSurrogatesOnly);

                for (final char ch2 : chars) {
                    seq = getCharSequenceOf(ch0, ch1, ch2);
                    expected = Character.isHighSurrogate(ch0) && Character.isHighSurrogate(ch1) && Character.isHighSurrogate(ch2);

                    test(seq, expected, CharSequenceUtils::containsHighSurrogatesOnly);
                }
            }
        }
    }

    @Test
    public void testContainsLowSurrogates() {
        test(null, false, CharSequenceUtils::containsLowSurrogates);
        test("", false, CharSequenceUtils::containsLowSurrogates);

        CharSequence seq;
        boolean expected;

        for (final char ch0 : chars) {
            seq = getCharSequenceOf(ch0);
            expected = Character.isLowSurrogate(ch0);

            test(seq, expected, CharSequenceUtils::containsLowSurrogates);

            for (final char ch1 : chars) {
                seq = getCharSequenceOf(ch0, ch1);
                expected = Character.isLowSurrogate(ch0) || Character.isLowSurrogate(ch1);

                test(seq, expected, CharSequenceUtils::containsLowSurrogates);

                for (final char ch2 : chars) {
                    seq = getCharSequenceOf(ch0, ch1, ch2);
                    expected = Character.isLowSurrogate(ch0) || Character.isLowSurrogate(ch1) || Character.isLowSurrogate(ch2);

                    test(seq, expected, CharSequenceUtils::containsLowSurrogates);
                }
            }
        }
    }

    @Test
    public void testContainsLowSurrogatesOnly() {
        test(null, false, CharSequenceUtils::containsLowSurrogatesOnly);
        test("", false, CharSequenceUtils::containsLowSurrogatesOnly);

        CharSequence seq;
        boolean expected;

        for (final char ch0 : chars) {
            seq = getCharSequenceOf(ch0);
            expected = Character.isLowSurrogate(ch0);

            test(seq, expected, CharSequenceUtils::containsLowSurrogatesOnly);

            for (final char ch1 : chars) {
                seq = getCharSequenceOf(ch0, ch1);
                expected = Character.isLowSurrogate(ch0) && Character.isLowSurrogate(ch1);

                test(seq, expected, CharSequenceUtils::containsLowSurrogatesOnly);

                for (final char ch2 : chars) {
                    seq = getCharSequenceOf(ch0, ch1, ch2);
                    expected = Character.isLowSurrogate(ch0) && Character.isLowSurrogate(ch1) && Character.isLowSurrogate(ch2);

                    test(seq, expected, CharSequenceUtils::containsLowSurrogatesOnly);
                }
            }
        }
    }

    @Test
    public void testContainsSurrogatePairs() {
        test(null, false, CharSequenceUtils::containsSurrogatePairs);
        test("", false, CharSequenceUtils::containsSurrogatePairs);

        CharSequence seq;
        boolean expected;

        for (final char ch0 : chars) {
            seq = getCharSequenceOf(ch0);
            test(seq, false, CharSequenceUtils::containsSurrogatePairs);

            for (final char ch1 : chars) {
                seq = getCharSequenceOf(ch0, ch1);
                expected = Character.isSurrogatePair(ch0, ch1);

                test(seq, expected, CharSequenceUtils::containsSurrogatePairs);

                for (final char ch2 : chars) {
                    seq = getCharSequenceOf(ch0, ch1, ch2);
                    expected = Character.isSurrogatePair(ch0, ch1) || Character.isSurrogatePair(ch1, ch2);

                    test(seq, expected, CharSequenceUtils::containsSurrogatePairs);
                }
            }
        }
    }

    @Test
    public void testContainsSurrogatePairsOnly() {
        test(null, false, CharSequenceUtils::containsSurrogatePairsOnly);
        test("", false, CharSequenceUtils::containsSurrogatePairsOnly);

        CharSequence seq;
        boolean expected;

        for (final char ch0 : chars) {
            seq = getCharSequenceOf(ch0);
            test(seq, false, CharSequenceUtils::containsSurrogatePairsOnly);

            for (final char ch1 : chars) {
                seq = getCharSequenceOf(ch0, ch1);
                expected = Character.isSurrogatePair(ch0, ch1);

                test(seq, expected, CharSequenceUtils::containsSurrogatePairsOnly);

                for (final char ch2 : chars) {
                    seq = getCharSequenceOf(ch0, ch1, ch2);
                    test(seq, false, CharSequenceUtils::containsSurrogatePairsOnly);

                    for (final char ch3 : chars) {
                        seq = getCharSequenceOf(ch0, ch1, ch2, ch3);
                        expected = Character.isSurrogatePair(ch0, ch1) && Character.isSurrogatePair(ch2, ch3);

                        test(seq, expected, CharSequenceUtils::containsSurrogatePairsOnly);
                    }
                }
            }
        }
    }

    @Test
    public void testContainsUnattachedSurrogates() {
        test(null, false, CharSequenceUtils::containsUnattachedSurrogates);
        test("", false, CharSequenceUtils::containsUnattachedSurrogates);

        CharSequence seq;
        boolean expected;

        for (final char ch0 : chars) {
            seq = getCharSequenceOf(ch0);
            expected = Character.isSurrogate(ch0);

            test(seq, expected, CharSequenceUtils::containsUnattachedSurrogates);

            for (final char ch1 : chars) {
                seq = getCharSequenceOf(ch0, ch1);
                expected = (Character.isSurrogate(ch0) || Character.isSurrogate(ch1)) && !Character.isSurrogatePair(ch0, ch1);

                test(seq, expected, CharSequenceUtils::containsUnattachedSurrogates);

                for (final char ch2 : chars) {
                    seq = getCharSequenceOf(ch0, ch1, ch2);

                    if (!Character.isSurrogate(ch0) && !Character.isSurrogate(ch1) && !Character.isSurrogate(ch2)) {
                        expected = false;
                    } else if (Character.isSurrogate(ch0) && !Character.isSurrogate(ch1) && !Character.isSurrogate(ch2)) {
                        expected = true;
                    } else if (!Character.isSurrogate(ch0) && Character.isSurrogate(ch1) && !Character.isSurrogate(ch2)) {
                        expected = true;
                    } else if (!Character.isSurrogate(ch0) && !Character.isSurrogate(ch1) && Character.isSurrogate(ch2)) {
                        expected = true;
                    } else if (Character.isSurrogate(ch0) && Character.isSurrogate(ch1) && !Character.isSurrogate(ch2) && !Character.isSurrogatePair(ch0, ch1)) {
                        expected = true;
                    } else if (!Character.isSurrogate(ch0) && Character.isSurrogate(ch1) && Character.isSurrogate(ch2) && !Character.isSurrogatePair(ch1, ch2)) {
                        expected = true;
                    } else if (Character.isSurrogate(ch0) && !Character.isSurrogate(ch1) && Character.isSurrogate(ch2)) {
                        expected = true;
                    } else if (Character.isSurrogate(ch0) && Character.isSurrogate(ch1) && Character.isSurrogate(ch2) && (!Character.isSurrogatePair(ch0, ch1) || !Character.isSurrogatePair(ch1, ch2))) {
                        expected = true;
                    } else {
                        expected = false;
                    }

                    test(seq, expected, CharSequenceUtils::containsUnattachedSurrogates);
                }
            }
        }
    }

    @Test
    public void testTrim() {
        CharSequence seq;
        CharSequence res;

        String expected;

        String assertionError;

        int target = 0;

        for (final int target1 : targets) {
            target = target | target1;
        }

        seq = null;
        expected = null;
        res = CharSequenceUtils.trim(seq, target);
        assertionError = getAssertionError(seq, expected, res);
        Assert.assertEquals(assertionError, expected, res);

        expected = "test";

        for (final char ch : chars) {
            seq = ch + expected + ch;
            res = CharSequenceUtils.trim(seq, target);

            if (!expected.contentEquals(res)) {
                assertionError = getAssertionError(seq, expected, res);
                Assert.fail(assertionError);
            }
        }

        seq = " \t\n\r" + expected + "\r\n\t ";
        res = CharSequenceUtils.trim(seq);
        assertionError = getAssertionError(seq, expected, res);
        Assert.assertEquals(assertionError, expected, res);

        seq = null;
        expected = null;
        res = CharSequenceUtils.trim(seq);
        assertionError = getAssertionError(seq, expected, res);
        Assert.assertEquals(assertionError, expected, res);
    }

    private void test(final CharSequence seq, final boolean expected, final Function<CharSequence, Boolean> target) {
        final boolean result = target.apply(seq);

        if (expected != result) {
            final String error = getAssertionError(seq, expected, result);
            Assert.fail(error);
        }
    }

    private CharSequence getCharSequenceOf(char... chars) {
        return new String(chars);
    }

    private String getAssertionError(final CharSequence seq, final boolean expected, final boolean result) {
        final StringBuilder rep;

        if (seq == null) {
            rep = null;
        } else {
            rep = new StringBuilder();

            char ch;
            String hex;

            for (int i = 0; i < seq.length(); i++) {
                if (i > 0) {
                    rep.append(' ');
                }

                ch = seq.charAt(i);
                hex = String.format("U+%04x", (int) ch);

                rep.append(hex);
            }
        }

        return "Char sequence '" + rep + "' test failed, expected " + expected + ", given " + result;
    }

    private String getAssertionError(final CharSequence seq, final CharSequence expected, final CharSequence result) {
        char ch;
        String hex;

        final StringBuilder seqRep;

        if (seq == null) {
            seqRep = null;
        } else {
            seqRep = new StringBuilder();

            for (int i = 0; i < seq.length(); i++) {
                if (i > 0) {
                    seqRep.append(' ');
                }

                ch = seq.charAt(i);
                hex = String.format("U+%04x", (int) ch);

                seqRep.append(hex);
            }
        }

        final StringBuilder expectedRep;

        if (expected == null) {
            expectedRep = null;
        } else {
            expectedRep = new StringBuilder();

            for (int i = 0; i < expected.length(); i++) {
                if (i > 0) {
                    expectedRep.append(' ');
                }

                ch = expected.charAt(i);
                hex = String.format("U+%04x", (int) ch);

                expectedRep.append(hex);
            }
        }

        final StringBuilder resultRep;

        if (result == null) {
            resultRep = null;
        } else {
            resultRep = new StringBuilder();

            for (int i = 0; i < result.length(); i++) {
                if (i > 0) {
                    resultRep.append(' ');
                }

                ch = result.charAt(i);
                hex = String.format("U+%04x", (int) ch);

                resultRep.append(hex);
            }
        }

        return "Char sequence '" + seqRep + "' test failed, expected " + expectedRep + ", given " + resultRep;
    }

}
