package Structures;

import java.util.LinkedList;

public class LinkedListProblems {
    public static void main(String[] args) {
        addSinglyRecursion(2, 99);
    }

    // 1) Insert in singly linked list using recursion
    static void  addSinglyRecursion(int index, int value) {
        CustomLinkedList<Integer> arr = new CustomLinkedList<>();
        for (int i = 0; i <6; i++) {
            arr.add(i*2);
        }
        arr.printAll();
        arr.addRecursion(index, value);
        arr.printAll();
    }

}


