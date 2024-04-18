package assignment;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Your implementation of an ArrayList.
 */
public class ArrayList<T> {

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        nums.addToFront(3);
        nums.addToFront(8);
        nums.addToFront(5);
        nums.addToBack(1);
        nums.addToBack(14);
        nums.addToFront(4);
        nums.addToFront(7);
        nums.addToBack(10);
        nums.addToBack(11);
        System.out.println(Arrays.toString(nums.getBackingArray()));
        System.out.printf("Arrays after add: %s, size: %d\n",
                Arrays.toString(nums.getBackingArray()),
                nums.size
        );
        nums.addToBack(99);
        System.out.printf("Arrays after add exceed capacity: %s, size: %d\n",
                Arrays.toString(nums.getBackingArray()),
                nums.size
        );

        System.out.println("Removed: " + nums.removeFromBack());
        System.out.println("Removed: " + nums.removeFromFront());
        System.out.printf("Arrays after removed: %s, size: %d\n",
                Arrays.toString(nums.getBackingArray()),
                nums.size
        );

    }

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
     * <p>
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast an Object[] to a T[] to get the generic typing.
     */
    public ArrayList() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Adds the data to the front of the list.
     * <p>
     * This add may require elements to be shifted.
     * <p>
     * Method should run in O(n) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (size == backingArray.length) {
            int doubleSize = backingArray.length * 2;
            T[] newArray = (T[]) new Object[doubleSize];

            System.arraycopy(backingArray, 0, newArray, 1, backingArray.length);
            newArray[0] = data;
            backingArray = newArray;
        } else {
            for (int i = size - 1; i >= 0; i--) {
                backingArray[i + 1] = backingArray[i];
            }
            backingArray[0] = data;
        }
        size++;
    }

    /**
     * Adds the data to the back of the list.
     * <p>
     * Method should run in amortized O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (size == backingArray.length) {
            int doubleSize = backingArray.length * 2;
            T[] newArray = (T[]) new Object[doubleSize];

            for (int i = 0; i < backingArray.length; i++) {
                newArray[i] = backingArray[i];
            }
            newArray[size] = data;
            backingArray = newArray;
        } else {
            backingArray[size] = data;
        }
        size++;
    }

    /**
     * Removes and returns the first data of the list.
     * <p>
     * Do not shrink the backing array.
     * <p>
     * This remove may require elements to be shifted.
     * <p>
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T removed = backingArray[0];
        for (int i = 1; i < size; i++) {
            backingArray[i - 1] = backingArray[i];
        }
        backingArray[size - 1] = null;
        size--;
        return removed;
    }

    /**
     * Removes and returns the last data of the list.
     * <p>
     * Do not shrink the backing array.
     * <p>
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T removed = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return removed;
    }

    /**
     * Returns the backing array of the list.
     * <p>
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
     * <p>
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