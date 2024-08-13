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
        if (tail.value == null) {
            tail = head;
        }
    }

     void add(T value) {
        Node temp = new Node(value);
        tail.next = temp;
        tail = temp;
        size++;

         // If fresh linked list
         if (head.value == null) {
             head = tail;
         }
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
