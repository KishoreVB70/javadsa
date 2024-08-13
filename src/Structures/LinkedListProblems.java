package Structures;

import java.util.LinkedList;

public class LinkedListProblems {
    public static void main(String[] args) {
        CdoublyLinkedList<Integer> arr= new CdoublyLinkedList<>();
        arr.insertFirst(12);
        arr.printElements();
        arr.add(21);
        arr.add(0,99);
        arr.printElements();
    }
}


