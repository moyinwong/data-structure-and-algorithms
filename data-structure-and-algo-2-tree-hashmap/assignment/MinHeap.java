package assignment;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     * <p>
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     * <p>
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        //DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    public void buildHeap(T[] list) {
        if (list.length > backingArray.length) {
            backingArray = (T[]) new Comparable[list.length + 1];
        }
        for (int i = 1; i < list.length + 1; i++) {
            backingArray[i] = list[i - 1];
        }
        size = list.length;

        recursiveBuildHeap(size / 2);
    }

    private void recursiveBuildHeap(int currentIndex) {
        if (currentIndex <= 0) {
            return;
        }
        downHeap(currentIndex);
        recursiveBuildHeap(currentIndex - 1);
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     * <p>
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (backingArray.length - 1 == size) {
            resize();
        }
        size++;
        backingArray[size] = data;
        upHeap(size);
    }

    private void resize() {
        T[] newArray = (T[]) new Comparable[backingArray.length * 2];

        for (int i = 0; i < backingArray.length; i++) {
            newArray[i] = backingArray[i];
        }
        backingArray = newArray;
    }


    private void upHeap(int currentIndex) {
        if (currentIndex <= 1) {
            return;
        }
        int parentIndex = currentIndex / 2;
        T parent = backingArray[parentIndex];
        if (parent.compareTo(backingArray[currentIndex]) > 0) {
            swap(parentIndex, currentIndex);
            upHeap(parentIndex);
        }
    }

    private void swap(int index1, int index2) {
        T temp = backingArray[index1];
        backingArray[index1] = backingArray[index2];
        backingArray[index2] = temp;
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     * <p>
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T removed = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downHeap(1);
        return removed;
    }

    private void downHeap(int currentIndex) {
        if (currentIndex >= size) {
            return;
        }

        int leftChildIndex = currentIndex * 2;
        int rightChildIndex = currentIndex * 2 + 1;
        if (leftChildIndex > size && rightChildIndex > size) {
            return;
        }
        T leftChild = backingArray[leftChildIndex];
        T rightChild = backingArray[rightChildIndex];
        int childIndex = rightChildIndex <= size && leftChild.compareTo(rightChild) > 0 ? rightChildIndex : leftChildIndex;

        if (backingArray[currentIndex].compareTo(backingArray[childIndex]) > 0) {
            swap(currentIndex, childIndex);
            downHeap(childIndex);
        }
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