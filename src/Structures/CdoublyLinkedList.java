package Structures;

public class CdoublyLinkedList<T> {
    private Node head;
    private Node tail;
    private int size;

    // Adding functions
    void insertFirst(T value) {
        Node temp = new Node(value);
        temp.next = head;
        head = temp;
        size++;

        // First time
        if (tail == null) {
            tail = head;
        }
    }
    void add(T value) {
        Node temp = new Node(value);
        tail.next = temp;
        temp.previous = tail;
        tail = temp;
        size++;

        // First time
        if (head == null) {
            head = tail;
        }
    }
    void add(int index, T value) {
        if (index == 0) {
            insertFirst(value);
            return;
        }

        if(index == size) {
            add(value);
            return;
        }

        size++;
        Node temp = new Node(value);

        Node previous = findNode(index - 1);
        Node next = previous.next;

        temp.previous = previous;
        temp.next = next;

        next.previous = temp;
        previous.next = temp;
    }
    void set(int index, T value) {
    }

    // Removing functions
    void removeFirst(){}
    void remove(){}
    void remove(int index){}

    // Getting functions
    int length() {return  size;}
    T get(int index){
        Node temp = findNode(index);
        return temp.value;
    }
    private Node findNode(int index) {
        Node temp = head;
        int currentIndex = 0;
        while (temp != null) {
            if (currentIndex == index) {
                return temp;
            }
            temp = temp.next;
        }

        return new Node();
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

    @Override
    public String toString() {
        return "head=" + head + ", tail=" + tail +  ", size=" + size;
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
