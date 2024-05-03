package exam;

import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of LSD Radix Sort.
 */
public class Sorting {

    /**
     * Implement LSD (least significant digit) radix sort.
     * <p>
     * It should be: out-of-place stable not adaptive
     * <p>
     * Have a worst case running time of: O(kn) And a best case running time of:
     * O(kn)
     * <p>
     * For this question, you are given k: the number of digits in the greatest
     * magnitude number in arr. Because of this, you do not need to make an initial
     * O(n) pass through to determine this number.
     * <p>
     * At no point should you find yourself needing a way to exponentiate a number;
     * any such method would be non-O(1). Think about how how you can get each power
     * of BASE naturally and efficiently as the algorithm progresses through each
     * digit.
     * <p>
     * You may use an ArrayList or LinkedList if you wish. Be sure the List
     * implementation you choose allows for stability while being as efficient as
     * possible.
     * <p>
     * Do NOT use anything from the Math class except Math.abs().
     * <p>
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     * @param k   The number of digits in the greatest magnitude number in arr.
     */
    public static void lsdRadixSort(int[] arr, int k) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        Queue<Integer>[] buckets = new LinkedList[19];

        int div = 1;
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < arr.length; j++) {
                int valForDigit = arr[j] / div % 10 + 9;
                if (buckets[valForDigit] == null) {
                    buckets[valForDigit] = new LinkedList<>();
                }
                buckets[valForDigit].offer(arr[j]);
            }
            int currentIndex = 0;
            for (Queue<Integer> bucket : buckets) {
                while (bucket != null && !bucket.isEmpty()) {
                    int num = bucket.poll();
                    arr[currentIndex] = num;
                    currentIndex++;
                }
            }
            div = div * 10;
        }
    }

    /**
     * Implement bubble sort.
     * <p>
     * It should be: in-place stable adaptive
     * <p>
     * Have a worst case running time of: O(n^2) And a best case running time of:
     * O(n)
     * <p>
     * NOTE: You should implement bubble sort with the last swap optimization.
     * <p>
     * You may assume that the passed in array and comparator are both valid and
     * will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        int stopIndex = arr.length - 1;
        while (stopIndex > 0) {
            int lastSwappedIndex = 0;
            for (int i = 0; i < stopIndex; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    lastSwappedIndex = i;
                }
            }
            stopIndex = lastSwappedIndex;
        }
    }
}