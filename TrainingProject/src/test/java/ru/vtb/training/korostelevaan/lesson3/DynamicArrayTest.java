package ru.vtb.training.korostelevaan.lesson3;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DynamicArrayTest {

    DynamicArray dynamicArray = new DynamicArray(4);

    @BeforeEach
    public void setUp() {

        dynamicArray.add("1");
        dynamicArray.add("2");
        dynamicArray.add("3");
    }

    @Test
    void instantiateDynamicArray_MustThrowException_ForNegativeInitialSize() {
        int initialSize = -5;
        String msg = "Specified initial capacity is negative: " + initialSize;
        try {
            DynamicArray dynamicArray = new DynamicArray(initialSize);
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void add_MustReturnTrue_ForAddElement() {
        boolean actual = dynamicArray.add("First!");
        Assert.assertTrue(actual);
    }

    @Test
    void add_MustReturnTrue_ForBeginningIndex() {
        dynamicArray.add(0, "First!");
        Object[] expected = {"First!", "1", "2", "3"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], dynamicArray.get(i));
        }
    }

    @Test
    void add_MustReturnTrue_ForMiddleIndex() {
        dynamicArray.add(1, "Second!");
        Object[] expected = {"1", "Second!", "2", "3"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], dynamicArray.get(i));
        }
    }

    @Test
    void add_MustReturnTrue_ForEndIndex() {
        dynamicArray.add(3, "Last!");
        Object[] expected = {"1", "2", "3", "Last!"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], dynamicArray.get(i));
        }
    }

    @Test
    void add_MustThrowException_ForNegativeIndex() {
        int index = -5;
        String msg = "Specified number is went out beyond dimension: " + index;
        try {
            dynamicArray.add(index, "False!");
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void set_MustReturnTrue_ForBeginningIndex() {
        Object actual = dynamicArray.set(0, "First!");
        Object[] expected = {"First!", "2", "3", null};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], dynamicArray.get(i));
        }
        Assert.assertEquals("1", actual);
    }

    @Test
    void set_MustReturnTrue_ForMiddleIndex() {
        Object actual = dynamicArray.set(1, "Second!");
        Object[] expected = {"1", "Second!", "3", null};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], dynamicArray.get(i));
        }
        Assert.assertEquals("2", actual);
    }

    @Test
    void set_MustReturnTrue_ForEndIndex() {
        Object actual = dynamicArray.set(3, "last!");
        Object[] expected = {"1", "2", "3", "last!"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], dynamicArray.get(i));
        }
        Assert.assertEquals(null, actual);
    }

    @Test
    void set_MustThrowException_ForNegativeIndex() {
        int index = -5;
        String msg = "Specified number is went out beyond dimension: " + index;
        try {
            dynamicArray.set(index, "False!");
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void get_MustReturnTrue_ForBeginningIndex() {
        Object actual = dynamicArray.get(0);
        Assert.assertEquals("1", actual);
    }

    @Test
    void get_MustReturnTrue_ForMiddleIndex() {
        Object actual = dynamicArray.get(2);
        Assert.assertEquals("3", actual);
    }

    @Test
    void get_MustReturnTrue_ForEndIndex() {
        Object actual = dynamicArray.get(3);
        Assert.assertEquals(null, actual);
    }

    @Test
    void get_MustThrowException_ForNegativeIndex() {
        int index = -5;
        String msg = "Specified number is went out beyond dimension: " + index;
        try {
            dynamicArray.get(index);
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void remove_MustReturnTrue_ForBeginningIndex() {
        Object actual = dynamicArray.remove(0);
        Object[] expected = {"2", "3", null, null};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], dynamicArray.get(i));
        }
        Assert.assertEquals("1", actual);
    }

    @Test
    void remove_MustReturnTrue_ForMiddleIndex() {
        Object actual = dynamicArray.remove(1);
        Object[] expected = {"1", "3", null, null};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], dynamicArray.get(i));
        }
        Assert.assertEquals("2", actual);
    }

    @Test
    void remove_MustReturnTrue_ForEndIndex() {
        Object actual = dynamicArray.remove(3);
        Object[] expected = {"1", "2", "3", null};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], dynamicArray.get(i));
        }
        Assert.assertEquals(null, actual);
    }

    @Test
    void remove_MustThrowException_ForNegativeIndex() {
        int index = -5;
        String msg = "Specified number is went out beyond dimension: " + index;
        try {
            dynamicArray.remove(index);
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void remove_MustReturnTrue_ForFoundObject() {
        Object removeObject = "2";
        boolean actual = dynamicArray.remove(removeObject);
        Object[] expected = {"1", "3", null, null};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], dynamicArray.get(i));
        }
        Assert.assertTrue(actual);
    }

    @Test
    void remove_MustReturnFalse_ForNotFoundObject() {
        Object removeObject = "NotFound";
        boolean actual = dynamicArray.remove(removeObject);
        Assert.assertFalse(actual);
    }

    @Test
    void size_MustReturnTrue_ForTrueValueSizeArray() {
        int actual = dynamicArray.size();
        Assert.assertEquals(4, actual);
    }

    @Test
    void size_MustReturnFalse_ForFalseValueSizeArray() {
        int actual = dynamicArray.size();
        boolean equals = 44 == actual;
        Assert.assertFalse(equals);
    }

    @Test
    void indexOf_MustReturnTrue_ForFoundObject() {
        int actual = dynamicArray.indexOf("2");
        Assert.assertEquals(1, actual);
    }

    @Test
    void indexOf_MustReturnTrue_ForFoundObjectNull() {
        int actual = dynamicArray.indexOf(null);
        Assert.assertEquals(3, actual);
    }

    @Test
    void indexOf_MustReturnTrue_ForNotFoundObject() {
        int actual = dynamicArray.indexOf("22");
        Assert.assertEquals(-1, actual);
    }

    @Test
    void contains_MustReturnTrue_ForFoundObject() {
        boolean actual = dynamicArray.contains("3");
        Assert.assertTrue(actual);
    }

    @Test
    void contains_MustReturnFalse_ForNotFoundObject() {
        boolean actual = dynamicArray.contains("33");
        Assert.assertFalse(actual);
    }

    @Test
    void toArray() {
        Object[] actual = dynamicArray.toArray();
        for (int i = 0; i < dynamicArray.size(); i++) {
            Assert.assertEquals(actual[i], dynamicArray.get(i));
        }
    }
}