package ru.vtb.training.korostelevaan.lesson3.util;

public class StopWatch {

    public static long start() {
        return System.currentTimeMillis();
    }

    public static long getElapsedTime(long start) {
        long end = System.currentTimeMillis();
        long elapsed = end - start;
        return elapsed;
    }
}
