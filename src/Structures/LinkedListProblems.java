package Structures;

import java.util.LinkedList;

public class LinkedListProblems {
    public static void main(String[] args) {
        CustomLinkedList<Integer> arr = new CustomLinkedList<>();
        LinkedList<Integer> ll = new LinkedList<>();
        arr.add(22);
        arr.add(42);
        arr.add(52);
        arr.printAll();
        System.out.println(arr.length());
        arr.add(3,99);
        arr.printAll();
        arr.replace(0,44);
        arr.printAll();
        System.out.println(arr.get(1));
    }
}


