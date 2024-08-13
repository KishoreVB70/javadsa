package Structures;

import java.util.LinkedList;

public class LinkedListProblems {
    public static void main(String[] args) {
        ListNode arr = new ListNode(20);
        arr.next = new ListNode(40);
        arr.next = new ListNode(40);
        arr.next = new ListNode(40);


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

    // 1b) Insert in doubly linked list using recursion
    static void addDoublyRecursion(int index, int value) {
        CdoublyLinkedList<Integer> arr  = new CdoublyLinkedList<>();
        for (int i = 0; i <6; i++) {
            arr.add(i*2);
        }
        arr.printElements();
        arr.addRecursion(index, value);
        arr.printElements();
    }
    // 1c) Insert in circular singly linked list recursion

    // 2) Insert before a specific value single linked list recursion
    static void insertBeforeValueRecursion(int target, int value) {
        CustomLinkedList<Integer> arr = new CustomLinkedList<>();
        for (int i = 0; i <6; i++) {
            arr.add(i*2);
        }
        arr.printAll();
        arr.addBeforeValueRecursion(target, value);
        arr.printAll();
    }

    // 3 Remove duplicates from singly linked list
    static public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   }

    static ListNode removeDuplicateSinglyLinked(ListNode head) {
        if (head == null) {
            return null;
        }
        head.next = removeDuplicateSinglyLinked(head.next, head.val);
        return head;
    }

    static ListNode  removeDuplicateSinglyLinked(ListNode currentNode, int previousValue) {
        // Base condition
        if (currentNode == null) {
            return null;
        }

        // Remove condition
        if (currentNode.val == previousValue) {
            return removeDuplicateSinglyLinked(currentNode.next, currentNode.val);

        }

        currentNode.next =  removeDuplicateSinglyLinked(currentNode.next, currentNode.val);
        return currentNode;

    }






}


