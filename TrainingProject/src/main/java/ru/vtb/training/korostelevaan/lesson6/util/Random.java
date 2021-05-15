package ru.vtb.training.korostelevaan.lesson6.util;

public class Random {
    private final static int MAX_LIMIT = 10000000;

    public static int random() {
        return (int) Math.random() * MAX_LIMIT;
    }
}
