package assignment;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(6);
        TreeNode<Integer> one = new TreeNode<>(1);
        TreeNode<Integer> two = new TreeNode<>(2);
        TreeNode<Integer> five = new TreeNode<>(5);
        one.setLeft(five);
        root.setLeft(one);
        root.setRight(two);

        System.out.println("Nums of subtree is: " + countAllSubtrees(root));
    }

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    public static <T extends Comparable<? super T>> int numsOfSubtrees(TreeNode<T> root) {
        if (root == null) {
            return 0;
        }


        int leftCount = numsOfSubtrees(root.getLeft());
        int rightCount = numsOfSubtrees(root.getRight());
        int totalCount = leftCount + rightCount + 1;
        return totalCount;
    }

    public static int countAllSubtrees(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }

        int subtreesRootedAtRoot = numsOfSubtrees(root);
        int subtreesRootedAtLeft = countAllSubtrees(root.getLeft());
        int subtreesRootedAtRight = countAllSubtrees(root.getRight());

        return subtreesRootedAtRoot + subtreesRootedAtLeft + subtreesRootedAtRight;
    }

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @param <T>  Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> list = new ArrayList<>();
        getPreorderList(root, list);
        return list;
    }

    private void getPreorderList(TreeNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }

        list.add(node.getData());
        getPreorderList(node.getLeft(), list);
        getPreorderList(node.getRight(), list);
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @param <T>  Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> list = new ArrayList<>();
        getInorderList(root, list);
        return list;
    }

    private void getInorderList(TreeNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        getInorderList(node.getLeft(), list);
        list.add(node.getData());
        getInorderList(node.getRight(), list);
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     * <p>
     * This must be done recursively.
     * <p>
     * Must be O(n).
     *
     * @param <T>  Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        List<T> list = new ArrayList<>();
        getPostorderList(root, list);
        return list;
    }

    private void getPostorderList(TreeNode<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        getPostorderList(node.getLeft(), list);
        getPostorderList(node.getRight(), list);
        list.add(node.getData());
    }
}
