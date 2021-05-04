package ru.vtb.training.korostelevaan.lesson4;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DoublyLinkedListTest {

    DoublyLinkedList<String> doublyLinkedList = new DoublyLinkedList<>();

    @BeforeEach
    void setUp() {
        doublyLinkedList.add("1");
        doublyLinkedList.add("2");
        doublyLinkedList.add("3");
    }

    @Test
    void add_MustReturnTrue_WhenAddFirstElement() {
        DoublyLinkedList<String> doublyLinkedList1 = new DoublyLinkedList<>();
        boolean actual = doublyLinkedList1.add("First!");
        Assert.assertTrue(actual);
    }

    @Test
    void add_MustReturnTrue_WhenAddNotFirstElement() {
        boolean actual = doublyLinkedList.add("First!");
        Assert.assertTrue(actual);
    }

    @Test
    void add_MustReturnTrue_WhenBeginningIndex() {
        doublyLinkedList.add(0, "First!");
        Object[] expected = {"First!", "1", "2", "3"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], doublyLinkedList.get(i));
        }
    }

    @Test
    void add_MustReturnTrue_WhenMiddleIndex() {
        doublyLinkedList.add(1, "Second!");
        Object[] expected = {"1", "Second!", "2", "3"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], doublyLinkedList.get(i));
        }
    }

    @Test
    void add_MustReturnTrue_WhenEndIndex() {
        doublyLinkedList.add(doublyLinkedList.size() - 1, "Last!");
        Object[] expected = {"1", "2", "3", "Last!"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], doublyLinkedList.get(i));
        }
    }

    @Test
    void add_MustThrowException_WhenNegativeIndex() {
        int index = -5;
        String msg = "Specified number is went out beyond dimension [0, " + (doublyLinkedList.size() - 1) + "]: " + index;
        try {
            doublyLinkedList.add(index, "False!");
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void set_MustReturnTrue_WhenBeginningIndex() {
        Object actual = doublyLinkedList.set(0, "First!");
        Object[] expected = {"First!", "2", "3"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], doublyLinkedList.get(i));
        }
        Assert.assertEquals("1", actual);
    }

    @Test
    void set_MustReturnTrue_WhenMiddleIndex() {
        Object actual = doublyLinkedList.set(1, "Second!");
        Object[] expected = {"1", "Second!", "3"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], doublyLinkedList.get(i));
        }
        Assert.assertEquals("2", actual);
    }

    @Test
    void set_MustReturnTrue_WhenEndIndex() {
        Object actual = doublyLinkedList.set(doublyLinkedList.size() - 1, "last!");
        Object[] expected = {"1", "2", "last!"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], doublyLinkedList.get(i));
        }
        Assert.assertEquals("3", actual);
    }

    @Test
    void set_MustThrowException_WhenNegativeIndex() {
        int index = -5;
        String msg = "Specified number is went out beyond dimension [0, " + (doublyLinkedList.size() - 1) + "]: " + index;
        try {
            doublyLinkedList.set(index, "False!");
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void remove_MustReturnTrue_WhenFoundObject() {
        boolean actual = doublyLinkedList.remove("1");
        Object[] expected = {"2", "3"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], doublyLinkedList.get(i));
        }
        Assert.assertTrue(actual);
    }

    @Test
    void remove_MustReturnFalse_WhenNotFoundObject() {
        boolean actual = doublyLinkedList.remove("11");
        Assert.assertFalse(actual);
    }

    @Test
    void remove_MustReturnTrue_WhenBeginningIndex() {
        Object actual = doublyLinkedList.remove(0);
        Object[] expected = {"2", "3"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], doublyLinkedList.get(i));
        }
        Assert.assertEquals("1", actual);
    }

    @Test
    void remove_MustReturnTrue_WhenMiddleIndex() {
        Object actual = doublyLinkedList.remove(1);
        Object[] expected = {"1", "3"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], doublyLinkedList.get(i));
        }
        Assert.assertEquals("2", actual);
    }

    @Test
    void remove_MustReturnTrue_WhenEndIndex() {
        Object actual = doublyLinkedList.remove(doublyLinkedList.size() - 1);
        Object[] expected = {"1", "2"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], doublyLinkedList.get(i));
        }
        Assert.assertEquals("3", actual);
    }

    @Test
    void remove_MustThrowException_WhenNegativeIndex() {
        int index = -5;
        String msg = "Specified number is went out beyond dimension [0, " + (doublyLinkedList.size() - 1) + "]: " + index;
        try {
            doublyLinkedList.remove(index);
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void get_MustReturnTrue_WhenBeginningIndex() {
        Object actual = doublyLinkedList.get(0);
        Assert.assertEquals("1", actual);
    }

    @Test
    void get_MustReturnTrue_WhenMiddleIndex() {
        Object actual = doublyLinkedList.get(2);
        Assert.assertEquals("3", actual);
    }

    @Test
    void get_MustReturnTrue_WhenEndIndex() {
        Object actual = doublyLinkedList.get(doublyLinkedList.size() - 1);
        Assert.assertEquals("3", actual);
    }

    @Test
    void get_MustThrowException_WhenNegativeIndex() {
        int index = -5;
        String msg = "Specified number is went out beyond dimension [0, " + (doublyLinkedList.size() - 1) + "]: " + index;
        try {
            doublyLinkedList.get(index);
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void size_MustReturnTrue_WhenTrueValueSizeList() {
        int actual = doublyLinkedList.size();
        Assert.assertEquals(3, actual);
    }

    @Test
    void size_MustReturnFalse_WhenFalseValueSizeList() {
        int actual = doublyLinkedList.size();
        Assert.assertFalse(44 == actual);
    }

    @Test
    void indexOf_MustReturnTrue_WhenFoundObject() {
        int actual = doublyLinkedList.indexOf("2");
        Assert.assertEquals(1, actual);
    }

    @Test
    void indexOf_MustReturnNegative_WhenNotFoundObject() {
        int actual = doublyLinkedList.indexOf("22");
        Assert.assertEquals(-1, actual);
    }
}