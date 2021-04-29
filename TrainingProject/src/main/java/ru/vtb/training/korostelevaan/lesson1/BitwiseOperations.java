package ru.vtb.training.korostelevaan.lesson1;

/**
 * Класс BitwiseOperations содержит методы для работы с бинарными операциям из задания 6
 */
public class BitwiseOperations {

    String buts = "";

    /**
     * a) given n<31, calc 2^n
     *
     * @param n натуральное число типа int, меньше 31
     * @return 2^n
     */
    public int powerTwo(int n) {
        return 1 << n;
    }

    /**
     * b) given n,m<31, calc 2^n+2^m
     *
     * @param n натуральное число типа int, меньше 31
     * @param m натуральное число типа int, меньше 31
     * @return 2^n+2^m
     * comment: при n=m данный алгоритм не работает
     */
    public int powerTwoSum(int n, int m) {
        return (1 << n) | (1 << m);
    }

    /**
     * c) reset n lower bits (create mask with n lower bits reset)
     *
     * @param a натуральное число типа int
     * @param n кол-во младших битов
     * @return reset n lower bits
     */
    public int resetLowerBits(int a, int n) {
        int mask = -1 << n;
        a = a & mask;
        return a;
    }

    /**
     * d) set a's n-th bit with 1
     *
     * @param a натуральное число типа int
     * @param n номер бита, на месте которого нужно установить 1
     * @return число типа int у котрого nый бит 1
     */
    public int setBitOne(int a, int n) {
        return a | (1 << n);
    }

    /**
     * e) invert n-th bit (use 2 bit ops)
     *
     * @param a натуральное число типа int
     * @param n номер бита, значение которого нужно инвертировать
     * @return число типа int у котрого nый бит инвертированный
     */
    public int invertBit(int a, int n) {
        return a ^ (1 << n);
    }

    /**
     * f) set a's n-th bit with 0
     *
     * @param a натуральное число типа int
     * @param n номер бита, на месте которого нужно установить 0
     * @return число типа int у которого n-ый бит 0
     */
    public int setBitZero(int a, int n) {
        return a & ~(1 << n);
    }

    /**
     * g) return n lower bits
     *
     * @param a натуральное число типа int
     * @param n кол-во младших битов
     * @return n младших битов
     */
    public int lowerBits(int a, int n) {
        return a & ~(-1 << n);
    }

    /**
     * h) return n-th bit
     *
     * @param a натуральное число типа int
     * @param n номер бита
     * @return значение n-ого бита
     */
    public int valueBit(int a, int n) {
        return a & (1 << n);
    }

    /**
     * i) given byte a. output bin representation using bit ops (don't use jdk api).
     *
     * @param a натуральное число типа int
     * представление числа a в двоичной системе
     */
    public String representationInBits(int a) {
        if (a > 1) {
            representationInBits(a >> 1);
        }
        buts += a & 1;
        return buts;
    }

}
