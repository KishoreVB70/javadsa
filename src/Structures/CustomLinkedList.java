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
    void addRecursion(int index, T value) {
        Node current = head;
        if (index == 0) {
            insertFirst(value);
            return;
        }
        helperAddRecursion(index, 0, value, current);
    }
    private void helperAddRecursion(int index, int currentIndex, T value, Node currentNode) {
        // Base condition
        if (currentNode == null) {
            System.out.println("Index out of range!");
            return;
        }
        // Success condition
        if (currentIndex == index - 1) {
            // This means it's the tail
            if (currentNode.next == null) {
                add(value);
                return;
            }

            // Not tail or head
            Node temp = new Node(value);
            Node next = currentNode.next;
            currentNode.next = temp;
            temp.next = next;
            this.size++;
            return;
        }

        // Next one
        helperAddRecursion(index, currentIndex+1, value, currentNode.next);
    }
    void addBeforeValueRecursion(T target, T value) {
        if (head.value == target) {
            insertFirst(value);
            return;
        }
        helperAddBeforeValueRecursion(target, value, head);
    }
    Node  helperAddBeforeValueRecursion(T target, T value, Node currentNode) {
        // Base condition
        if (currentNode == null) {
            return currentNode;
        }

        // Success condition
        if (currentNode.value == target) {
            Node temp = new Node(value);
            temp.next = currentNode;
            return temp;
        }

        currentNode.next = helperAddBeforeValueRecursion(target, value, currentNode.next);
        return  currentNode;
    }
    void set(int index, T value) {
        if (index > size) {
            return;
        }

        Node temp = findTheNode(index);
        temp.value = value;
    }
    Node find(T value) {
        Node temp = head;
        while (temp != null) {
            if (temp.value == value) {
                return temp;
            }
            temp = temp.next;
        }
        return new Node();
    }



    void removeHead() {
        // Nothing to remove
        if (head == null) {
            return;
        }

        // If only one element
        if(head == tail) {
            tail = null;
        }

        head = head.next;
    }
    void remove(int index) {
        if (index == 0) {
            removeHead();
            return;
        }

        if (index == size - 1) {
            removeTail();
            return;
        }

        if (index >= size) {
            return;
        }

        Node previous = findTheNode(index - 1);
        previous.next = (previous.next).next;
    }
    void removeTail() {
        // Nothing to remove
        if (tail == null) {
            return;
        }

        // If only one element
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }

        // 1) Find element before tail
        Node temp = head;
        while (temp.next != tail) {
            temp = temp.next;
        }

        // 2) Set it as the tail
        tail = temp;
        tail.next = null;
    }

    T get(int index) {
        Node temp = findTheNode(index);
        if (index > size) {
            return null;
        }
        return temp.value;
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
        System.out.println("]");

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
