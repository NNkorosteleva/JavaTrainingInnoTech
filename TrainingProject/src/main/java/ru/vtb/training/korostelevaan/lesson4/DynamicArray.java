package ru.vtb.training.korostelevaan.lesson4;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;

public class DynamicArray<V> extends AbstractList<V> implements List<V> {

    private static final int DEFAULT_SIZE = 10;
    private static final Object[] EMPTY_ELEMENT = {};
    private V[] data;
    private int currentIndex;

    /**
     * Если при создании объекта DynamicArray не указан размер массива, то по умолчанию устанавливается размерность 10
     */
    public DynamicArray() {
        this.data = (V[]) new Object[DEFAULT_SIZE];
    }

    /**
     * Создаем объект DynamicArray с указанной размерностью
     *
     * @param initialSize - размерность массива
     * @throws IllegalArgumentException если initialSize < 0
     */
    public DynamicArray(int initialSize) {
        if (initialSize > 0) {
            this.data = (V[]) new Object[initialSize];
        } else if (initialSize == 0) {
            this.data = (V[]) EMPTY_ELEMENT;
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
    public boolean add(V e) {
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
    public void add(int i, V e) {
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
    public V set(int i, V e) {
        checkSizeOfArray(i);
        if (data[i] == null) {
            data[i] = e;
            return null;
        } else {
            V temp = data[i];
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
    public V get(int i) {
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
    public V remove(int i) {
        checkSizeOfArray(i);
        currentIndex--;
        V temp = data[i];
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
    public V[] toArray() {
        return Arrays.copyOf(data, size());
    }

    /**
     * Создает итератор массива
     *
     * @return итератор массива
     */
    public ListIteratorImpl listIterator() {
        return new DynamicArray.ListIteratorImpl(data);
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
        data = (V[]) new Object[(int) Math.round(1.5 * size())];
        System.arraycopy(temp, 0, data, 0, temp.length);
        return data;
    }

    /**
     * Класс итератор, который позволяет перемещаться по массиву в любом направлении,
     * изменять список во время итерации и получать текущую позицию итератора в списке.
     *
     * @param <V> тип элемента списка
     */
    private class ListIteratorImpl<V> implements java.util.ListIterator<V> {
        private V[] data;
        private int currentIndexIterator;
        private int expectedCurrentIndex = currentIndex;
        private DynamicArray<V> dynamicArray = (DynamicArray<V>) DynamicArray.this;
        ;

        ListIteratorImpl(V[] data) {
            this.data = data;
        }

        /**
         * Возвращает true, если следующий элемент массива не null при обходе массива в прямом направлении
         *
         * @return true если следующий элемент массива не null, false если элемент null
         */
        @Override
        public boolean hasNext() {
            return currentIndexIterator < data.length;
        }

        /**
         * Возвращает следующий элемента массива и передвигает вправо курсор массива
         *
         * @return следующий элемент массива
         * @throws ConcurrentModificationException если список был модифицирован вне итератора
         * @throws ArrayIndexOutOfBoundsException  если i выходит за границы списка
         */
        @Override
        public V next() {
            checkForModification();
            int i = currentIndexIterator;
            if ((i > data.length) || (i < 0)) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (i < data.length) {
                currentIndexIterator++;
            }
            return data[i];
        }

        /**
         * Возвращает true, если предыдущий элемент массива не null при обходе массива в обратном направлении
         *
         * @return true если предыдущий элемент массива не null, false если элемент null
         */
        @Override
        public boolean hasPrevious() {
            return currentIndexIterator > 0;
        }

        /**
         * Возвращает предыдущий элемента массива и передвигает влево курсор массива
         *
         * @return предыдущее значение элемента списка
         * @throws ConcurrentModificationException если список был модифицирован вне итератора
         * @throws ArrayIndexOutOfBoundsException  если i выходит за границы списка
         */
        @Override
        public V previous() {
            checkForModification();
            int i = currentIndexIterator - 1;
            if ((i > data.length) || (i < 0)) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (i >= 0) {
                currentIndexIterator--;
            }
            return data[i];
        }

        /**
         * Возвращает индекс следующего элемента
         *
         * @return индекс следующего элемента
         */
        @Override
        public int nextIndex() {
            return currentIndexIterator;
        }

        /**
         * Возвращает индекс предыдущего элемента
         *
         * @return индекс предыдущего элемента
         */
        @Override
        public int previousIndex() {
            return currentIndexIterator - 1;
        }

        /**
         * Удаляет из массива последний элемент
         *
         * @throws ConcurrentModificationException если массив был модифицирован вне итератора
         * @throws ArrayIndexOutOfBoundsException  если currentIndex выходит за границы массива
         */
        @Override
        public void remove() {
            checkForModification();
            if (currentIndex < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            DynamicArray.this.remove(currentIndex - 1);
            expectedCurrentIndex = currentIndex;
            data = (V[]) DynamicArray.this.data;
        }

        /**
         * Заменяет в массиве последний элемент
         *
         * @param o значение элемента
         * @throws ConcurrentModificationException если список был модифицирован вне итератора
         * @throws ArrayIndexOutOfBoundsException  если currentIndex выходит за границы массива
         */
        @Override
        public void set(V o) {
            checkForModification();
            if (currentIndex < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            dynamicArray.set(currentIndex - 1, o);
            expectedCurrentIndex = currentIndex;
            data = (V[]) DynamicArray.this.data;
        }

        /**
         * Добавляет в массив последний элемент
         *
         * @param o значение элемента
         * @throws ConcurrentModificationException если список был модифицирован вне итератора
         */
        @Override
        public void add(V o) {
            checkForModification();
            dynamicArray.add(o);
            expectedCurrentIndex = currentIndex;
            data = (V[]) DynamicArray.this.data;
        }

        /**
         * Проверка модификации массива вне итератора
         *
         * @throws ConcurrentModificationException если массив был модифицирован вне итератора
         */
        private void checkForModification() {
            if (expectedCurrentIndex != currentIndex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
