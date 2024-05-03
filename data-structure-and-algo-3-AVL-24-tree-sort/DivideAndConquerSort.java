import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class DivideAndConquerSort {

    public static void main(String[] args) {
        DivideAndConquerSort sort = new DivideAndConquerSort();
        int[] nums = {4, 3, 1, 5, 2, 6, 7};
        sort.lsdRadixSort(nums);
        System.out.println(Arrays.toString(nums));

        System.out.println(-9 % 10);

    }

    /*
     * Merge sort
     * */
    public <T extends Comparable<T>> void mergeSort(T[] list) {
        if (list.length == 1) {
            return;
        }
        int mid = list.length / 2;

        T[] left = Arrays.copyOfRange(list, 0, mid);
        T[] right = Arrays.copyOfRange(list, mid, list.length);

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    public <T extends Comparable<T>> void merge(T[] list, T[] left, T[] right) {
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex].compareTo(right[rightIndex]) < 0) {
                list[leftIndex + rightIndex] = left[leftIndex];
                leftIndex++;
            } else if (left[leftIndex].compareTo(right[rightIndex]) > 0) {
                list[leftIndex + rightIndex] = right[rightIndex];
                rightIndex++;
            }
        }

        while (leftIndex < left.length) {
            list[leftIndex + rightIndex] = left[leftIndex];
            leftIndex++;
        }
        while (rightIndex < right.length) {
            list[leftIndex + rightIndex] = right[rightIndex];
            rightIndex++;
        }

    }

    /*
     * Quick sort
     * */
    public <T extends Comparable<T>> void quickSort(T[] list) {
        qSort(list, 0, list.length - 1);
    }

    public <T extends Comparable<T>> void qSort(T[] list, int start, int end) {
        if (end - start < 1) {
            return;
        }
        int pivot = partition(list, start, end);
        qSort(list, start, pivot - 1);
        qSort(list, pivot + 1, end);
    }

    public <T extends Comparable<T>> int partition(T[] list, int start, int end) {
        int pivotIndex = randomPivotIndex(start, end);
        T pivotValue = list[pivotIndex];
        swap(list, pivotIndex, start);
        int i = start + 1;
        int j = end;

        while (leftAndRightNotCrossed(i, j)) {
            while (leftAndRightNotCrossed(i, j) && list[i].compareTo(pivotValue) < 0) {
                i++;
            }

            while (leftAndRightNotCrossed(i, j) && list[j].compareTo(pivotValue) > 0) {
                j--;
            }

            if (leftAndRightNotCrossed(i, j)) {
                swap(list, i, j);
                i++;
                j--;
            }
        }

        swap(list, j, start);
        return j;
    }

    private <T> void swap(T[] list, int index1, int index2) {
        T temp = list[index1];
        list[index1] = list[index2];
        list[index2] = temp;
    }

    private boolean leftAndRightNotCrossed(int i, int j) {
        return i <= j;
    }

    private int randomPivotIndex(int start, int end) {
        Random random = new Random();
        return random.nextInt(start, end + 1);
    }

    /*
     * LSD Radix sort
     * */
    public void lsdRadixSort(int[] list) {
        // cater negative numbers
        Queue<Integer>[] buckets = new LinkedList[19];
        Arrays.fill(buckets, new LinkedList<>());

        int k = findMaxDigitis(list);
        int div = 1;
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < list.length; j++) {
                int valForDigit = list[j] / div % 10 + 9;
                buckets[valForDigit] = new LinkedList<>();
                buckets[valForDigit].offer(list[j]);
            }
            int currentIndex = 0;
            for (Queue<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    int num = bucket.poll();
                    list[currentIndex] = num;
                    currentIndex++;
                }
            }
            div = div * 10;
        }
    }

    private <T> int findMaxDigitis(int[] list) {
        int max = Math.abs(list[0]);

        for (int j : list) {
            if (Math.abs(j) > max) {
                max = Math.abs(j);
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
