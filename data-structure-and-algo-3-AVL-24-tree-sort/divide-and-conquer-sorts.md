# Divide and Conquer sorts

## Merge sorts

Halving arrays into subarrays until reaching base case of length 1. Then merging those subarrays and put data in sorted
positions

- Time complexity, same across cases as needed to halve and merge no matter the order
    - Best
        - O(nlog(n))
    - Average
        - O(nlog(n))
    - Worst
        - O(nlog(n))
- stable
- not adaptive
- memory intensive, need to allocate spaces for new subarrays. O(n) spaces needed

```java
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
```

## Quick Sort

Find a pivot in each recursive call and do partitioning. Such that everything **left to the pivot is less than** the
pivot
and everything **right to the pivot is larger than** the pivot. Then recursive call sort on left & right subarray again

Pivot is **guaranteed to be in its final position** after partitioning

- Time complexity, same across cases as needed to halve and merge no matter the order
    - Best
        - O(nlog(n))
    - Average
        - O(nlog(n))
    - Worst
        - O(n^2), the pivot is always the max/min elements, end up comparison like selection sort on every adjacent
          elements
- not stable due to partitioning
- not adaptive
- in place, but use O(log(n)) average memory because of recursive call

```java
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
```

## LSD (least significant digit) Radix Sort

No comparison. Needed extra memories for "buckets" (array of queues (linkedlists)). Ascending sort starting for the LSD,
in base 10, starting from digit 1, 2...

- Time complexity, `n` is the input size, where `k` is number of digits of the number that has the largest absolute
  value. E.g. `[-9432, 1, 100]`, `k` is 4, `-9432` is the number with the largest number of digits
    - Best
        - O(kn)
    - Average
        - O(kn)
    - Worst
        - O(kn)
- stable
- not adaptive
- memory intensive, need additional memory for buckets

```java
public void lsdRadixSort(int[] list) {
    // cater negative numbers
    Queue<Integer>[] buckets = new LinkedList[19];
    Arrays.fill(buckets, new LinkedList<>());

    // find the maximum digits of a integer in the list
    int k = findMaxDigitis(list);
    int div = 1;

    // for each digit, iterative over the numbers
    // put them into digit buckets based on their value of that digit
    // after the list is iterated through, dequeue them from buckets and put it original list
    // repeat for second digit and so on
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
```

## Read further

- tim sort
- intro sort

