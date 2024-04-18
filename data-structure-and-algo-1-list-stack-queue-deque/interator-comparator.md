# Lesson 0 Itera(tor/ble) and Compara(tor/ble)

## Iterator and Iterable

- `Iterator` is independent of `Iterable`, has two methods: `next()` and `hasNext()`
- `Iterable` is dependent of `Iterator`, it has an `Iterator()`
- When we need to traverse an object which implement iterable, we can iterate over it using a `for loop`

## Comparator and Comparable

- `Comparator` and `Comparable` are independent of each other
- `Comparator` has an `compareTo()` method which return an int to represent the natural ordering. It needs to be implemented by the class that comparing itself to another object
- `Comparable` has an `compare()` method which accept two objects and return an int result. It's up to the developer to determine the ordering of things

```java
import java.util.Comparator;

class Book {
    int length;
}

class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.length - o2.length;
    }
}
```


