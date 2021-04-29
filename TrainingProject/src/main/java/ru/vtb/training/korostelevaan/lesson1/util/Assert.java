package ru.vtb.training.korostelevaan.lesson1.util;

/**
 * Класс позволяет сравнить фактический результат работы метода с ожидаемым значением
 */
public class Assert {

    public static void assertEquals(String message, Object expected, Object actual) throws AssertionFailedError {
        if (expected.equals(actual)) {
            System.out.println(success(message, actual));
        } else {
            fail(expected, actual);
        }
    }

    public static void assertEqualsSwap(String message, Object expectedA, Object expectedB, Object actualA, Object actualB) throws AssertionFailedError {
        if (expectedA.equals(actualB) && expectedB.equals(actualA)) {
            System.out.println(successSwap(message, expectedA, expectedB, actualA, actualB));
        } else {
            failSwap(expectedA, expectedB, actualA, actualB);
        }
    }

    private static void fail(Object expected, Object actual) throws AssertionFailedError {
        throw new AssertionFailedError(expected, actual);
    }

    private static String success(String message, Object actual) {
        return "TRUE! " + message + actual;
    }

    private static String successSwap(String message, Object expectedA, Object expectedB, Object actualA, Object actualB) {
        return "TRUE! " + message + "\nBEFORE! a = " + expectedA + "; b = " + expectedB + "\nSWAP! a = " + actualA + "; b = " + actualB;
    }

    private static void failSwap(Object expectedA, Object expectedB, Object actualA, Object actualB) throws AssertionFailedError {
        throw new AssertionFailedError(expectedA, expectedB, actualA, actualB);
    }

}
