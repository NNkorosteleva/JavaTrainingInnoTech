package ru.vtb.training.korostelevaan.lesson6;

import ru.vtb.training.korostelevaan.lesson6.util.Random;
import ru.vtb.training.korostelevaan.lesson6.util.StopWatch;

import java.util.HashMap;

public class AssociativeArrayPerformanceTest {

    private final static int COUNT_REPEAT = 10000000;
    private static HashMap<Integer, String> hashMap = new HashMap<>();
    private static AssociativeArray<Integer, String> associativeArray = new AssociativeArray<>();

    public static void main(String[] args) {
        System.out.println("AssociativeArray.add(K key, V val): " + add(1) + " ms");
        System.out.println("HashMap.put(K key, V val): " + add(2) + " ms");
        System.out.println("AssociativeArray.get(K key): " + get(1) + " ms");
        System.out.println("HashMap.get(K key): " + get(2) + " ms");
        System.out.println("AssociativeArray.remove(K key): " + remove(1) + " ms");
        System.out.println("HashMap.remove(K key): " + remove(2) + " ms");

        System.out.println("AssociativeArray.add(K key, V val): " + addRandom(1) + " ms");
        System.out.println("HashMap.put(K key, V val): " + addRandom(2) + " ms");
        System.out.println("AssociativeArray.get(K key): " + getRandom(1) + " ms");
        System.out.println("HashMap.get(K key): " + getRandom(2) + " ms");
        System.out.println("AssociativeArray.remove(K key): " + removeRandom(1) + " ms");
        System.out.println("HashMap.remove(K key): " + removeRandom(2) + " ms");
    }

    public static long add(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                associativeArray.add(i, "1");
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                hashMap.put(i, "2");
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long addRandom(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                associativeArray.add(Random.random(), "1");
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                hashMap.put(Random.random(), "2");
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long get(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                associativeArray.get(i);
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                hashMap.get(i);
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long getRandom(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                associativeArray.get(Random.random());
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                hashMap.get(Random.random());
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long remove(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                associativeArray.remove(i);
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                hashMap.remove(i);
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long removeRandom(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                associativeArray.remove(Random.random());
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                hashMap.remove(Random.random());
            }
        }
        return StopWatch.getElapsedTime(start);
    }

}
