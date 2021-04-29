package ru.vtb.training.korostelevaan.lesson1;

/**
 * Класс SwapTwoVars содержит методы, которые взаимно меняют значения двух переменных 4 способами:
 * 2 с помощью бинарных операций и 2 с арифметическими операциями для задания 7
 */
public class SwapTwoVars {

    int a;
    int b;

    /**
     * Меняет местами значения двух переменных с помощью операций сложения и вычитания
     */
    public void swapArithmeticSum() {
        a = a + b;
        b = a - b;
        a = a - b;
    }

    /**
     * Меняет местами значения двух переменных с помощью операций умножение и деление
     */
    public void swapArithmeticMultiplication() {
        a = a * b;
        b = a / b;
        a = a / b;
    }

    /**
     * Меняет местами значения двух переменных с помощью операции XOR
     */
    public void swapBitwiseXor() {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }

    /**
     * Меняет местами значения двух переменных с помощью операций |, &, ~, +
     */
    public void swapBitwiseMulti() {
        a = (a | b) + (a & b);
        b = a + ~b + 1;
        a = a + ~b + 1;
    }
}
