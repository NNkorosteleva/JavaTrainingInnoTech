package ru.vtb.training.korostelevaan.lesson10;

import ru.vtb.training.korostelevaan.lesson10.util.Random;
import ru.vtb.training.korostelevaan.lesson10.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuickSortPerformanceTest {

    private final static int SIZE = 10000000;
    private static int[] array = new int[SIZE];
    private static ArrayList<Integer> array3 = new ArrayList<>(SIZE);


    public static void main(String[] args) {
        System.out.println("QuickSort.sort() for sort array: " + sortForSortArray(1) + " ms");
        System.out.println("Arrays.sort() for sort array: " + sortForSortArray(2) + " ms");
        System.out.println("Collections.sort() for sort array: " + sortForSortArray(3) + " ms");

        System.out.println("QuickSort.sort() for worst case: " + sortForWorstCase(1) + " ms");
        System.out.println("Arrays.sort() for worst case: " + sortForWorstCase(2) + " ms");
        System.out.println("Collections.sort() for worst case: " + sortForWorstCase(3) + " ms");

        System.out.println("QuickSort.sort() for random: " + sortForRandom(1) + " ms");
        System.out.println("Arrays.sort() for random: " + sortForRandom(2) + " ms");
        System.out.println("Collections.sort() for random: " + sortForRandom(3) + " ms");
    }

    public static long sortForSortArray(int numOfTest) {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
            array3.add(i);
        }

        int[] array1 = array.clone();
        int[] array2 = array.clone();

        long start = StopWatch.start();
        if (numOfTest == 1) {
            QuickSort.sort(array1);
        }
        if (numOfTest == 2) {
            Arrays.sort(array2);
        }
        if (numOfTest == 3) {
            Collections.sort(array3);
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long sortForWorstCase(int numOfTest) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
            array3.add(array.length - i);
        }

        int[] array1 = array.clone();
        int[] array2 = array.clone();

        long start = StopWatch.start();
        if (numOfTest == 1) {
            QuickSort.sort(array1);
        }
        if (numOfTest == 2) {
            Arrays.sort(array2);
        }
        if (numOfTest == 3) {
            Collections.sort(array3);
        }
        return StopWatch.getElapsedTime(start);
    }

    public static long sortForRandom(int numOfTest) {
        for (int i = 0; i < array.length; i++) {
            array[i] = Random.random();
            array3.add(array[i]);
        }

        int[] array1 = array.clone();
        int[] array2 = array.clone();

        long start = StopWatch.start();
        if (numOfTest == 1) {
            QuickSort.sort(array1);
        }
        if (numOfTest == 2) {
            Arrays.sort(array2);
        }
        if (numOfTest == 3) {
            Collections.sort(array3);
        }
        return StopWatch.getElapsedTime(start);
    }
}
