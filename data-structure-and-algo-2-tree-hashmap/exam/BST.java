package exam;

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
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     * <p>
     * This should be done recursively.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to search for. You may assume data is never null.
     * @return true if the parameter is contained within the tree, false otherwise.
     */
    public boolean contains(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        return containsR(root, data);
    }

    private boolean containsR(BSTNode<T> current, T data) {
        if (current == null) {
            return false;
        }
        if (current.getData().equals(data)) {
            return true;
        }

        if (data.compareTo(current.getData()) > 0) {
            return containsR(current.getRight(), data);
        } else {
            return containsR(current.getLeft(), data);
        }
    }

    /**
     * Returns the data from the tree matching the given parameter.
     * <p>
     * This should be done recursively.
     * <p>
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to search for. You may assume data is never null.
     * @return The data in the tree equal to the parameter.
     * @throws java.util.NoSuchElementException If the data is not in the tree.
     */
    public T get(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        return getR(root, data);
    }

    private T getR(BSTNode<T> node, T data) {
        if (node == null) {
            throw new NoSuchElementException();
        }
        if (node.getData().equals(data)) {
            return node.getData();
        }
        if (node.getData().compareTo(data) > 0) {
            return getR(node.getLeft(), data);
        } else {
            return getR(node.getRight(), data);
        }

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
     * 3: The node containing the data has 2 children. Use the PREDECESSOR to
     * replace the data. You should use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
     * handle this case efficiently).
     * <p>
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     * <p>
     * Hint: Should you use value equality or reference equality?
     * <p>
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove. You may assume that data is never null.
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        BSTNode<T> dummy = new BSTNode<>(null);
        root = removeR(root, data, dummy);
        return dummy.getData();
    }

    private BSTNode<T> removeR(BSTNode<T> current, T data, BSTNode<T> dummy) {
        if (current == null) {
            throw new NoSuchElementException();
        }
        if (current.getData().compareTo(data) > 0) {
            current.setLeft(removeR(current.getLeft(), data, dummy));
        } else if (current.getData().compareTo(data) < 0) {
            current.setRight(removeR(current.getRight(), data, dummy));
        } else {
            dummy.setData(current.getData());

            if (current.getLeft() == null && current.getRight() == null) {
                current = null;
            } else if (current.getLeft() == null) {
                current = current.getRight();
            } else if (current.getRight() == null) {
                current = current.getLeft();
            } else {
                BSTNode<T> dummy2 = new BSTNode<>(null);
                current.setLeft(findPredecessor(current.getLeft(), data, dummy2));
                current.setData(dummy2.getData());
            }
            size--;
        }
        return current;
    }

    private BSTNode<T> findPredecessor(BSTNode<T> current, T data, BSTNode<T> dummy2) {
        if (current.getRight() == null) {
            dummy2.setData(current.getData());
            return current.getLeft();
        } else {
            current.setRight(findPredecessor(current.getRight(), data, dummy2));
            return current;
        }
    }

    private BSTNode<T> findSuccessor(BSTNode<T> current, T data, BSTNode<T> dummy2) {
        if (current.getLeft() == null) {
            dummy2.setData(current.getData());
            return current.getRight();
        } else {
            current.setLeft(findSuccessor(current.getLeft(), data, dummy2));
            return current;
        }
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