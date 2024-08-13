package Structures;

public class CustomLinkedList<T> {
    public CustomLinkedList() {
        Node temp = new Node();
        head = temp;
        tail = temp;
    }

    Node tail;
    Node head;


    public void addAtHead(T value) {
        if (head.value == null) {
            head.value = value;
            return;
        }

        Node temp = new Node(value);
        temp.next = head;
        head = temp;
    }

    public void add(T value) {
        // If fresh linked list
        if (head.value == null) {
            head.value = value;
            return;
        }

        Node temp = new Node(value);
        tail.next = temp;
        tail = temp;
    }

    public T removeHead() {
        // If head can't be removed
        if (head == tail) {
            return head.value;
        }

        T value = head.value;
        head = head.next;
        return value;
    }

    public T removeTail() {
        // If tail can't be removed
        if (head == tail) {
            return head.value;
        }

        // 1) Find element before tail
        Node current = head;
        while (current.next != tail) {
            current = current.next;
        }

        T value = tail.value;
        // 2) Set it as the tail
        tail = current;
        tail.next = null;

        return value;
    }

    @Override
    public String toString() {
        return " head=" + head + " tail=" + tail;
    }

    private class Node {
        T value;
        Node next;

        Node (T value) {
            this.value = value;
            this.next = null;
        }
        Node () {
            this.value = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return value + "";
        }
    }
}
