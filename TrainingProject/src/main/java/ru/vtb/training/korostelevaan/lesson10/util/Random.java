package ru.vtb.training.korostelevaan.lesson10.util;

public class Random {
    private final static int MAX_LIMIT = 1000;

    public static int random() {
        return (int) (Math.random() * MAX_LIMIT);
    }
}
