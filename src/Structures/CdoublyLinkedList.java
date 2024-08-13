package Structures;

public class CdoublyLinkedList<T> {


    public class Dnode {
        private T value;
        private Dnode previous;
        private Dnode next;

        Dnode(T value) {
            this.value = value;
            this.previous = null;
            this.next = null;
        }

        Dnode() {
            this.next = null;
            this.value = null;
            this.next = null;
        }
    }
}
