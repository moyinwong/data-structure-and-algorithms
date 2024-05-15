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
- **initial length**
    - usually a prime number to avoid collision
- **collision**
    - where different hash codes results in same index after compression
- **load factor**
    - size / capacity, this is the number that determine when should the array be resized
    - if adding an entry to map cause the ratio to exceed the load factor, then resized is needed
    - normally set at 0.75
- **resize**
    - when adding exceed defined load factor, resized needed
    - to space out entry and avoid collisions, maintaining O(1) add, remove and search
    - normally resized to `2n + 1` length where `n` is the current table length
    - For every entry, need to recompress to find the new index in new array (`key % new array.length`)

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

The index after hashing and compression is the final index where the pair will be put. When pair collides, they are
being put at the same index.

#### External chaining

- use a `Linkedlist` to do this
    - `add`
        1. check if duplicate key through chain
        2. if yes, update pair with new value
        3. if no, put pair at the start of the chain
    - `remove`
        1. find entry with matching key
        2. store the value
        3. remove the entry
        4. return the value
    - `search`
        1. iterate through the chain and find matching key

### Open addressing

The index after hashing and compression may NOT be the final index where the pair will be put due to **probing**

#### Linear probing

- `add`
    - if collision, which means an index is already occupied by another entry
    - then add the result of hashing and compression (a.k.a original index) with the **times of probing**
    - e.g. `7 % 5 = 2`, `2` is occupied, then `2 + 1 = 3 % 5`, if `3` is occupied, then `2 + 2 % 5= 4`.
      Continue `original index + Prob i % table.length` until:
        1. Entry with the same key is found. Then update old value with new value
        2. `null` index is found. Store the entry at that index
        3. First DEL marker is saved and later encounter `null` index. Then store entry at DEL marker index (refer
           to `remove`)
- `search`
    - Compute the original index
    - if key is not at that index, continue to the next index (if at end of array, look back around) until:
        1. key is found. Return value
        2. reaching an index with `null` then key is not in table
        3. if already iterate through `table.length` times but not found, then key is not in the table
- `remove`

  Making an index null does not work, as there might be entry with the same original index and store somewhere else due
  to probing.<br/>

  It would make `search` not work as it would stop at `null` and determine a particular key is not found but in fact it
  might be stored at later index.

    - Therefore, we need to do **soft removal**, we would just mark the entry as deleted but still keep it in the array
    - sometimes called a DEL(deleted/defunct) marker

#### Quadratic probing

To avoid problem of linear probing, **primary clustering**, where different keys collide to form one big group occupying
contiguous block in the map. Causing `add`, `remove` and `search` to be $O(n)$ as need to iterate through the shifted
index

- `add`
    - the procedure is the same with linear probing but instead of linearly adding the index with number of probing, add
      the square of times of probing
    - e.g. `7 % 5 = 2`, `2` is occupied. Then `2 + 1^2 % 5 = 3`, if `3` is occupied, then `2 + 2^2 % 5 = 1`.
      Continue `original index + prob i^2 % table.length` until:
        - same conditions with linear probing
        - **HOWEVER**, it possible for quadratic probing to not reach particular indices and stuck in **infinite probing
          **
        - so resize is needed when the times of probing already reach `table.length` or other factors
- `search`
    - the procedure is the same with linear probing but looks for `original index + prob i^2` for next index
- `remove` same as Linear probing

#### Double hashing

To avoid problem of linear and quadratic probing, making it less likely for entry to cluster together when collision
occurs. Every key has a different rate of change. Changing a constant to something that vary break up sequence pattern

- use a **secondary hash function** to compute a constant
    - e.g. let result of first hash function be `H(key)` and `D(key` be secondary function
    - it could be `D(key) = q - H(key) % q`, where `q` is a prime number
    - this function only takes the key as the input
    - the output must not be 0 and need to ensure all indices can be reached
- then **add the original index** with the **multiple of constant and number of probes
  ** `original index + (c = D(key)) * P sub i % table.length`

## Read Further

- Other Hashing data structure, Bloom Filter (allow false positive, "possibly yes" and "absolutely no")
- ![notes](resources/hashmap-collision-note.png)