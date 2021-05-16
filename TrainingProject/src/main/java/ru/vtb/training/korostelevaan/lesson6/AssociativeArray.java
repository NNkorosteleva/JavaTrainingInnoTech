package ru.vtb.training.korostelevaan.lesson6;

public class AssociativeArray<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] table;
    private int size;
    private int capacity;
    private final float loadFactor;
    private int threshold;

    /**
     * Конструктор, который задает значения параметров capacity и loadFactor значения по умолчанию
     */
    public AssociativeArray() {
        table = (Node<K, V>[]) new Node[DEFAULT_INITIAL_CAPACITY];
        capacity = DEFAULT_INITIAL_CAPACITY;
        loadFactor = DEFAULT_LOAD_FACTOR;
        threshold = (int) (capacity * loadFactor);
    }

    /**
     * Конструктор, который задает пользовательское значение параметру capacity,
     * а параметр loadFactor задается по умолчанию
     *
     * @param capacity емкость хеш-таблицы
     * @throws IllegalArgumentException если входное значение capacity некорректно
     */
    public AssociativeArray(int capacity) {
        this.capacity = getCapacity(capacity);
        table = (Node<K, V>[]) new Node[this.capacity];
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.threshold = (int) (this.capacity * this.loadFactor);
    }

    /**
     * Конструктор, который задает пользовательское значения параметрам capacity и loadFactor
     *
     * @param capacity   емкость хеш-таблицы
     * @param loadFactor степень загруженности хеш-таблицы
     * @throws IllegalArgumentException если входные значения capacity и loadFactor некорректны
     */
    public AssociativeArray(int capacity, float loadFactor) {
        this.capacity = getCapacity(capacity);
        table = (Node<K, V>[]) new Node[this.capacity];
        this.loadFactor = getLoadFactor(loadFactor);
        this.threshold = (int) (this.capacity * this.loadFactor);
    }

    /**
     * Добавление элемента Node<K,V> ключ - значение в хеш-таблице
     * По значению ключа определяется hashCode и позиция в хеш-таблице
     * Если при добавлении нового элемента ключи не совпадают, то создается связный список
     * Если при добавлении нового элемента ключи совпадают, то значение val заменяется
     *
     * @param key ключ
     * @param val значение
     * @return значение старого элемента если он был, в остальных случаях возвращает null
     */
    public V add(K key, V val) {
        if (checkModTable()) {
            resize();
        }
        int hash = hashCode(key);
        int i = indexPosition(hash);
        if (isBasketEmpty(i)) {
            table[i] = new Node<>(hash, key, val, null);
            this.size++;
            return null;
        } else {
            Node<K, V> p = table[i];
            for (int binCount = 0; ; ++binCount) {
                if (p.key == key) {
                    V returnVal = p.value;
                    p.value = val;
                    return returnVal;
                }
                if (p.next == null) {
                    p.next = new Node<>(hash, key, val, null);
                    return null;
                } else {
                    p = p.next;
                }
            }
        }
    }

    /**
     * Возвращает значение элемента по ключу key
     *
     * @param key ключ
     * @return значение элемента, если элемент не находится с таким ключом, то возвращается null
     */
    public V get(K key) {
        int hash = hashCode(key);
        int i = indexPosition(hash);
        if (!isBasketEmpty(i)) {
            Node<K, V> p = table[i];
            do {
                if (p.key == key) {
                    return p.value;
                } else {
                    p = p.next;
                }
            } while (p != null);
        }
        return null;
    }

    /**
     * Удаляет элемент по ключу
     * По значению ключа определяется hashCode и позиция в хеш-таблице
     * Если в заданном бакете хеш-таблицы 1 значение, то ему присваивается null,
     * Если связный список, то ссылке предыдущего элемента присваивается null
     *
     * @param key ключ
     * @return значение элемента, если его удалось найти по ключу и удалить.
     * Возвращает null в случае, если элемент не удалось найти по заданному ключу
     */
    public V remove(K key) {
        int hash = hashCode(key);
        int i = indexPosition(hash);
        if (!isBasketEmpty(i)) {
            Node<K, V> p = table[i];
            Node<K, V> e = null;
            do {
                if (p.key == key) {
                    V val = p.value;
                    if (e == null) {
                        table[i] = p.next;
                        if (p.next == null) {
                            this.size--;
                        }
                    } else if ((e != null) && (p != null)) {
                        e.next = p.next;
                    }
                    return val;
                }
                e = p;
                p = p.next;
            } while (p != null);
        }
        return null;
    }

    /**
     * Возвращает размерность хеш-таблицы
     *
     * @return размерность хеш-таблицы
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * Возвращает кол-во занятых позиций хеш-таблицы
     *
     * @return кол-во занятых позиций хеш-таблицы
     */
    public int size() {
        return this.size;
    }

    /**
     * Задает значение capacity
     *
     * @param capacity емкость хеш-таблицы
     * @return емкость хеш-таблицы
     * @throws IllegalArgumentException если входное значение capacity некорректно
     */
    private int getCapacity(int capacity) {
        if (capacity > 0) {
            return capacity;
        } else if (capacity > MAXIMUM_CAPACITY) {
            return MAXIMUM_CAPACITY;
        } else if (capacity == 0) {
            return DEFAULT_INITIAL_CAPACITY;
        } else {
            throw new IllegalArgumentException("Specified initial capacity is negative: " + capacity);
        }
    }

    /**
     * Задает значение loadFactor
     *
     * @param loadFactor степень загруженности хеш-таблицы
     * @return степень загруженности хеш-таблицы
     * @throws IllegalArgumentException если входное значение loadFactor некорректно
     */
    private float getLoadFactor(float loadFactor) {
        if ((loadFactor > 0) && (loadFactor <= 1)) {
            return loadFactor;
        } else if (loadFactor > 1) {
            return 1;
        } else {
            throw new IllegalArgumentException("Specified loadFactor is incorrect: " + loadFactor);
        }
    }

    /**
     * Генерирует hashCode по ключу key
     *
     * @param key ключ
     * @return hashCode по ключу key
     */
    private int hashCode(K key) {
        if (key == null) {
            return 0;
        } else {
            return key.hashCode();
        }
    }

    /**
     * Возвращает позицию в хеш-таблице по hashCode
     *
     * @param hash hashCode
     * @return позицию в хеш-таблице по hashCode
     */
    private int indexPosition(int hash) {
        return Math.abs(hash % capacity());
    }

    /**
     * Провека путой ли бакет i в хеш-таблице
     *
     * @param i бакет в хеш-таблице
     * @return true если бакет пустой, false иначе
     */
    private boolean isBasketEmpty(int i) {
        return table[i] == null;
    }

    /**
     * Метод для увеличения размера хеш-таблицы с перераспределением элементов.
     * Он перебирает все элементы текущего хранилища, пересчитывает их индексы, с учетом нового размера,
     * и перераспределяет элементы по новой хеш-таблице
     */
    private void resize() {
        int capacityOld = capacity();
        this.capacity *= 2;
        this.threshold = (int) (this.capacity * this.loadFactor);
        Node<K, V>[] tableNew = (Node<K, V>[]) new Node[capacity()];
        for (int i = 0; i < capacityOld; i++) {
            if (!isBasketEmpty(i)) {
                Node<K, V> p = table[i];
                int hash, index;
                do {
                    hash = p.hash;
                    index = indexPosition(hash);
                    if (tableNew[index] == null) {
                        tableNew[index] = p;
                    } else {
                        Node<K, V> e = tableNew[index];
                        while (e.next != null) {
                            e = e.next;
                        }
                        e.next = p;
                    }
                    p = p.next;
                } while (p != null);
            }
        }
        //table = (Node<K, V>[]) new Node[capacity()];
        table = tableNew;
    }

    /**
     * Проверка нужно ли увеличивать емкость хеш-таблицы
     *
     * @return true если кол-во бакетов превышает порог threshold (threshold = capacity * loadFactor), false иначе
     */
    private boolean checkModTable() {
        return size() >= this.threshold;
    }

    /**
     * Класс, который описывает структуру элемента в бакете
     *
     * @param <K> тип ключа элемента
     * @param <V> тип значения элемента
     */
    private static class Node<K, V> {
        private int hash;
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
