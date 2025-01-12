import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;
//import myself arraylist class


import java.util.NoSuchElementException;

@SpringBootTest
public class ArrayListTest {

    private ArrayList<Integer> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test
    public void testAddToFront() {
        list.addToFront(1);
        list.addToFront(2);
        list.addToFront(3);
        assertArrayEquals(new Integer[]{3, 2, 1, null, null, null, null, null, null}, list.getBackingArray());
        assertEquals(3, list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToFrontNull() {
        list.addToFront(null);
    }

    @Test
    public void testAddToBack() {
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        assertArrayEquals(new Integer[]{1, 2, 3, null, null, null, null, null, null}, list.getBackingArray());
        assertEquals(3, list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToBackNull() {
        list.addToBack(null);
    }

    @Test
    public void testRemoveFromFront() {
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        assertEquals(Integer.valueOf(1), list.removeFromFront());
        assertArrayEquals(new Integer[]{2, 3, null, null, null, null, null, null, null}, list.getBackingArray());
        assertEquals(2, list.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromFrontEmpty() {
        list.removeFromFront();
    }

    @Test
    public void testRemoveFromBack() {
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        assertEquals(Integer.valueOf(3), list.removeFromBack());
        assertArrayEquals(new Integer[]{1, 2, null, null, null, null, null, null, null}, list.getBackingArray());
        assertEquals(2, list.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromBackEmpty() {
        list.removeFromBack();
    }
}