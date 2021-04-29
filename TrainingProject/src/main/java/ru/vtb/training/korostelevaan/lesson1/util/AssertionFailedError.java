package ru.vtb.training.korostelevaan.lesson1.util;

public class AssertionFailedError extends Throwable {

    public AssertionFailedError(Object expected, Object actual) {
        System.out.println("FALSE! expected:<" + expected + "> but was:<" + actual + ">");
    }

    public AssertionFailedError(Object expectedA, Object expectedB, Object actualA, Object actualB) {
        System.out.println("FALSE! expected:<" + " a = " + expectedB + "; b = " + expectedA + ">\nbut was:<" + " a = " + actualB + "; b = " + actualA + ">");
    }
}
