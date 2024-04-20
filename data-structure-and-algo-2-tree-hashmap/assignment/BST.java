package assignment;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Your implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     * <p>
     * This must be done recursively.
     * <p>
     * The new data should become a leaf in the tree.
     * <p>
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     * <p>
     * Should be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to add to the tree.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        root = addR(root, data);
    }

    private BSTNode<T> addR(BSTNode<T> current, T data) {
        if (current == null) {
            size++;
            return new BSTNode<>(data);
        }

        T currData = current.getData();
        if (currData.compareTo(data) > 0) {
            current.setLeft(addR(current.getLeft(), data));
        } else if (currData.compareTo(data) < 0) {
            current.setRight(addR(current.getRight(), data));
        }

        return current;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     * <p>
     * This must be done recursively.
     * <p>
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     * <p>
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }

        BSTNode<T> dummy = new BSTNode<>(null);
        root = removeR(root, data, dummy);
        return dummy.getData();
    }

    private BSTNode<T> removeR(BSTNode<T> curr, T target, BSTNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException();
        }
        T currData = curr.getData();
        if (currData.compareTo(target) > 0) {
            curr.setLeft(removeR(curr.getLeft(), target, dummy));
        } else if (currData.compareTo(target) < 0) {
            curr.setRight(removeR(curr.getRight(), target, dummy));
        } else {
            dummy.setData(curr.getData());
            if (curr.getLeft() == null && curr.getRight() == null) {
                curr = null;
            } else if (curr.getLeft() == null) {
                curr = curr.getRight();
            } else if (curr.getRight() == null) {
                curr = curr.getLeft();
            } else {
                BSTNode<T> dummy2 = new BSTNode<>(null);
                curr.setRight(removeSuccessor(curr.getRight(), dummy2));
                curr.setData(dummy2.getData());
            }
            size--;
        }
        return curr;
    }

    private BSTNode<T> removeSuccessor(BSTNode<T> curr, BSTNode<T> dummy) {
        if (curr.getLeft() == null) {
            dummy.setData(curr.getData());
            return curr.getRight();
        }
        curr.setLeft(removeSuccessor(curr.getLeft(), dummy));
        return curr;
    }

    /**
     * Returns the root of the tree.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}