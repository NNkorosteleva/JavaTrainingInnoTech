package ru.vtb.training.korostelevaan.lesson3;

import java.util.Arrays;
import java.util.ConcurrentModificationException;

public class DynamicArray {

    private static final int DEFAULT_SIZE = 10;
    private static final Object[] EMPTY_ELEMENT = {};
    private Object[] data;
    private int currentIndex;

    /**
     * Если при создании объекта DynamicArray не указан размер массива, то по умолчанию устанавливается размерность 10
     */
    public DynamicArray() {
        this.data = new Object[DEFAULT_SIZE];
    }

    /**
     * Создаем объект DynamicArray с указанной размерностью
     *
     * @param initialSize - размерность массива
     * @throws IllegalArgumentException если initialSize < 0
     */
    public DynamicArray(int initialSize) {
        if (initialSize > 0) {
            this.data = new Object[initialSize];
        } else if (initialSize == 0) {
            this.data = EMPTY_ELEMENT;
        } else {
            throw new IllegalArgumentException("Specified initial capacity is negative: " + initialSize);
        }
    }

    /**
     * Добавляет элемент e в конец массива. Если массив переполнен, то увеличивается размерность в 1,5 раза
     *
     * @param e - элемент массива
     * @return возвращает true, если объект добавился
     */
    public boolean add(Object e) {
        if (currentIndex == size()) {
            grow();
        }
        data[currentIndex] = e;
        currentIndex++;
        return true;
    }

    /**
     * Добавляет элемент e на место i, а все элементы которые находятся от него справа - сдвигаются
     *
     * @param i - индекс элемента массива
     * @param e - элемент массива
     * @throws ArrayIndexOutOfBoundsException если i выходит за границы массива
     */
    public void add(int i, Object e) {
        checkSizeOfArray(i);
        if (i > currentIndex) {
            i = currentIndex;
        }
        currentIndex++;
        if (currentIndex == size()) {
            grow();
        }
        System.arraycopy(data, i, data, i + 1, currentIndex - i);
        data[i] = e;

    }

    /**
     * Устанавливает элемент e на i место массива
     *
     * @param i - индекс элемента массива
     * @param e - элемент массива
     * @return элемент на месте которого установлен новый элемент или null в случае если элемента не было
     * @throws ArrayIndexOutOfBoundsException если i выходит за границы массива
     */
    public Object set(int i, Object e) {
        checkSizeOfArray(i);
        if (data[i] == null) {
            data[i] = e;
            return null;
        } else {
            Object temp = data[i];
            data[i] = e;
            return temp;
        }
    }

    /**
     * Возвращает элемент с индексом i
     *
     * @param i - индекс элемента массива
     * @return объект массива с индексом i
     * @throws ArrayIndexOutOfBoundsException если i выходит за границы массива
     */
    public Object get(int i) {
        checkSizeOfArray(i);
        return data[i];
    }

    /**
     * Удаляет элемент с индексом i
     *
     * @param i - индекс элемента массива
     * @return удаленный элемент
     * @throws ArrayIndexOutOfBoundsException если i выходит за границы массива
     */
    public Object remove(int i) {
        checkSizeOfArray(i);
        currentIndex--;
        Object temp = data[i];
        if (i == size() - 1) {
            data[i] = null;
        } else {
            System.arraycopy(data, i + 1, data, i, currentIndex - i);
            data[currentIndex] = null;
        }
        return temp;
    }

    /**
     * Удаляет элемент e
     *
     * @param e - элемент массива
     * @return true если элемент удалось найти и удалить, false если элемент не удалось найти
     */
    public boolean remove(Object e) {
        int index;
        index = indexOf(e);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    /**
     * Возвращает размерность массива
     *
     * @return размерность массива
     */
    public int size() {
        return data.length;
    }

    /**
     * Возвращает индекс элемента массива
     *
     * @param e - элемент массива
     * @return индекс элемента, если элемент удалось найти; -1 в случае, если не удалось найти элемент
     */
    public int indexOf(Object e) {
        if (e == null) {
            for (int i = 0; i < size(); i++) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < currentIndex; i++) {
                if (data[i].equals(e)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Проверка, содержит ли массив указанный элемент
     *
     * @param e - элемент массива
     * @return true если элемент находится в массиве, false если элемент не находится в массиве
     */
    public boolean contains(Object e) {
        int index;
        index = indexOf(e);
        if (index == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Возвращает массив
     *
     * @return массив
     */
    public Object[] toArray() {
        return Arrays.copyOf(data, size());
    }

    /**
     * Проверка входного индекса массива не выходит ли за границы массива
     *
     * @param index - индекс массива
     * @return true если индекс находится в пределах границ массива, false если i выходит за границы массива
     * @throws ArrayIndexOutOfBoundsException если i выходит за границы массива
     */
    private boolean checkSizeOfArray(int index) {
        if (index < 0 || index >= size()) {
            throw new ArrayIndexOutOfBoundsException("Specified number is went out beyond dimension: " + index);
        }
        return true;
    }

    /**
     * Увеличивает размерность массива в 1,5 раз
     *
     * @return массив новой размерности
     */
    private Object[] grow() {
        Object[] temp = data.clone();
        data = new Object[(int) Math.round(1.5 * size())];
        System.arraycopy(temp, 0, data, 0, temp.length);
        return data;
    }

    public ListIterator listIterator() {
        return new DynamicArray.ListIterator(data);
    }

    private class ListIterator implements java.util.ListIterator {
        private Object[] data;
        private int currentIndexIterator;
        private int expectedCurrentIndex = currentIndex;

        ListIterator(Object[] data) {
            this.data = data;
        }

        @Override
        public boolean hasNext() {
            return currentIndexIterator < data.length;
        }

        @Override
        public Object next() {
            checkForModification();
            int i = currentIndexIterator;
            if ((i > data.length) && (i < 0)) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (i < data.length) {
                currentIndexIterator++;
            }
            return data[i];
        }

        @Override
        public boolean hasPrevious() {
            return currentIndexIterator > 0;
        }

        @Override
        public Object previous() {
            checkForModification();
            int i = currentIndexIterator - 1;
            if ((i > data.length) && (i < 0)) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (i >= 0) {
                currentIndexIterator--;
            }
            return data[i];
        }

        @Override
        public int nextIndex() {
            return currentIndexIterator;
        }

        @Override
        public int previousIndex() {
            return currentIndexIterator - 1;
        }

        @Override
        public void remove() {
            checkForModification();
            if (currentIndex < 0) {
                throw new IndexOutOfBoundsException();
            }
            DynamicArray.this.remove(currentIndex - 1);
            expectedCurrentIndex = currentIndex;
            data = DynamicArray.this.data;
        }

        @Override
        public void set(Object o) {
            checkForModification();
            if (currentIndex < 0) {
                throw new IndexOutOfBoundsException();
            }
            DynamicArray.this.set(currentIndex - 1, o);
            expectedCurrentIndex = currentIndex;
            data = DynamicArray.this.data;
        }

        @Override
        public void add(Object o) {
            checkForModification();
            DynamicArray.this.add(o);
            expectedCurrentIndex = currentIndex;
            data = DynamicArray.this.data;
        }

        private void checkForModification() {
            if (expectedCurrentIndex != currentIndex) {
                throw new ConcurrentModificationException();
            }
        }

    }
}
