import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 */
public class ArrayList<T> {

    /*
     * The initial capacity of the ArrayList.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 9;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new ArrayList.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the data to the front of the list.
     *
     * This add may require elements to be shifted.
     *
     * Method should run in O(n) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        try{
            if (data == null){
                throw new IllegalArgumentException("Data is null");
            }
            //if the backing array is full, double the size
            if(size == backingArray.length){
                //double it
                T[] temp = (T[]) new Object[backingArray.length * 2];
                for(int i = 0;i<size;i++){
                    temp[i+1] = backingArray[i];
                }
                temp[0] = data;
                backingArray = temp;
                size++;
            }else{
                for(int i = size;i>0;i--){
                    backingArray[i] = backingArray[i-1];
                }
                backingArray[0] = data;
                size++;
            }
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Data is null");
        }
    }

    /**
     * Adds the data to the back of the list.
     *
     * Method should run in amortized O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        try{
         if (data == null){
                throw new IllegalArgumentException("Data is null");
         }
         //if the backing array is full, double the size
            if (size == backingArray.length){
                //double it
                T[] temp = (T[]) new Object[backingArray.length * 2];
                for(int i = 0;i<size;i++){
                    temp[i] = backingArray[i];
                }
                temp[size] = data;
                backingArray = temp;
                size++;
            }else {
                backingArray[size] = data;
                size++;
            }
        }catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Data is null");
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Do not shrink the backing array.
     *
     * This remove may require elements to be shifted.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        try{
            if (size == 0){
                throw new NoSuchElementException("List is empty");
            }
            T temp = backingArray[0];
            for (int i = 0; i < size-1; i++){
                backingArray[i] = backingArray[i+1];
            }
            backingArray[size-1] = null;
            size--;
            return temp;
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("List is empty");
        }
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Do not shrink the backing array.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        try{
            if (size == 0){
                throw new NoSuchElementException("List is empty");
            }
            T temp = backingArray[size-1];
            backingArray[size-1] = null;
            size--;
            return temp;
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("List is empty");
        }
    }

    /**
     * Returns the backing array of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
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