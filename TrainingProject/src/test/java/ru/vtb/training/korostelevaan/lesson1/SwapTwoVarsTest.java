package ru.vtb.training.korostelevaan.lesson1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vtb.training.korostelevaan.lesson1.util.Assert;
import ru.vtb.training.korostelevaan.lesson1.util.AssertionFailedError;

class SwapTwoVarsTest {

    SwapTwoVars swap = new SwapTwoVars();

    @BeforeEach
    void setUp() {
        swap.a = 13;
        swap.b = 8;
    }

    @Test
    void swapArithmeticSum() throws AssertionFailedError {
        int tempA = swap.a;
        int tempB = swap.b;
        swap.swapArithmeticSum();

        Assert.assertEqualsSwap("Use swapArithmeticSum -> ", tempA, tempB, swap.a, swap.b);
    }

    @Test
    void swapArithmeticMultiplication() throws AssertionFailedError {
        int tempA = swap.a;
        int tempB = swap.b;
        swap.swapArithmeticMultiplication();

        Assert.assertEqualsSwap("Use swapArithmeticMultiplication -> ", tempA, tempB, swap.a, swap.b);
    }

    @Test
    void swapBitwiseXor() throws AssertionFailedError {
        int tempA = swap.a;
        int tempB = swap.b;
        swap.swapBitwiseXor();

        Assert.assertEqualsSwap("Use swapBitwiseXor -> ", tempA, tempB, swap.a, swap.b);
    }

    @Test
    void swapBitwiseMulti() throws AssertionFailedError {
        int tempA = swap.a;
        int tempB = swap.b;
        swap.swapBitwiseMulti();

        Assert.assertEqualsSwap("Use swapBitwiseMulti -> ", tempA, tempB, swap.a, swap.b);
    }
}