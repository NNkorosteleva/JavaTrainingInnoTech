package ru.vtb.training.korostelevaan.lesson10;

import org.junit.jupiter.api.Test;
import ru.vtb.training.korostelevaan.lesson10.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class QuickSortTest {

    int[] array = new int[50000000];

    @Test
    void sort_MustReturnSortArray_ForSortArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        QuickSort.sort(array);
        assertThat(array).isSorted();
    }

    @Test
    void sort_MustReturnSortArray_ForWorstCase() {
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
        QuickSort.sort(array);
        assertThat(array).isSorted();
    }

    @Test
    void sort_MustReturnSortArray_ForRandom() {
        for (int i = 0; i < array.length; i++) {
            array[i] = Random.random();
        }
        QuickSort.sort(array);
        assertThat(array).isSorted();
    }
}