package Structures;

public class CustomLinkedList<T> {
    private int size;
    private Node tail;
    private Node head;

    void insertFirst(T value) {
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
    void add(int index, T value) {
        if (index == 0) {
            insertFirst(value);
            return;
        }

        if (index == size) {
            add(value);
            return;
        }

        if (index > size) {
            return;
        }

        Node tempPrevious = findTheNode(index - 1);
        Node newNode = new Node(value);

        Node tempNext = tempPrevious.next;
        tempPrevious.next = newNode;

        newNode.next = tempNext;
        size++;

    }
    void replace(int index, T value) {
        if (index > size) {
            return;
        }

        Node temp = findTheNode(index);
        temp.value = value;
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

    T get(int index) {
        Node temp = findTheNode(index);
        if (index > size) {
            return null;
        }
        return (T) temp.value;
    }
    int length() {
        return size;
    }
    private Node findTheNode(int index) {
        // Find the node
        int currentIndex = 0;
        Node temp = head;
        while (currentIndex < index) {
            if (temp == null) {
                return new Node();
            }
            temp = temp.next;
            currentIndex++;
        }
        return temp;
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
