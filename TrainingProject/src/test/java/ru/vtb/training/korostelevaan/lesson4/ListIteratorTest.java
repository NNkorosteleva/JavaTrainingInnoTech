package ru.vtb.training.korostelevaan.lesson4;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ListIterator;

class ListIteratorTest {

    DoublyLinkedList<String> doublyLinkedList = new DoublyLinkedList<>();
    ListIterator<String> listIterator;

    @BeforeEach
    void setUp() {
        doublyLinkedList.add("1");
        doublyLinkedList.add("2");
        doublyLinkedList.add("3");
        doublyLinkedList.add("4");
        doublyLinkedList.add("5");
        listIterator = doublyLinkedList.listIterator();
    }

    @Test
    void hasNext_MustReturnTrue_WhenNextElementNotNull() {
        Assert.assertTrue(listIterator.hasNext());
    }

    @Test
    void hasNext_MustReturnFalse_WhenNextElementNull() {
        for (int i = 0; i < doublyLinkedList.size(); i++) {
            listIterator.next();
        }
        Assert.assertFalse(listIterator.hasNext());
    }

    @Test
    void next_MustReturnTrue_WhenNextElementNotNull() {
        Assert.assertEquals("1", listIterator.next());
    }

    @Test
    void next_MustThrowException_WhenListModification() {
        doublyLinkedList.add("6");
        String msg = "List has been changed";
        try {
            listIterator.next();
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void next_MustThrowException_WhenIndexWentOut() {
        for (int i = 0; i < doublyLinkedList.size(); i++) {
            listIterator.next();
        }
        String msg = "Specified number is went out beyond dimension [0, " + (doublyLinkedList.size() - 1) + "]: " + 5;
        try {
            listIterator.next();
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void hasPrevious_MustReturnTrue_WhenPreviousElementNotNull() {
        listIterator.next();
        Assert.assertTrue(listIterator.hasPrevious());
    }

    @Test
    void hasPrevious_MustReturnFalse_WhenPreviousElementNull() {
        Assert.assertFalse(listIterator.hasPrevious());
    }

    @Test
    void previous_MustReturnTrue_WhenPreviousElementNotNull() {
        listIterator.next();
        Assert.assertEquals("1", listIterator.previous());
    }

    @Test
    void previous_MustThrowException_WhenListModification() {
        doublyLinkedList.add("6");
        String msg = "List has been changed";
        try {
            listIterator.previous();
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void previous_MustThrowException_WhenIndexWentOut() {
        String msg = "Specified number is went out beyond dimension (0, " + (doublyLinkedList.size() - 1) + "]: " + 0;
        try {
            listIterator.previous();
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void nextIndex_MustReturnTrue_WhenNextElementNotNull() {
        Assert.assertEquals(0, listIterator.nextIndex());
    }

    @Test
    void nextIndex_MustThrowException_WhenIndexWentOut() {
        for (int i = 0; i < doublyLinkedList.size(); i++) {
            listIterator.next();
        }
        String msg = "Specified number is went out beyond dimension [0, " + (doublyLinkedList.size() - 1) + "): " + 5;
        try {
            listIterator.nextIndex();
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void previousIndex_MustReturnTrue_WhenPreviousElementNotNull() {
        listIterator.next();
        Assert.assertEquals(0, listIterator.previousIndex());
    }

    @Test
    void previousIndex_MustThrowException_WhenIndexWentOut() {
        String msg = "Specified number is went out beyond dimension (0, " + (doublyLinkedList.size() - 1) + "]: " + 0;
        try {
            listIterator.previousIndex();
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void remove_MustReturnTrue_WhenListNotEmpty() {
        listIterator.remove();
        Object[] expected = {"1", "2", "3", "4"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], listIterator.next());
        }
    }

    @Test
    void remove_MustThrowException_WhenListEmpty() {
        DoublyLinkedList<String> doublyLinkedList1 = new DoublyLinkedList<>();
        listIterator = doublyLinkedList1.listIterator();
        String msg = "List is empty";
        try {
            listIterator.remove();
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void remove_MustThrowException_WhenListModification() {
        doublyLinkedList.add("6");
        String msg = "List has been changed";
        try {
            listIterator.remove();
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void set_MustReturnTrue_WhenListNotEmpty() {
        listIterator.set("6");
        Object[] expected = {"1", "2", "3", "4", "6"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], listIterator.next());
        }
    }

    @Test
    void set_MustThrowException_WhenListEmpty() {
        DoublyLinkedList<String> doublyLinkedList1 = new DoublyLinkedList<>();
        listIterator = doublyLinkedList1.listIterator();
        String msg = "List is empty";
        try {
            listIterator.set("1");
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void set_MustThrowException_WhenListModification() {
        doublyLinkedList.add("6");
        String msg = "List has been changed";
        try {
            listIterator.set("1");
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }

    @Test
    void add_MustReturnTrue_WhenAddElement() {
        listIterator.add("6");
        Object[] expected = {"1", "2", "3", "4", "5", "6"};
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], listIterator.next());
        }
    }

    @Test
    void add_MustThrowException_WhenListModification() {
        doublyLinkedList.add("6");
        String msg = "List has been changed";
        try {
            listIterator.add("1");
            Assert.fail(msg);
        } catch (Exception e) {
            Assert.assertEquals(msg, e.getMessage());
        }
    }


}