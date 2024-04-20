# HashMap (Map and Set ADT)

## Hash Function, Compression, Load factor

### Key terms

- **key**
    - the original key used for hashing. It must be
        - unique
        - immutable

      It could be ordered (optional, if keys are sorted, it's called ordered map)
- **hash code**
    - the result of applying the hashing function, **must be integers**, the more unique the better
- **capacity**
    - the backing array's capacity, usually length in prime number to reduce collision after compression
- **compression**
    - the hash code might be large and we don't want create an array of size 10000 when there's only 5 items.
    - therefore we want to compress the hash code so that it fits in to the current size of the array
- **index**
    - the index to put the key/value pair in the backing array, the result of compression

Given two objects `a` & `b`, if `a.equals(b)`, then `a.hashcode == b.hashcode`. But if we only
know`a.hashcode == b.hashcode`, we cannot guarantee `a.equals(b)`.

From the above, we can infer:

- keys that have the same hash code will always result to the same index (if no resizing involve)
- if there are duplicate keys, then hash map would replace the value with newer value

## Collision Handling polices

Index might be the same for different hash codes, causing **collision**.

E.g. given a backing array with capacity of `8`, Using keys `40` & `80`, mod by `8` will produce same `0` index for
storing the values

### Closed addressing

- external chaining, put pair at the same index
- use a linkedlist to do this, add new pair
    1. check if duplicate key through chain
    2. if yes, update pair with new value
    3. if no, put pair at the start of the chain

### Open addressing

## Read Further

- Other Hashing data structure, Bloom Filter (allow false positive, "possibly yes" and "absolutely no")