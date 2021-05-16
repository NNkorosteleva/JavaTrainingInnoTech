package ru.vtb.training.korostelevaan.lesson6;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AssociativeArrayTest {

    AssociativeArray<Integer, String> associativeArray = new AssociativeArray<>();

    @BeforeEach
    void setUp() {
        associativeArray.add(0, "0");
        associativeArray.add(1, "1");
        associativeArray.add(16, "16");
        associativeArray.add(2, "2");
    }

    @Test
    void instantiateAssociativeArray_MustThrowException_ForNegativeCapacity() {
        int capacity = -5;
        String msg = "Specified initial capacity is negative: " + capacity;
        try {
            AssociativeArray<Integer, String> array = new AssociativeArray<>(capacity);
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void instantiateAssociativeArray_MustThrowException_ForNegativeLoadFactor() {
        float loadFactor = -5;
        String msg = "Specified loadFactor is incorrect: " + loadFactor;
        try {
            AssociativeArray<Integer, String> array = new AssociativeArray<>(0, loadFactor);
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void add_MustReturnNull_ForNewNode() {
        AssociativeArray<Integer, String> array = new AssociativeArray<>();
        String actual = array.add(0, "First");
        String expected = null;
        int actualSize = array.size();
        int expectedSize = 1;
        Assert.assertEquals(expected, actual);
        Assert.assertTrue(array.get(0).equals("First"));
        Assert.assertEquals(expectedSize, expectedSize);
    }

    @Test
    void add_MustReturnNull_ForNewNodeWithCollision() {
        AssociativeArray<Integer, String> array = new AssociativeArray<>();
        array.add(0, "First");
        String actual = array.add(16, "Second");
        String expected = null;
        int actualSize = array.size();
        int expectedSize = 1;
        Assert.assertEquals(expected, actual);
        Assert.assertTrue(array.get(16).equals("Second"));
        Assert.assertEquals(expectedSize, expectedSize);
    }

    @Test
    void add_MustReturnValue_ForNewNodeExistingKey() {
        AssociativeArray<Integer, String> array = new AssociativeArray<>();
        array.add(0, "First");
        String actual = array.add(0, "Second");
        String expected = "First";
        int actualSize = array.size();
        int expectedSize = 1;
        Assert.assertEquals(expected, actual);
        Assert.assertTrue(array.get(0).equals("Second"));
        Assert.assertEquals(expectedSize, expectedSize);
    }

    @Test
    void add_MustGrowTable_ForNewNodeMoreCapacity() {
        AssociativeArray<Integer, String> array = new AssociativeArray<>(2, 1);
        array.add(0, "0");
        array.add(1, "1");
        array.add(2, "2");
        int actualSize = array.size();
        int expectedSize = 3;
        int actualCapacity = array.capacity();
        int expectedCapacity = 4;
        Assert.assertEquals(expectedCapacity, actualCapacity);
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    void get_MustReturnValue_ForOneBinInBasket() {
        Assert.assertTrue(associativeArray.get(1).equals("1"));
    }

    @Test
    void get_MustReturnValue_ForMultipleBinInBasket() {
        Assert.assertTrue(associativeArray.get(16).equals("16"));
    }

    @Test
    void get_MustReturnNull_ForUnknownNode() {
        Assert.assertTrue(associativeArray.get(123) == null);
    }

    @Test
    void remove_MustReturnVal_ForOneBinInBasket() {
        Assert.assertTrue(associativeArray.remove(1).equals("1"));
        int actualSize = associativeArray.size();
        int expectedSize = 2;
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    void remove_MustReturnVal_ForBeginningBinInBasket() {
        Assert.assertTrue(associativeArray.remove(0).equals("0"));
        int actualSize = associativeArray.size();
        int expectedSize = 3;
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    void remove_MustReturnVal_ForMiddleBinInBasket() {
        associativeArray.add(32, "32");
        Assert.assertTrue(associativeArray.remove(16).equals("16"));
        int actualSize = associativeArray.size();
        int expectedSize = 3;
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    void remove_MustReturnVal_ForEndBinInBasket() {
        associativeArray.add(32, "32");
        Assert.assertTrue(associativeArray.remove(32).equals("32"));
        int actualSize = associativeArray.size();
        int expectedSize = 3;
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    void remove_MustReturnNull_ForUnknownNode() {
        Assert.assertTrue(associativeArray.remove(12) == null);
        int actualSize = associativeArray.size();
        int expectedSize = 3;
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    void capacity_MustReturnTrue_ForTrueValueCapacity() {
        int actualCapacity = associativeArray.capacity();
        int expectedCapacity = 16;
        Assert.assertEquals(expectedCapacity, actualCapacity);
    }

    void capacity_MustReturnFalse_ForFalseValueCapacity() {
        int actualCapacity = associativeArray.capacity();
        boolean equals = 44 == actualCapacity;
        Assert.assertFalse(equals);
    }

    @Test
    void size_MustReturnTrue_ForTrueValueSize() {
        int actualSize = associativeArray.size();
        int expectedSize = 3;
        Assert.assertEquals(expectedSize, actualSize);
    }

    void size_MustReturnFalse_ForFalseValueSizeArray() {
        int actualSize = associativeArray.size();
        boolean equals = 44 == actualSize;
        Assert.assertFalse(equals);
    }
}