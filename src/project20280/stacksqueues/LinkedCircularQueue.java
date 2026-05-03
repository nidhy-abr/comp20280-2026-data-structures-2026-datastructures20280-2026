package project20280.stacksqueues;

import project20280.interfaces.Queue;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            data = e;
            next = n;
        }

        public E getData() {
            return data;
        }

        public void setNext(Node<E> n) {
            next = n;
        }

        public Node<E> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;  // We maintain only tail reference
    private int size = 0;

    public LinkedCircularQueue() {
        // Constructor - nothing to initialize beyond default values
    }

    /**
     * Rotates the front element to the back of the queue.
     * This is equivalent to enqueue(dequeue()) but more efficient.
     */
    public void rotate() {
        if (tail != null) {
            tail = tail.getNext();  // The head becomes the new tail
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        Node<E> newNode = new Node<>(e, null);

        if (isEmpty()) {
            // First element - points to itself
            newNode.setNext(newNode);
            tail = newNode;
        } else {
            // Insert at end (after tail)
            newNode.setNext(tail.getNext());  // New node points to head
            tail.setNext(newNode);           // Current tail points to new node
            tail = newNode;                  // New node becomes the tail
        }

        size++;
    }

    @Override
    public E first() {
        if (isEmpty()) {
            return null;
        }
        // Head is tail.getNext()
        return tail.getNext().getData();
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        Node<E> head = tail.getNext();  // Get the head (front of queue)
        E data = head.getData();

        if (size == 1) {
            // Only one element - queue becomes empty
            tail = null;
        } else {
            // Skip the head node
            tail.setNext(head.getNext());
        }

        size--;
        return data;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        Node<E> current = tail.getNext();  // Start at head

        do {
            sb.append(current.getData());
            current = current.getNext();
            if (current != tail.getNext()) {
                sb.append(", ");
            }
        } while (current != tail.getNext());

        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedCircularQueue<Integer> queue = new LinkedCircularQueue<>();

        System.out.println("Initial queue: " + queue);
        System.out.println("Is empty? " + queue.isEmpty());

        // Test enqueue
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        System.out.println("After enqueue 10, 20, 30: " + queue);
        System.out.println("Size: " + queue.size());
        System.out.println("First: " + queue.first());

        // Test dequeue
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("After dequeue: " + queue);

        // Test rotate
        queue.rotate();
        System.out.println("After rotate: " + queue);

        // More operations
        queue.enqueue(40);
        queue.enqueue(50);
        System.out.println("After enqueue 40, 50: " + queue);

        // Test multiple dequeues
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("Dequeue: " + queue.dequeue());
        System.out.println("After multiple dequeues: " + queue);

        // Test empty queue operations
        System.out.println("Dequeue from empty: " + queue.dequeue());
        System.out.println("First from empty: " + queue.first());
        System.out.println("Is empty? " + queue.isEmpty());

        // Test with strings
        LinkedCircularQueue<String> stringQueue = new LinkedCircularQueue<>();
        stringQueue.enqueue("Hello");
        stringQueue.enqueue("World");
        stringQueue.enqueue("!");
        System.out.println("String queue: " + stringQueue);
        stringQueue.rotate();
        System.out.println("After rotate: " + stringQueue);
    }
}