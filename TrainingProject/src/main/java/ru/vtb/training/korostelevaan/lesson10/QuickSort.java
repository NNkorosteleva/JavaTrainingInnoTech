package ru.vtb.training.korostelevaan.lesson10;

public class QuickSort {

    /**
     * метод, который вызывает сортировку QuickSort для массива array
     *
     * @param array массив
     */
    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    /**
     * рекурсивный метод сортировки - QuickSort
     *
     * @param array       массив
     * @param leftBorder  левая граница = начало массива
     * @param rightBorder правая граница = конец массива
     */
    private static void sort(int[] array, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = array[(leftMarker + rightMarker) / 2];
        do {
            while (array[leftMarker] < pivot) {
                leftMarker++;
            }
            while (array[rightMarker] > pivot) {
                rightMarker--;
            }
            if (leftMarker <= rightMarker) {
                if (leftMarker < rightMarker) {
                    array[leftMarker] += array[rightMarker];
                    array[rightMarker] = array[leftMarker] - array[rightMarker];
                    array[leftMarker] = array[leftMarker] - array[rightMarker];
                }
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        if (leftMarker < rightBorder) {
            sort(array, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            sort(array, leftBorder, rightMarker);
        }
    }
}
