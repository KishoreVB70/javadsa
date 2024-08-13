package Structures;

public class CircularLL<T> {
    private Node head;
    private Node tail;
    private  int size;

    // Adding
    void insertFirst(T value) {
        size++;
        Node temp = new Node(value);
        if (head == null) {
            temp.previous = temp;
            temp.next = temp;
            tail = temp;
            head = temp;
            return;
        }
        temp.next = head;
        temp.previous = tail;

        head.previous = temp;
        tail.next = temp;

        head = temp;
    }

    void add(T value) {
        size++;
        Node temp = new Node(value);
        if (tail == null) {
            temp.previous = temp;
            temp.next = temp;
            tail = temp;
            head = temp;
            return;
        }

        temp.next = head;
        temp.previous = tail;
        head.previous = temp;
        tail.next = temp;
        tail = temp;
    }

    void  printElements() {
        Node temp = head;
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(temp.value + ", ");
            temp = temp.next;
        }
        System.out.println("]");
    }

    public class Node {
        private T value;
        private Node previous;
        private Node next;

        Node(T value) {
            this.value = value;
            this.previous = null;
            this.next = null;
        }

        Node() {
            this.next = null;
            this.value = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return value + "";
        }
    }
}
