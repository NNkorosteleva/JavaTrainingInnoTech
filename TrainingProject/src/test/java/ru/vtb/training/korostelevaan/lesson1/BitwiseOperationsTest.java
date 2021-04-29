package ru.vtb.training.korostelevaan.lesson1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vtb.training.korostelevaan.lesson1.util.Assert;
import ru.vtb.training.korostelevaan.lesson1.util.AssertionFailedError;

class BitwiseOperationsTest {

    BitwiseOperations bitwise = new BitwiseOperations();

    private static int n, m, a;

    @BeforeEach
    public void setUp() {
        n = 3;
        m = 4;
        a = 13;
    }

    @Test
    void powerTwo() throws AssertionFailedError {
        String message = "2^" + n + " = ";
        int expected = 8;
        int actual = bitwise.powerTwo(n);

        Assert.assertEquals(message, expected, actual);
    }

    @Test
    void powerTwoSum() throws AssertionFailedError {
        String message = "2^" + n + " + 2^" + m + " = ";
        int expected = 24;
        int actual = bitwise.powerTwoSum(n, m);

        Assert.assertEquals(message, expected, actual);
    }

    @Test
    void resetLowerBits() throws AssertionFailedError {
        String message = "reset " + n + " lower bits values " + a + " : ";
        int expected = 8;
        int actual = bitwise.resetLowerBits(a, n);

        Assert.assertEquals(message, expected, actual);
    }

    @Test
    void setBitOne() throws AssertionFailedError {
        String message = "set " + a + "'s " + n + "-th bit with 1: ";
        int expected = 13;
        int actual = bitwise.setBitOne(a, n);

        Assert.assertEquals(message, expected, actual);
    }

    @Test
    void invertBit() throws AssertionFailedError {
        String message = "invert " + n + "-th bit: ";
        int expected = 5;
        int actual = bitwise.invertBit(a, n);

        Assert.assertEquals(message, expected, actual);
    }

    @Test
    void setBitZero() throws AssertionFailedError {
        String message = "set " + a + "'s " + n + "-th bit with 0: ";
        int expected = 5;
        int actual = bitwise.setBitZero(a, n);

        Assert.assertEquals(message, expected, actual);
    }

    @Test
    void lowerBits() throws AssertionFailedError {
        String message = "return " + n + " lower bits values " + a + " : ";
        int expected = 5;
        int actual = bitwise.lowerBits(a, n);

        Assert.assertEquals(message, expected, actual);
    }

    @Test
    void valueBit() throws AssertionFailedError {
        String message = "return " + n + "-th bit: ";
        int expected = 8;
        int actual = bitwise.valueBit(a, n);

        Assert.assertEquals(message, expected, actual);
    }

    @Test
    void representationInBits() throws AssertionFailedError {
        String message = "return " + n + "-th bit: ";
        String expected = "1101";
        String actual = bitwise.representationInBits(a);

        Assert.assertEquals(message, expected, actual);
    }
}