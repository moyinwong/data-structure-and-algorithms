# Iterative sort

## Key measurement

1. **Time complexity**
2. **Stability,** a sorting algorithm is stable if data with duplicate values maintain their relative order after the
   sort.
    - E.g. A name list `[Alice Wong, Kelvin Wong, Bob Wong]`.
    - First sort by first name then last name. After a stable sort, Alice should still come before Bob
3. **Adaptivity,** a sorting algorithm is adaptive if it can utilize data that are in sorted order and terminate sort
   early
4. **Memory management**, is the sorting done in-place (`O(1)`) or out-of-place (require additional memory)

## Bubble sort

- Time complexity
    - Best
        - With optimization, `n-1`
        - Without, `n(n-1)/2`
    - Average
        - With optimization, multiple of `n`
        - Without, `n(n-1)/2`
    - Worst
        - both with and without optimization is `n(n-1)/2`
- stable
- adaptive with last swapped optimization
- memory, in place sort

```java
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
```

## Cocktail Shaker sort

Just two bubble sort (front to back & back to front) per iteration

- Time complexity
    - Best
        - With optimization, `n-1`
        - Without, `n(n-1)/2`
    - Average
        - With optimization, multiple of `n`
        - Without, `n(n-1)/2`
    - Worst
        - both with and without optimization is `n(n-1)/2`
- stable
- adaptive with last swapped optimization
- memory, in place sort
- could be more efficient than bubble sort in case the min element is put at the last index.
    - e.g. `2,3,4,5,1`. It would take bubble sort `n(n-1)/2` to sort
    - but it takes Cocktail Shaker sort 1.5 iteration (around 3 bubble sort) to sort it

```java
public <T extends Comparable<T>> void cocktailShakerSort(T[] list) {
    int start = 0;
    int end = list.length - 1;
    while (start < end) {
        int lastSwappedIndex = start;
        for (int i = start; i < end; i++) {
            if (list[i].compareTo(list[i + 1]) > 0) {
                T temp = list[i];
                list[i] = list[i + 1];
                list[i + 1] = temp;
                lastSwappedIndex = i;
            }
        }
        end = lastSwappedIndex;

        for (int j = end; end > start; j--) {
            if (list[j].compareTo(list[j - 1]) < 0) {
                T temp = list[j];
                list[j] = list[j - 1];
                list[j - 1] = temp;
                lastSwappedIndex = j;
            }
        }
        start = lastSwappedIndex;
    }
}
```

## Insertion sort

- Time complexity
    - Best
        - `n`
    - Average
        - `n(n-1)/2`
    - Worst
        - reversed sorted list, `n(n-1)/2`
- stable
- adaptive by design
- memory, in place sort

```java
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
```

## Selection sort

- Time complexity
    - Best
        - `n(n-1)/2`
    - Average
        - `n(n-1)/2`
    - Worst
        - `n(n-1)/2`
- unstable, don't know what's swapped with the max number
- not adaptive, need to iterate the full loop to ensure no other max data, max data is swapped with itself even it's
  sorted
- memory, in place sort
- have an advantage in numbers of swap, most cases will do better than bubble & insertion sort
- can use min/max for sort, depends on implementation

```java
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
```
