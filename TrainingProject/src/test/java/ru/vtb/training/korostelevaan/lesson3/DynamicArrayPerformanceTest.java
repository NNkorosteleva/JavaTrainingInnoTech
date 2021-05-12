package ru.vtb.training.korostelevaan.lesson3;

import ru.vtb.training.korostelevaan.lesson3.util.StopWatch;

import java.util.ArrayList;

public class DynamicArrayPerformanceTest {

    private final static int COUNT_REPEAT = 200000;
    private static DynamicArray dynamicArray = new DynamicArray();
    private static ArrayList arrayList = new ArrayList();

    public static void main(String[] args) {
        System.out.println("DynamicArray.add(Object e): " + add(1) + " ms");
        System.out.println("ArrayList.add(Object e): " + add(2) + " ms");
        System.out.println("DynamicArray.add(int i, Object e) in begin: " + addToBeginning(1) + " ms");
        System.out.println("ArrayList.add(int i, Object e) in begin: " + addToBeginning(2) + " ms");
        System.out.println("DynamicArray.add(int i, Object e) in middle: " + addToMiddle(1) + " ms");
        System.out.println("ArrayList.add(int i, Object e) in middle: " + addToMiddle(2) + " ms");
        System.out.println("DynamicArray.add(int i, Object e) in end: " + addToEnd(1) + " ms");
        System.out.println("ArrayList.add(int i, Object e) in end: " + addToEnd(2) + " ms");
        System.out.println("DynamicArray.remove(Object e): " + remove(1) + " ms");
        System.out.println("ArrayList.remove(Object e): " + remove(2) + " ms");
        System.out.println("DynamicArray.remove(int i) in begin: " + removeFromBeginning(1) + " ms");
        System.out.println("ArrayList.remove(int i) in begin: " + removeFromBeginning(2) + " ms");
        System.out.println("DynamicArray.remove(int i) in middle: " + removeFromMiddle(1) + " ms");
        System.out.println("ArrayList.remove(int i) in middle: " + removeFromMiddle(2) + " ms");
        System.out.println("DynamicArray.remove(int i) in end: " + removeFromEnd(1) + " ms");
        System.out.println("ArrayList.remove(int i) in end: " + removeFromEnd(2) + " ms");
    }

    public static long add(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                dynamicArray.add("1");
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                arrayList.add("1");
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long addToBeginning(int numOfTest) {

        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                dynamicArray.add(0, "2");
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                arrayList.add(0, "2");
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long addToMiddle(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                dynamicArray.add(100, "3");
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                arrayList.add(100, "3");
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long addToEnd(int numOfTest) {

        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                dynamicArray.add(dynamicArray.size() - 1, "4");
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                arrayList.add(arrayList.size() - 1, "4");
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long remove(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                dynamicArray.remove("1");
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                arrayList.remove("1");
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long removeFromBeginning(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                dynamicArray.remove(0);
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                arrayList.remove(0);
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long removeFromMiddle(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                dynamicArray.remove(100);
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                arrayList.remove(100);
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long removeFromEnd(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                dynamicArray.remove(dynamicArray.size() - 1);
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                arrayList.remove(arrayList.size() - 1);
            }
        }
        return StopWatch.getElapsedTime(start);
    }
}
