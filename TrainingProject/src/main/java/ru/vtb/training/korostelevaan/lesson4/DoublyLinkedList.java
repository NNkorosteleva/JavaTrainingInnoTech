package ru.vtb.training.korostelevaan.lesson4;

import java.util.ConcurrentModificationException;
import java.util.List;

public class DoublyLinkedList<V> extends AbstractList<V> implements List<V> {

    private int size = 0;
    private Element<V> first;
    private Element<V> last;

    /**
     * Добавляет значение val нового элемента в конец списка.
     *
     * @param val значение нового элемента
     * @return возвращает true, если объект добавился
     */
    @Override
    public boolean add(V val) {
        Element<V> element = new Element<>(last, null, val);
        if (first == null) {
            first = element;
        } else {
            last.next = element;
        }
        last = element;
        size++;
        return true;
    }

    /**
     * Добавляет новый элемент со значением element на место index, а все элементы которые находятся от него справа - сдвигаются
     *
     * @param index   индекс элемента списка
     * @param element значение элемента списка
     * @throws IndexOutOfBoundsException если index выходит за границы списка
     */
    @Override
    public void add(int index, V element) {
        checkSizeOfList(index);
        if (index == size - 1) {
            add(element);
        } else {
            Element<V> next = getElement(index);
            Element<V> prev = next.prev;
            Element<V> newElement = new Element<>(prev, next, element);
            if (prev == null) {
                first = newElement;
            } else {
                prev.next = newElement;
                next.prev = newElement;
            }
            size++;
        }
    }

    /**
     * Устанавливает значение element элемента на index место списка
     *
     * @param index   индекс элемента списка
     * @param element значение элемента списка
     * @return элемент на месте которого установлен новый элемент или null в случае если элемента не было
     * @throws IndexOutOfBoundsException если index выходит за границы списка
     */
    @Override
    public V set(int index, V element) {
        checkSizeOfList(index);
        Element<V> e = getElement(index);
        V returnVal = e.val;
        e.val = element;
        return returnVal;
    }

    /**
     * Удаляет объект o из списка
     *
     * @param o удаляемый объект
     * @return true если объект удалось найти и удалить, false если объект не удалось найти
     */
    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    /**
     * Удаляет элемент с индексом i
     *
     * @param index индекс элемента списка
     * @return значение удалённого элемента
     * @throws IndexOutOfBoundsException если index выходит за границы списка
     */
    @Override
    public V remove(int index) {
        checkSizeOfList(index);
        Element<V> e = getElement(index);
        if (e.prev == null) {
            first = e.next;
        } else if (e.next == null) {
            last = e.prev;
        } else {
            e.prev.next = e.next;
            e.next.prev = e.prev;
        }
        size--;
        return e.val;
    }

    /**
     * Возвращает значение элемента с индексом index
     *
     * @param index индекс элемента списка
     * @return значение элемента списка с индексом index
     * @throws IndexOutOfBoundsException если index выходит за границы списка
     */
    @Override
    public V get(int index) {
        checkSizeOfList(index);
        Element<V> e = getElement(index);
        return e.val;
    }

    /**
     * Возвращает размерность списка
     *
     * @return размерность списка
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Возвращает индекс элемента списка
     *
     * @param o элемент списка
     * @return индекс элемента, если элемент удалось найти; -1 в случае, если не удалось найти элемент
     */
    @Override
    public int indexOf(Object o) {
        Element<V> e = first;
        int index = 0;
        while (e != null) {
            if (e.val.equals(o)) {
                return index;
            }
            index++;
            e = e.next;
        }
        return -1;
    }

    /**
     * Создает итератор списка
     *
     * @return итератор списка
     */
    public ListIteratorImpl listIterator() {
        return new ListIteratorImpl(first, last);
    }

    /**
     * Возвращает элемент с индексом index
     *
     * @param index индекс элемента списка
     * @return элемент списка с индексом index
     */
    private Element<V> getElement(int index) {
        Element<V> e;
        if (index < size() / 2) {
            e = first;
            for (int i = 0; i < index; i++) {
                e = e.next;
            }
        } else {
            e = last;
            for (int i = size() - 1; i > index; i--) {
                e = e.prev;
            }
        }
        return e;
    }

