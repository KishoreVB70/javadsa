package Structures;

public class LinkedListProblems {
    public static void main(String[] args) {
        CustomLinkedList<Integer> arr = new CustomLinkedList<>();
        arr.add(22);
        System.out.println(arr);
        arr.add(42);
        System.out.println(arr);
        arr.add(52);
        System.out.println(arr);
        arr.printAll();
        System.out.println(arr.length());
        arr.set(2,99);
        arr.printAll();

    }
}


