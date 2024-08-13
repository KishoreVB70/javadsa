package Structures;

import java.util.LinkedList;

public class LinkedListProblems {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode peasant1 = new ListNode(2);
        ListNode peasant3 = new ListNode(4);

        ListNode head1 = new ListNode(1);
        ListNode peasant2 = new ListNode(3);
        ListNode peasant4 = new ListNode(4);

        head.next = peasant1;
        peasant1.next = peasant3;
        head1.next = peasant2;
        peasant2.next = peasant4;


        ListNode thala = mergeTwoSinglyLL(head, head1);
        while (thala != null) {
            System.out.println(thala.val);
            thala = thala.next;
        }
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

    // 3) Remove duplicates from singly linked list
    //https://leetcode.com/problems/remove-duplicates-from-sorted-list/
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

    // 4) Remove duplicates from sorted list
    //https://leetcode.com/problems/merge-two-sorted-lists/
    static ListNode mergeTwoSinglyLL(ListNode list1, ListNode list2) {
        // Case 1 -> both are null
        if (list1 == null && list2 == null) {
            return null;
        }
        // Case -> list 1 is null
        if (list1 == null && list2 != null) {
            return list2;}
        // Case -> list 1 is null
        if (list2 == null && list1 != null) {
            return list1;
        }


        // Actual stuff
        int smallest;
        if (list1.val < list2.val) {
            smallest = list1.val;
            list1 = list1.next;
        } else  {
            smallest = list2.val;
            list2 = list2.next;
        }
        ListNode tempThala = new ListNode(smallest);
        ListNode thala = tempThala;


        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                tempThala.next = list1;
                tempThala = tempThala.next;
                list1 = list1.next;
            } else  {
                tempThala.next = list2;
                tempThala = tempThala.next;
                list2 = list2.next;
            }
        }

        // Add the remaining
        while (list2 != null) {
            tempThala.next = list2;
            tempThala = tempThala.next;
            list2 = list2.next;
        }
        while (list1 != null) {
            tempThala.next = list1;
            tempThala = tempThala.next;
            list1 = list1.next;
        }

        return thala;
    }

    //5) Find if linked list is cycled
    static boolean isLinkedListCycled(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && slow != null) {
            if (fast == slow) {
                return true;
            }

            if (fast.next == null) {
                return false;
            }

            fast = (fast.next).next;
            slow = slow.next;
        }

        return false;
    }
}


