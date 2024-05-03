package assignment;

import java.util.*;


class Count {
    public int count;
}

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */
public class DivdeAndConquerSort {
    public static void main(String[] args) {
//        int[] nums = {-1, 0, 3, 2};
//        DivdeAndConquerSort.lsdRadixSort(nums);

        Integer[] nums = {3, 2, 1, 4, 9, 5, 7};
        DivdeAndConquerSort.mergeSort(nums, Comparator.comparingInt(a -> a));
        System.out.println(Arrays.toString(nums));
    }

    /**
     * Implement merge sort.
     * <p>
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     * <p>
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     * <p>
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     * <p>
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     * <p>
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     * <p>
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        mergeSortR(arr, 0, arr.length, comparator);
    }

    private static <T> void mergeSortR(T[] list, int start, int end, Comparator<T> comparator) {
        if (end - start <= 1) {
            return;
        }

        int mid = (end - start) / 2;
        mergeSortR(list, start, start + mid, comparator);
        mergeSortR(list, start + mid, end, comparator);
        merge(list, start, start + mid, end, comparator);
    }

    private static <T> void merge(T[] list, int leftHalfStart, int rightHalfStart, int end, Comparator<T> comparator) {
        int leftHalfSize = rightHalfStart - leftHalfStart;
        int rightHalfSize = end - rightHalfStart;

        T[] leftList = (T[]) new Object[leftHalfSize];
        T[] rightList = (T[]) new Object[rightHalfSize];

        for (int i = 0; i < leftHalfSize; i++) {
            leftList[i] = list[i + leftHalfStart];
        }
        for (int j = 0; j < rightHalfSize; j++) {
            rightList[j] = list[j + rightHalfStart];
        }

        int leftListIndex = 0;
        int rightListIndex = 0;

        int smallestIndex = leftHalfStart;

        while (leftListIndex < leftHalfSize && rightListIndex < rightHalfSize) {
            if (comparator.compare(leftList[leftListIndex], rightList[rightListIndex]) <= 0) {
                list[smallestIndex] = leftList[leftListIndex];
                leftListIndex++;
            } else {
                list[smallestIndex] = rightList[rightListIndex];
                rightListIndex++;
            }

            smallestIndex++;
        }

        while (leftListIndex < leftHalfSize) {
            list[smallestIndex] = leftList[leftListIndex];
            leftListIndex++;
            smallestIndex++;
        }

        while (rightListIndex < rightHalfSize) {
            list[smallestIndex] = rightList[rightListIndex];
            rightListIndex++;
            smallestIndex++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     * <p>
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     * <p>
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     * <p>
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     * <p>
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     * <p>
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     * <p>
     * Do NOT use anything from the Math class except Math.abs().
     * <p>
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (arr.length == 0) {
            return;
        }

        long k = findMaxDigitis(arr);
        Queue<Integer>[] buckets = new LinkedList[19];

        int div = 1;
        for (int i = 0; i < k; i++) {
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

    private static int findMaxDigitis(int[] list) {
        long max = Math.abs((long) list[0]);

        for (int j : list) {
            if (Math.abs((long) j) > max) {
                max = Math.abs((long) j);
            }
        }

        int digit = 0;

        while (max >= 1) {
            max = max / 10;
            digit++;
        }

        return digit;
    }
}
