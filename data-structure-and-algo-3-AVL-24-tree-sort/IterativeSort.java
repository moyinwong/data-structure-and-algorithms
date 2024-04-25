import java.util.Arrays;

public class IterativeSort {

    public static void main(String[] args) {
        IterativeSort sort = new IterativeSort();
        Integer[] nums = {4, 1, 3, 7, 6, 2, 5};
        sort.minSelectionSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    public <T extends Comparable<T>> void bubbleSort(T[] list) {
        int stopIndex = list.length - 1;
        while (stopIndex > 0) {
            int lastSwapIndex = 0;
            for (int i = 0; i < stopIndex; i++) {
                if (list[i].compareTo(list[i + 1]) > 0) {
                    T temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    lastSwapIndex = i;
                }
            }
            stopIndex = lastSwapIndex;
        }
    }

    public <T extends Comparable<T>> void insertionSort(T[] list) {
        for (int i = 1; i < list.length; i++) {
            int currentIndex = i;
            while (currentIndex > 0 && list[currentIndex].compareTo(list[currentIndex - 1]) < 0) {
                T temp = list[currentIndex];
                list[currentIndex] = list[currentIndex - 1];
                list[currentIndex - 1] = temp;
                currentIndex--;
            }
        }
    }

    // max select sort
    public <T extends Comparable<T>> void maxSelectionSort(T[] list) {
        for (int i = list.length - 1; i >= 0; i--) {
            int maxIndex = i;
            for (int j = 0; j <= i; j++) {
                if (list[j].compareTo(list[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            T temp = list[i];
            list[i] = list[maxIndex];
            list[maxIndex] = temp;
        }
    }

    // min select sort
    public <T extends Comparable<T>> void minSelectionSort(T[] list) {
        for (int i = 0; i < list.length; i++) {
            int minIndex = i;
            for (int j = i; j < list.length; j++) {
                if (list[j].compareTo(list[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            T temp = list[i];
            list[i] = list[minIndex];
            list[minIndex] = temp;
        }
    }
}

