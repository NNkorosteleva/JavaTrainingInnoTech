package ru.vtb.training.korostelevaan.lesson4;

import ru.vtb.training.korostelevaan.lesson4.util.StopWatch;
import java.util.LinkedList;

public class DoublyLinkedListPerformanceTest {

    private final static int COUNT_REPEAT = 100000;
    private static DoublyLinkedList<String> doublyLinkedList = new DoublyLinkedList<>();
    private static LinkedList<String> list = new LinkedList<>();

    public static void main(String[] args) {
        System.out.println("DoublyLinkedList.add(Object e): " + add(1) + " ms");
        System.out.println("LinkedList.add(Object e): " + add(2) + " ms");
        System.out.println("DoublyLinkedList.add(int i, Object e) in begin: " + addToBeginning(1) + " ms");
        System.out.println("LinkedList.add(int i, Object e) in begin: " + addToBeginning(2) + " ms");
        System.out.println("DoublyLinkedList.add(int i, Object e) in middle: " + addToMiddle(1) + " ms");
        System.out.println("LinkedList.add(int i, Object e) in middle: " + addToMiddle(2) + " ms");
        System.out.println("DoublyLinkedList.add(int i, Object e) in end: " + addToEnd(1) + " ms");
        System.out.println("LinkedList.add(int i, Object e) in end: " + addToEnd(2) + " ms");
        System.out.println("DoublyLinkedList.remove(Object e): " + remove(1) + " ms");
        System.out.println("LinkedList.remove(Object e): " + remove(2) + " ms");
        System.out.println("DoublyLinkedList.remove(int i) in begin: " + removeFromBeginning(1) + " ms");
        System.out.println("LinkedList.remove(int i) in begin: " + removeFromBeginning(2) + " ms");
        System.out.println("DoublyLinkedList.remove(int i) in middle: " + removeFromMiddle(1) + " ms");
        System.out.println("LinkedList.remove(int i) in middle: " + removeFromMiddle(2) + " ms");
        System.out.println("DoublyLinkedList.remove(int i) in end: " + removeFromEnd(1) + " ms");
        System.out.println("LinkedList.remove(int i) in end: " + removeFromEnd(2) + " ms");
    }

    public static long add(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                doublyLinkedList.add("1");
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                list.add("1");
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long addToBeginning(int numOfTest) {

        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                doublyLinkedList.add(0, "2");
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                list.add(0, "2");
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long addToMiddle(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                doublyLinkedList.add(100, "3");
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                list.add(100, "3");
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long addToEnd(int numOfTest) {

        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                doublyLinkedList.add(doublyLinkedList.size() - 1, "4");
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                list.add(list.size() - 1, "4");
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long remove(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                doublyLinkedList.remove("1");
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                list.remove("1");
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long removeFromBeginning(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                doublyLinkedList.remove(0);
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                list.remove(0);
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long removeFromMiddle(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                doublyLinkedList.remove(100);
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                list.remove(100);
            }
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long removeFromEnd(int numOfTest) {
        long start = StopWatch.start();
        if (numOfTest == 1) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                doublyLinkedList.remove(doublyLinkedList.size() - 1);
            }
        }
        if (numOfTest == 2) {
            for (int i = 0; i < COUNT_REPEAT; i++) {
                list.remove(list.size() - 1);
            }
        }
        return StopWatch.getElapsedTime(start);
    }

}
