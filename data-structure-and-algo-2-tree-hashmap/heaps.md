# Binary Heap

**Tree-type** data structure implementing the **Priorty Queue ADT**. Implmented by using **backing array**

Heap must have 2 properties:

1. **Shape Property**: It's a complete tree, with every level filled (except the last level) with no gap, from left to
   right
2. **Order Property**: Every node's child must have a less priority than itself
    - e.g. child must be larger than the parent in `MinHeap`
    - e.g. child must be less than the parent in `MaxHeap`

It has the **highest priority item at the root** and maintain this order when add/remove.

Conventionally Heaps use **1-indexing**. Given index n, can quickly locate the index of child/parent

- left child: `2n`
- right child: `2n + 1`
- parent: `n/2`

Given `n` nodes in a Heap, the `h` as height of the heap would be $log(n) + 1$.
The number of nodes at last level (if full) would be $2^h / 2$

## Operations

### Add (Upheap)

1. add the data at the end of backing array
2. compare with parent at index / 2, if less/larger (depends on heap type), swap
3. continue with the new index of parent until reaching root or no swap needed

#### Time Complexity

- Worst case: $O(n)$ as we need to resize backing array by coping from previous array
- Worst case amortized: $O(log(n))$
- Best case: $O(1)$

### Remove (Downheap)

1. set the root with data from last leaf node (end of backing array)
2. remove the last node to maintain structure (set the last leaf node to null)
3. start from root node, find the max/min (max for MaxHeap, min for MinHeap) of two child
4. Compare it with the node, if the child node has higher priority, swap it with the current node
5. continue step 3 & 4 with the swapped child node's index as the current node until no swapping is need / going out of
   array size

#### Time Complexity

- Worst case: $O(log(n))$
- Best case: $O(log(n))$

### Build heap from existing list

1. copy the list to the backing array
2. starting from the last node with child (a.k.a the node at index `n/2`), do:
    1. Perform `downHeap` recursively from the current node until the function return
    2. moving to a previous node (decrement the current index by 1)
3. continue steps in 2 until current index <= 1

#### Time Complexity

- Worst case: $O(n)$
- Best case: $O(n)$ as we need to read all input to verify order even if no swapping needed
