package exam;

import java.util.Arrays;

/**
 * Your implementation of a MaxHeap.
 */
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 10;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MaxHeap.
     * <p>
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MaxHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }
    
    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     * <p>
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add. You may assume that data will always be valid.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (backingArray.length - 1 == size) {
            resize();
        }
        size++;
        System.out.println("size: " + size);
        backingArray[size] = data;
        upHeap(size);
    }

    private void upHeap(int index) {
        if (index <= 1) {
            return;
        }
        int parentIndex = index / 2;
        if (backingArray[parentIndex].compareTo(backingArray[index]) < 0) {
            swap(parentIndex, index);
            upHeap(parentIndex);
        }
    }

    private void resize() {
        T[] newArray = (T[]) new Comparable[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            newArray[i] = backingArray[i];
        }
        backingArray = newArray;
    }


    /**
     * Removes and returns the max item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     * <p>
     * Method should run in O(log n) time.
     * <p>
     * You may assume that the heap is not empty.
     *
     * @return The data that was removed.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        T removed = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downHeap(1);
        return removed;
    }

    private void downHeap(int index) {
        T current = backingArray[index];
        int leftChildIndex = index * 2;
        int rightChildIndex = index * 2 + 1;
        if (leftChildIndex > size && rightChildIndex > size) {
            return;
        }
        int childIndex = rightChildIndex > size ? leftChildIndex :
                backingArray[rightChildIndex].compareTo(backingArray[leftChildIndex]) > 0 ? rightChildIndex : leftChildIndex;
        T child = backingArray[childIndex];

        if (current.compareTo(child) < 0) {
            swap(index, childIndex);
            downHeap(childIndex);
        }
    }

    private void swap(int index1, int index2) {
        T temp = backingArray[index1];
        backingArray[index1] = backingArray[index2];
        backingArray[index2] = temp;
    }

    /**
     * Returns the backing array of the heap.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}