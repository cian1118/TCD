package Assignment2;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);

        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 8 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );

    }

    public DoublyLinkedList<Integer> getTestDLL()
    {
        //Makes a list that can be used in the tests below
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,4);
        testDLL.insertBefore(1,5);
        testDLL.insertBefore(2,6);
        testDLL.insertBefore(-1,7);
        testDLL.insertBefore(7,8);
        testDLL.insertBefore(700,9);
        //returns list: "7,4,5,6,1,2,3,8,9"
        return testDLL;
    }

    @Test
    public void testDeleteAt()
    {
        DoublyLinkedList<Integer> testDLL = getTestDLL();

        testDLL.deleteAt(0);
        assertEquals("Checking deleteAt - deleting head of the DLL", "4,5,6,1,2,3,8,9", testDLL.toString());
        testDLL.deleteAt(testDLL.getSize()-1);
        assertEquals("Checking deleteAt - deleting tail of the DLL", "4,5,6,1,2,3,8", testDLL.toString());
        testDLL.deleteAt(3);
        assertEquals("Checking deleteAt - deleting element in the middle of the DLL", "4,5,6,2,3,8", testDLL.toString());

        //test empty list
        testDLL = new DoublyLinkedList<Integer>();
        assertFalse("checking that deleteAt returns false when deleting head from empty list",testDLL.deleteAt(0));
        testDLL = new DoublyLinkedList<Integer>();
        assertFalse("checking that deleteAt returns false when deleting element in pos 3 from empty list",testDLL.deleteAt(3));
        testDLL = new DoublyLinkedList<Integer>();
        assertFalse("checking that deleteAt returns false when deleting element in 'last pos' from empty list",testDLL.deleteAt(testDLL.getSize()-1));
    }

    @Test
    public void testReverse()
    {
        DoublyLinkedList<Integer> testDLL = getTestDLL();

        testDLL.reverse();
        assertEquals("Checking that reverse correctly reverses a DLL", "9,8,3,2,1,6,5,4,7", testDLL.toString());

        testDLL = new DoublyLinkedList<Integer>();
        testDLL.reverse();
        assertEquals("Testing reverse on an empty list", "", testDLL.toString());
        testDLL.insertBefore(0,1);
        assertEquals("Testing reverse on an list with one node", "1", testDLL.toString());
    }

    @Test
    public void testMakeUnique()
    {
        DoublyLinkedList<Integer> testDLL = getTestDLL();
        testDLL.insertBefore(9, 7);

        testDLL.makeUnique();
        assertEquals("Testing makeUnique on list \"7,4,5,6,1,2,3,8,9,7\"", "7,4,5,6,1,2,3,8,9", testDLL.toString());

        testDLL = new DoublyLinkedList<Integer>();
        testDLL.makeUnique();
        assertEquals("Testing makeUnique on an empty list", "", testDLL.toString());
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0, 1);
        testDLL.makeUnique();
        assertEquals("Testing makeUnique on list with one element", "1", testDLL.toString());

        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,2);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2, 2);
        testDLL.insertBefore(3, 1);
        testDLL.insertBefore(4,1);
        testDLL.insertBefore(5, 1);
        testDLL.insertBefore(6, 1);

        testDLL.makeUnique();
        assertEquals("Testing makeUnique on list 2,2,2,1,1,1,1", "2,1", testDLL.toString());

    }

    @Test
    public void testPush()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.push(1);
        assertEquals("pushing 1 onto stack", "1", testDLL.toString());
        testDLL.push(2);
        assertEquals("pushing 2 onto stack", "1,2", testDLL.toString());
        testDLL.push(3);
        assertEquals("pushing 3 onto stack", "1,2,3", testDLL.toString());
        testDLL.push(4);
        assertEquals("pushing 4 onto stack", "1,2,3,4", testDLL.toString());

    }

    @Test
    public void testPop()
    {
        DoublyLinkedList<Integer> testDLL = getTestDLL();
        //"7,4,5,6,1,2,3,8,9"
        assertTrue("expected data returned: int 9", testDLL.pop() == 9);
        assertEquals("popping stack","7,4,5,6,1,2,3,8", testDLL.toString());

        assertTrue("expected data returned: int 8", testDLL.pop() == 8);
        assertEquals("popping stack","7,4,5,6,1,2,3", testDLL.toString());

        assertTrue("expected data returned: int 3", testDLL.pop() == 3);
        assertEquals("popping stack","7,4,5,6,1,2", testDLL.toString());

        assertTrue("expected data returned: int 2", testDLL.pop() == 2);
        assertEquals("popping stack","7,4,5,6,1", testDLL.toString());

        assertTrue("expected data returned: int 1", testDLL.pop() == 1);
        assertEquals("popping stack","7,4,5,6", testDLL.toString());

        assertTrue("expected data returned: int 6", testDLL.pop() == 6);
        assertEquals("popping stack","7,4,5", testDLL.toString());

        assertTrue("expected data returned: int 5", testDLL.pop() == 5);
        assertEquals("popping stack","7,4", testDLL.toString());

        assertTrue("expected data returned: int 4", testDLL.pop() == 4);
        assertEquals("popping stack","7", testDLL.toString());

        assertTrue("expected data returned: int 7", testDLL.pop() == 7);
        assertEquals("popping stack","", testDLL.toString());

        assertTrue("expected data returned: null", testDLL.pop() == null);
        assertEquals("popping empty stack", "", testDLL.toString());


    }

    @Test
    public void testEnqueue()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.enqueue(1);
        assertEquals("Enqueuing 1 to an empty list", "1", testDLL.toString());
        testDLL.enqueue(2);
        assertEquals("Enqueuing 2 to the list", "1,2", testDLL.toString());
        testDLL.enqueue(3);
        assertEquals("Enqueuing 3 to the list", "1,2,3", testDLL.toString());
        testDLL.enqueue(4);
        assertEquals("Enqueuing 4 to the list", "1,2,3,4", testDLL.toString());

    }

    @Test
    public void testDequeue()
    {
        DoublyLinkedList<Integer> testDLL = getTestDLL();
        //7,4,5,6,1,2,3,8,9
        assertTrue("Dequeuing head node: int 7", testDLL.dequeue() == 7);
        assertEquals("Checking first node in is removed (the head)", "4,5,6,1,2,3,8,9", testDLL.toString());

        assertTrue("Dequeuing head node: int 4", testDLL.dequeue() == 4);
        assertEquals("Checking first node in is removed (the head)", "5,6,1,2,3,8,9", testDLL.toString());

        assertTrue("Dequeuing head node: int 5", testDLL.dequeue() == 5);
        assertEquals("Checking first node in is removed (the head)", "6,1,2,3,8,9", testDLL.toString());

        assertTrue("Dequeuing head node: int 6", testDLL.dequeue() == 6);
        assertEquals("Checking first node in is removed (the head)", "1,2,3,8,9", testDLL.toString());

        assertTrue("Dequeuing head node: int 1", testDLL.dequeue() == 1);
        assertEquals("Checking first node in is removed (the head)", "2,3,8,9", testDLL.toString());

        assertTrue("Dequeuing head node: int 2", testDLL.dequeue() == 2);
        assertEquals("Checking first node in is removed (the head)", "3,8,9", testDLL.toString());

        assertTrue("Dequeuing head node: int 3", testDLL.dequeue() == 3);
        assertEquals("Checking first node in is removed (the head)", "8,9", testDLL.toString());

        assertTrue("Dequeuing head node: int 8", testDLL.dequeue() == 8);
        assertEquals("Checking first node in is removed (the head)", "9", testDLL.toString());

        assertTrue("Dequeuing head node: int 9", testDLL.dequeue() == 9);
        assertEquals("Checking first node in is removed (the head)", "", testDLL.toString());

        assertTrue("Dequeuing head node from empty list expecting to return null", testDLL.dequeue() == null);
        assertEquals("Checking first node in is removed (the head)", "", testDLL.toString());

    }
    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.

}
