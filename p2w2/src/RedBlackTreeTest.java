import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedBlackTreeTest {
    /**
     * test1 tests
     */
    @Test
    public void test1(){
        RedBlackTree<Integer> test = new RedBlackTree<Integer>();
        test.insert(5);
        test.insert(4);
        test.insert(6);
        test.insert(7);

        assertEquals(1, test.findNodeWithData(5).blackHeight);
        assertEquals(1, test.findNodeWithData(4).blackHeight);
        assertEquals(1, test.findNodeWithData(6).blackHeight);
        assertEquals(0, test.findNodeWithData(7).blackHeight);


    }

    /**
     * test2
     */
    @Test
    public void test2(){
        RedBlackTree<Integer> test = new RedBlackTree<Integer>();
        test.insert(5);
        test.insert(4);
        test.insert(3);
        test.insert(2);
        test.insert(1);

        assertEquals(1, test.findNodeWithData(4).blackHeight);
        assertEquals(1, test.findNodeWithData(5).blackHeight);
        assertEquals(1, test.findNodeWithData(2).blackHeight);
        assertEquals(0, test.findNodeWithData(1).blackHeight);
        assertEquals(0, test.findNodeWithData(3).blackHeight);
    }

    /**
     * test3
     */
    @Test
    public void test3(){
        RedBlackTree<Integer> test = new RedBlackTree<Integer>();
        test.insert(1);
        test.insert(2);
        test.insert(4);
        test.insert(6);
        test.insert(5);

        assertEquals(1, test.findNodeWithData(2).blackHeight);
        assertEquals(1, test.findNodeWithData(1).blackHeight);
        assertEquals(0, test.findNodeWithData(6).blackHeight);
        assertEquals(0, test.findNodeWithData(4).blackHeight);
        assertEquals(1, test.findNodeWithData(5).blackHeight);
    }
}
