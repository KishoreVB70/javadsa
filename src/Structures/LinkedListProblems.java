package Structures;

public class LinkedListProblems {
    public static void main(String[] args) {
        CustomLinkedList<Integer> arr = new CustomLinkedList<>();
        arr.add(22);
        arr.add(42);
        arr.add(52);
        arr.printAll();
        System.out.println(arr.length());
        arr.set(3,99);
        arr.printAll();
        arr.replace(0,44);
        arr.printAll();

    }
}