    /**
     * Проверка входного индекса списка не выходит ли за границы списка
     *
     * @param index индекс элемента списка
     * @return true если индекс index находится в пределах границ списка, false если index выходит за границы списка
     * @throws IndexOutOfBoundsException если index выходит за границы списка
     */
    private boolean checkSizeOfList(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Specified number is went out beyond dimension [0, " + (size() - 1) + "]: " + index);
        }
        return true;
    }

    /**
     * Класс, который описывает структуру элемента связного списка
     *
     * @param <V> тип элемента списка
     */
    private static class Element<V> {
        private Element<V> prev;
        private Element<V> next;
        private V val;

        public Element(Element<V> prev, Element<V> next, V val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }

    /**
     * Класс итератор, который позволяет перемещаться по списку в любом направлении,
     * изменять список во время итерации и получать текущую позицию итератора в списке.
     *
     * @param <V> тип элемента списка
     */
    private class ListIteratorImpl<V> implements java.util.ListIterator<V> {

        private Element<V> first;
        private Element<V> last;
        private int currentIndexIterator;
        private int expectedCurrentIndex = size;
        private DoublyLinkedList<V> doublyLinkedList = (DoublyLinkedList<V>) DoublyLinkedList.this;

        public ListIteratorImpl(Element<V> first, Element<V> last) {
            this.first = first;
            this.last = last;
        }

        /**
         * Возвращает true, если следующий элемент списка не null при обходе списка в прямом направлении
         *
         * @return true если следующий элемент списка не null, false если элемент null
         */
        @Override
        public boolean hasNext() {
            return currentIndexIterator < expectedCurrentIndex;
        }

        /**
         * Возвращает следующее значение элемента списка и передвигает вправо курсор списка
         *
         * @return следующее значение элемента списка
         * @throws ConcurrentModificationException если список был модифицирован вне итератора
         * @throws IndexOutOfBoundsException       если currentIndexIterator выходит за границы списка
         */
        @Override
        public V next() {
            checkForModification();
            if ((currentIndexIterator >= size) || (currentIndexIterator < 0)) {
                throw new IndexOutOfBoundsException("Specified number is went out beyond dimension [0, " + (size - 1) + "]: " + currentIndexIterator);
            } else {
                V returnVal = first.val;
                first = first.next;
                currentIndexIterator++;
                return returnVal;
            }
        }

        /**
         * Возвращает true, если предыдущий элемент списка не null при обходе списка в обратном направлении
         *
         * @return true если предыдущий элемент списка не null, false если элемент null
         */
        @Override
        public boolean hasPrevious() {
            return currentIndexIterator > 0;
        }

        /**
         * Возвращает предыдущее значение элемента списка и передвигает влево курсор списка
         *
         * @return предыдущее значение элемента списка
         * @throws ConcurrentModificationException если список был модифицирован вне итератора
         * @throws IndexOutOfBoundsException       если currentIndexIterator выходит за границы списка
         */
        @Override
        public V previous() {
            checkForModification();
            V returnVal = null;
            if ((currentIndexIterator >= size) || (currentIndexIterator <= 0)) {
                throw new IndexOutOfBoundsException("Specified number is went out beyond dimension (0, " + (size - 1) + "]: " + currentIndexIterator);
            } else {
                for (int i = 0; i <= (size - currentIndexIterator); i++) {
                    returnVal = last.val;
                    last = last.prev;
                }
                currentIndexIterator--;
                return returnVal;
            }
        }

        /**
         * Возвращает индекс следующего элемента
         *
         * @return индекс следующего элемента
         */
        @Override
        public int nextIndex() {
            if ((currentIndexIterator >= size) || (currentIndexIterator < 0)) {
                throw new IndexOutOfBoundsException("Specified number is went out beyond dimension [0, " + (size - 1) + "): " + currentIndexIterator);
            }
            return currentIndexIterator;
        }

        /**
         * Возвращает индекс предыдущего элемента
         *
         * @return индекс предыдущего элемента
         */
        @Override
        public int previousIndex() {
            if ((currentIndexIterator > size) || (currentIndexIterator <= 0)) {
                throw new IndexOutOfBoundsException("Specified number is went out beyond dimension (0, " + (size - 1) + "]: " + currentIndexIterator);
            }
            return currentIndexIterator - 1;
        }

        /**
         * Удаляет из списка последний элемент
         *
         * @throws ConcurrentModificationException если список был модифицирован вне итератора
         * @throws NullPointerException            если список пустой
         */
        @Override
        public void remove() {
            checkForModification();
            if (size == 0) {
                throw new NullPointerException("List is empty");
            }
            DoublyLinkedList.this.remove(size - 1);
            expectedCurrentIndex = size;
            first = (Element<V>) DoublyLinkedList.this.first;
            last = (Element<V>) DoublyLinkedList.this.last;
        }

        /**
         * Заменяет в списке последний элемент
         *
         * @param v значение элемента
         * @throws ConcurrentModificationException если список был модифицирован вне итератора
         * @throws NullPointerException            если список пустой
         */
        @Override
        public void set(V v) {
            checkForModification();
            if (size == 0) {
                throw new NullPointerException("List is empty");
            }
            doublyLinkedList.set(size - 1, v);
            expectedCurrentIndex = size;
            first = (Element<V>) DoublyLinkedList.this.first;
            last = (Element<V>) DoublyLinkedList.this.last;
        }

        /**
         * Добавляет в список последний элемент
         *
         * @param v значение элемента
         * @throws ConcurrentModificationException если список был модифицирован вне итератора
         */
        @Override
        public void add(V v) {
            checkForModification();
            doublyLinkedList.add(v);
            expectedCurrentIndex = size;
            first = (Element<V>) DoublyLinkedList.this.first;
            last = (Element<V>) DoublyLinkedList.this.last;
        }

        /**
         * Проверка модификации списка вне итератора
         *
         * @throws ConcurrentModificationException если список был модифицирован вне итератора
         */
        private void checkForModification() {
            if (expectedCurrentIndex != size) {
                throw new ConcurrentModificationException("List has been changed");
            }
        }
    }
}
