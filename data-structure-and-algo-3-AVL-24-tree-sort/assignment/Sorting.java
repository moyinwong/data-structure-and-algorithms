package assignment;

import java.util.Comparator;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

    /**
     * Implement bubble sort.
     * <p>
     * It should be:
     * in-place
     * stable
     * adaptive
     * <p>
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     * <p>
     * NOTE: You should implement bubble sort with the last swap optimization.
     * <p>
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        int stopIndex = arr.length - 1;
        while (stopIndex > 0) {
            int lastSwapIndex = 0;
            for (int i = 0; i < stopIndex; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    lastSwapIndex = i;
                }
            }
            stopIndex = lastSwapIndex;
        }
    }

    private static <T> void swap(T[] arr, int index1, int index2) {
        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * Implement selection sort.
     * <p>
     * It should be:
     * in-place
     * unstable
     * not adaptive
     * <p>
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     * <p>
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        for (int i = arr.length - 1; i >= 0; i--) {
            int maxIndex = i;
            for (int j = 0; j < i; j++) {
                if (comparator.compare(arr[j], arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }

            swap(arr, i, maxIndex);
        }
    }

    /**
     * Implement insertion sort.
     * <p>
     * It should be:
     * in-place
     * stable
     * adaptive
     * <p>
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     * <p>
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        for (int i = 1; i < arr.length; i++) {
            int currentIndex = i;
            while (currentIndex > 0 && comparator.compare(arr[currentIndex], arr[currentIndex - 1]) < 0) {
                swap(arr, currentIndex, currentIndex - 1);
                currentIndex--;
            }
        }
    }
}