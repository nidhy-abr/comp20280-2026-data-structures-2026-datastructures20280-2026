package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private  E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            // TODO
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            // TODO
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            // TODO
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        // TODO
        return size;
    }

    //@Override
    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    @Override
    public E get(int position) {
        // TODO
        if (position < 0 || position >= size)
            throw new IndexOutOfBoundsException();

        Node<E> curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.getNext();
        }
        return curr.getElement();
    }


    @Override
    public void add(int position, E e) {
        // TODO
        if (position < 0 || position > size){
            throw new IndexOutOfBoundsException();
        }

        if (position == 0){
            addFirst(e);
            return;
        }
        Node <E> prev = head;
        for (int i = 0; i < position - 1; i++) {
            prev = prev.getNext();
        }

        Node <E> newNode = new Node<>(e, prev.getNext()); //store element and point to the next element
        prev.setNext(newNode);
        size++;


    }


    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e, head);
        head = newNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        // TODO
        add(size, e);
    }

    @Override
    public E remove(int position) {
        // TODO
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (position == 0) {
            return removeFirst();
        }

        Node<E> prev = head;
        for (int i = 0; i < position - 1; i++) {
            prev = prev.getNext();
        }

        Node<E> removed = prev.getNext();
        prev.setNext(removed.getNext());
        size--;
        return removed.getElement();
    }




    @Override
    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }

        E removed = head.getElement();
        head = head.getNext();
        size--;
        return removed;
    }


    @Override
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }

        if (size == 1) {
            return removeFirst();
        }

        Node<E> prev = head;
        for (int i = 0; i < size - 2; i++) {
            prev = prev.getNext();
        }

        E removed = prev.getNext().getElement();
        prev.setNext(null);
        size--;
        return removed;
    }


    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public void reverse() {
        head = reverseRec(head);
    }

    private Node<E> reverseRec(Node<E> curr) {
        if (curr == null || curr.getNext() == null) {
            return curr;
        }

        Node<E> newHead = reverseRec(curr.getNext());

        curr.getNext().setNext(curr);
        curr.setNext(null);

        return newHead;
    }

    public SinglyLinkedList<E> recursiveCopy() {
        SinglyLinkedList<E> copy = new SinglyLinkedList<>();
        copy.head = copyRec(this.head);
        copy.size = this.size;
        return copy;
    }

    private Node<E> copyRec(Node<E> node) {
        if (node == null) return null;

        Node<E> newNode = new Node<>(node.getElement(), null);
        newNode.setNext(copyRec(node.getNext()));
        return newNode;
    }


    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        //ll.removeLast();
        //ll.removeFirst();
        //System.out.println("I accept your apology");
        //ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);
        ll.reverse();
        System.out.println("Reversed: " + ll);
        SinglyLinkedList<Integer> copy = ll.recursiveCopy();
        System.out.println("Copy: " + copy);

    }
}
