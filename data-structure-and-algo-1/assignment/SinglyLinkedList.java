package assignment;

import java.util.*;
import java.util.ArrayList;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */


    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.poll()

        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addToFront(4);
        list.addToFront(3);
        list.addToFront(2);
        list.addToFront(1);
        list.addToFront(0);

        System.out.println("After adding -----");
        System.out.println(list);


        System.out.println("Removed: " + list.removeFromFront() + " size: " + list.size);
        System.out.println("Removed: " + list.removeFromFront() + " size: " + list.size);
        System.out.println("Removed: " + list.removeFromFront() + " size: " + list.size);
        System.out.println("Removed: " + list.removeFromBack() + " size: " + list.size);
        System.out.println("Removed: " + list.removeFromBack() + " size: " + list.size);
        System.out.println("After removing ------");
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        a[0];
    }

//    @Override
//    public String toString() {
//        SinglyLinkedListNode<T> current = head;
//        StringBuilder res = new StringBuilder();
//        while (current != null) {
//            res.append(", ").append(current.getData());
//            current = current.getNext();
//        }
//        return res.toString();
//    }

    /**
     * Adds the element to the front of the list.
     * <p>
     * Method should run in O(1) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data, head);
        if (size == 0) {
            tail = newNode;
        }
        head = newNode;
        size++;
    }

    /**
     * Adds the element to the back of the list.
     * <p>
     * Method should run in O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (size == 0) {
            addToFront(data);
            return;
        }

        tail.setNext(new SinglyLinkedListNode<T>(data));
        tail = tail.getNext();
        size++;
    }

    /**
     * Removes and returns the first data of the list.
     * <p>
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T removed = head.getData();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return removed;
    }

    /**
     * Removes and returns the last data of the list.
     * <p>
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new NoSuchElementException();
        } else if (size == 1) {
            T data = tail.getData();
            head = null;
            tail = null;
            size--;
            return data;
        }

        T removed = tail.getData();
        SinglyLinkedListNode<T> current = head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }
        current.setNext(null);
        tail = current;
        size--;
        return removed;
    }

    /**
     * Returns the head node of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the list.
     * <p>
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}