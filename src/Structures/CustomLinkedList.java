package Structures;

public class CustomLinkedList<T> {
    private int size;
    private Node tail;
    private Node head;

    void addAtHead(T value) {
         Node temp = new Node(value);
         temp.next = head;
         head = temp;
         size++;

         // Fresh linked list
        if (tail == null) {
            tail = head;
        }
    }

    void add(T value) {
        Node temp = new Node(value);
        size++;

        if (tail == null) {
            tail = temp;
            head = tail;
            return;
        }
        tail.next = temp;
        tail = temp;
    }

     void set(int index, T value) {
        if (index == 0) {
            addAtHead(value);
            return;
        }

        if (index == size) {
            add(value);
            return;
        }

        Node tempPrevious = head;
        int currentIndex = 0;

        while (currentIndex < index -1) {
            tempPrevious = tempPrevious.next;
            currentIndex++;
        }

        Node newNode = new Node(value);

        Node tempNext = tempPrevious.next;
        tempPrevious.next = newNode;

        newNode.next = tempNext;

    }

    T removeHead() {
        // If head can't be removed
        if (head == tail) {
            return head.value;
        }

        T value = head.value;
        head = head.next;
        return value;
    }

    T removeTail() {
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

    int length() {
        return size;
    }

    void printAll() {
         Node temp = head;

        System.out.print("[");
         while (temp != null) {
             System.out.print(temp.value + ",");
             temp = temp.next;
         }
        System.out.print("]");

    }



    @Override
    public String toString() {
        return " head=" + head + " tail=" + tail;
    }

    private class Node {
        private T value;
        private Node next;

        Node (T value) {
            this.value = value;
            this.next = null;
        }

        @Override
        public String toString() {
            return value + "";
        }
    }
}
