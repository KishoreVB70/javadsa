package Structures;

import java.util.LinkedList;

public class LinkedListProblems {
    public static void main(String[] args) {
        CustomLinkedList<Integer> arr = new CustomLinkedList<>();
        for (int i = 0; i < 20; i++) {
            arr.add(i*2);

        }
        arr.printAll();
        arr.set(3, 5);
        arr.printAll();
    }
}


