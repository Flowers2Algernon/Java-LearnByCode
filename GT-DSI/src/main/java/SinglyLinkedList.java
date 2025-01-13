import java.util.NoSuchElementException;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the front of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        try{
            if (data == null){
                throw new IllegalArgumentException("Data cannot be null");
            }
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
            newNode.setNext(head);
            head = newNode;
            if (size == 0) {
                tail = head;
            }
            size++;
        }catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Data cannot be null");
        }
    }

    /**
     * Adds the element to the back of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        try{
            if(data == null){
                throw new IllegalArgumentException("error: data cannot be null");
            }
            SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
            tail.setNext( newNode );
            tail = newNode;
            if (size == 0){
                head = tail;
            }
            size++;
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("error: data cannot be null");
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        try{
            if(size == 0){
                throw new NoSuchElementException("error: list is empty");
            }
            T data = head.getData();
            head = head.getNext();
            size--;
            if(size == 0){
                tail = null;
            }
            return data;
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("error: list is empty");
        }
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        try{
            if (size == 0){
                throw new NoSuchElementException("error: list is empty");
            }
            T data = tail.getData();
            SinglyLinkedListNode<T> current = head;
            while(current.getNext()!= tail){
                current = current.getNext();
            }
            tail = current;
            tail.setNext(null);
            size--;
            if(size == 0){
                head = null;
            }
            return data;
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("error: list is empty");
        }
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}