package Assignment2;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 * This class contains the methods of Doubly Linked List.
 *
 * @author Cian Higgins
 * @version 01/10/18 17:35:49
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data:
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 *
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>> {
    /**
     * private class DLLNode: implements a *generic* Doubly Linked List node.
     */
    private class DLLNode {
        public final T data; // this field should never be updated. It gets its
        // value once from the constructor DLLNode.
        public DLLNode next;
        public DLLNode prev;

        /**
         * Constructor
         * @param theData : data of type T, to be stored in the node
         * @param prevNode : the previous Node in the Doubly Linked List
         * @param nextNode : the next Node in the Doubly Linked List
         * @return DLLNode
         */


        public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) {
            data = theData;
            prev = prevNode;
            next = nextNode;
        }
    }

    // Fields head and tail point to the first and last nodes of the list.
    private DLLNode head, tail;
    private int size;

    public int getSize() {
        return size;
    }


    /**
     *  Finds and returns the DLLNode at the position passed into the function
     *
     *  @param pos : the position
     *  @return DLLNode : the node at the given position in the list
     *  Worst-case asymptotic running time cost: O(N)
     *
     *  Justification: In the worst case the function will have to get the node at the end of the list, ie the Nth node.
     *
     */
    public DLLNode getNode(int pos) {
        //returns DLLNode if pos is within the bounds of the list, null otherwise
        if (pos >= 0 && pos < this.getSize()) {
            DLLNode current = head;
            for (int i = 0; i < pos; i++) {
                current = current.next;
            }
            return current;
        } else {
            return null;
        }
    }

    /**
     * Constructor of an empty DLL
     * @return DoublyLinkedList
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;

    }

    /**
     * Tests if the doubly linked list is empty
     * @return true if list is empty, and false otherwise
     *
     * Worst-case asymptotic running time cost: Theta(1)
     *
     * Justification: Constant running time, only one statement is executed to check if the list is empty.
     *
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserts an element in the doubly linked list
     * @param pos : The integer location at which the new data should be
     *      inserted in the list. We assume that the first position in the list
     *      is 0 (zero). If pos is less than 0 then add to the head of the list.
     *      If pos is greater or equal to the size of the list then add the
     *      element at the end of the list.
     * @param data : The new data of class T that needs to be added to the list
     * @return none
     *
     * Worst-case asymptotic running time cost: O(N)
     *
     * Justification:
     *      Worst case scenario is when the element is inserted inside the list, in this case the getNode()
     *      function is called which has a worst case running time of O(N).
     *
     */
    public void insertBefore(int pos, T data) {
        if (this.isEmpty()) { //list empty
            DLLNode newNode =  new DLLNode(data, null, null);
            tail = newNode;
            head = newNode;
            this.size++;

        } else if (pos <= 0) { //position <= 0 (head)
            DLLNode newNode = new DLLNode(data, null, head);
            head.prev = newNode;
            head = newNode;
            this.size++;

        } else if (pos < this.getSize()) { //position < size of list; inside the list
            DLLNode current = this.getNode(pos);
            DLLNode newNode = new DLLNode(data, current.prev, current);
            current.prev.next = newNode;
            current.prev = newNode;
            this.size++;

        } else if (pos >= this.getSize()) { //position >= size (make new tail)
            DLLNode newNode = new DLLNode(data, tail, null);
            tail.next = newNode;
            tail = newNode;
            this.size++;
        }
    }

    /**
     * Returns the data stored at a particular position
     * @param pos : the position
     * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
     *
     * Worst-case asymptotic running time cost: O(n)
     *
     * Justification: in the worst case the getNode function is called and has to get the element at the end of the
     *              DLL; the getNode function has a worst case asymptotic running time cost of O(n)
     *
     */
    public T get(int pos) {
        DLLNode node = this.getNode(pos);
        if (node != null) {
            return node.data;
        } else {
            return null;
        }
    }

    /**
     * Deletes the element of the list at position pos.
     * First element in the list has position 0. If pos points outside the
     * elements of the list then no modification happens to the list.
     * @param pos : the position to delete in the list.
     * @return true : on successful deletion, false : list has not been modified.
     *
     * Worst-case asymptotic running time cost: O(n)
     *
     * Justification:
     *  In the worst case the getNode function is called to get the node which needs to be deleted. getNode has a worst
     *  case asymptotic running time cost of O(n) so this is carried into the deleteAt function.
     */
    public boolean deleteAt(int pos) {
        DLLNode delete = this.getNode(pos);
        if (delete != null) { //delete head
            if (pos == 0) {
                head = delete.next;
                delete.next = null;
                this.size--;
                return true;

            } else if (pos > 0 && pos < this.getSize()-1) { //delete node inside the list, pos > 0 and < size of the list
                delete.next.prev = delete.prev;
                delete.prev.next = delete.next;
                delete.next = null;
                delete.prev = null;
                this.size--;
                return true;

            } else if (pos == this.getSize()-1) { //delete the tail
                tail = delete.prev;
                tail.next = null;
                this.size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Reverses the list.
     * If the list contains "A", "B", "C", "D" before the method is called
     * Then it should contain "D", "C", "B", "A" after it returns.
     *
     * Worst-case asymptotic running time cost: O(n)
     *
     * Justification:
     *  Worst case running time is n because the function must iterate through each element in the list in order to
     *      reverse all the elements, therefore the running time is determined by the size of the list, n.
     */
    public void reverse() {
        DLLNode temp = head;
        head = tail;
        tail = temp;

        DLLNode current = head;
        while (current != null) {
            temp = current.next;
            current.next = current.prev;
            current.prev = temp;
            current = current.next;
        }
    }

    /**
     * Removes all duplicate elements from the list.
     * The method should remove the _least_number_ of elements to make all elements uniqueue.
     * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
     * Then it should contain "A", "B", "C", "D" after it returns.
     * The relative order of elements in the resulting list should be the same as the starting list.
     *
     * Worst-case asymptotic running time cost: Theta(n^2)
     *
     * Justification:
     *  The function uses two linear for loops which both step through the entire length of the list making the
     *  asymptotic order of growth Theta(n * n) or Theta(n^2)
     */
    public void makeUnique() {
        DLLNode current = head;
        for (int pos = 0; pos < this.getSize() - 1; pos++) {
            DLLNode nextNode = current.next;
            for (int i = pos+1; i < this.getSize(); i++) {
                if (current.data.equals(nextNode.data)) {
                    DLLNode prev = nextNode.prev;
                    DLLNode next = nextNode.next;
                    if (next != null) {
                        prev.next = next;
                        next.prev = prev;
                    } else {
                        prev.next = null;
                    }
                    size--;
                    i--;
                }
                nextNode = nextNode.next;
            }
            current = current.next;
        }

    }

    /*----------------------- STACK API
     * If only the push and pop methods are called the data structure should behave like a stack.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to push on the stack
     *
     * Worst-case asymptotic running time cost: O(n)
     *
     * Justification:
     *  The push function uses the insertBefore method which has a worst case running time cost of O(n), this is carried
     *  on to the push function.
     */
    public void push(T item) {
        //add node to top of stack (tail)
        insertBefore(this.size, item);
    }

    /**
     * This method returns and removes the element that was most recently added by the push method.
     * @return the last item inserted with a push; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: O(n^2)
     *
     * Justification:
     *  pop uses the functions get and deleteAt, both of which have a worst case asymptotic running time cost of O(n),
     *  therefore pop will have a running time cost of O(n * n) = O(n^2)
     */
    public T pop() {
        //remove node from top of stack (tail)
        T data = get(this.size-1);
        deleteAt(this.size-1);
        return data;
    }

    /*----------------------- QUEUE API
     * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
     */

    /**
     * This method adds an element to the data structure.
     * How exactly this will be represented in the Doubly Linked List is up to the programmer.
     * @param item : the item to be enqueued to the stack
     *
     * Worst-case asymptotic running time cost: O(n)
     *
     * Justification:
     *  The enqueue function uses the insertBefore method which has a worst case running time cost of O(n), this is carried
     *  on to the enqueue function.
     */
    public void enqueue(T item) {
        //add node to end of queue (the tail)
        insertBefore(this.size+1, item);
    }

    /**
     * This method returns and removes the element that was least recently added by the enqueue method.
     * @return the earliest item inserted with an equeue; or null when the list is empty.
     *
     * Worst-case asymptotic running time cost: O(n^2)
     *
     * Justification:
     *  dequeue uses the functions get and deleteAt, both of which have a worst case asymptotic running time cost of O(n),
     *  therefore dequeue will have a running time cost of O(n * n) = O(n^2)
     */
    public T dequeue() {
        //remove node from top of the queue (the head)
        T data = get(0);
        deleteAt(0);
        return data;
    }


    /**
     * @return a string with the elements of the list as a comma-separated
     * list, from beginning to end
     *
     * Worst-case asymptotic running time cost:   Theta(n)
     *
     * Justification:
     *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
     *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
     *  Thus, every one iteration of the for-loop will have cost Theta(1).
     *  Suppose the doubly-linked list has 'n' elements.
     *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        boolean isFirst = true;

        // iterate over the list, starting from the head
        for (DLLNode iter = head; iter != null; iter = iter.next) {
            if (!isFirst) {
                s.append(",");
            } else {
                isFirst = false;
            }
            s.append(iter.data.toString());
        }

        return s.toString();
    }

}


