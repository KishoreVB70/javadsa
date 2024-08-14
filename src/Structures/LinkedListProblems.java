package Structures;

import java.util.LinkedList;

public class LinkedListProblems {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode peasant1 = new ListNode(4);
        ListNode peasant3 = new ListNode(5);
        ListNode peasant5 = new ListNode(6);
        ListNode peasant7 = new ListNode(7);
        ListNode peasant9 = new ListNode(8);

//        ListNode head1 = new ListNode(1);
//        ListNode peasant2 = new ListNode(3);
//        ListNode peasant4 = new ListNode(4);
//        head1.next = peasant2;
//        peasant2.next = peasant4;

        head.next = peasant1;
        peasant1.next = peasant3;
        peasant3.next = peasant5;
        peasant5.next = peasant7;
        peasant7.next = peasant9;
        peasant9.next = peasant1;
        System.out.println(isHappyNumber(2));

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
        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = (fast.next).next;
            slow = slow.next;
            if (fast == slow) {
                System.out.println(fast.val);
                return true;
            }
        }
        return false;
    }

    //5A) Find the length of the cycle
    static int lengthOfLinkedListCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = (fast.next).next;
            slow = slow.next;
            if (fast == slow) {
                int i = 0;
                do {
                    slow = slow.next;
                    i++;
                } while (fast != slow);
                return i;
            }
        }

        return -1;
    }

    //6) Find the Start Node of the cycle
    static ListNode startNodeOfCycle(ListNode head) {
        // 1 -> get the length
        int length = lengthOfLinkedListCycle(head);
        if (length == -1) {
            return null;
        }
        // 2 -> move first pointer the length into the array
        ListNode first = head;
        ListNode second = head;
        for (int i = 0; i < length; i++) {
            first = first.next;
        }
        // 3 -> Move first and second back till they meet
        while (first != second) {
            second = second.next;
            first = first.next;
        }
        return first;
    }

    // 7) Happy number
    //https://leetcode.com/problems/happy-number/

    static boolean isHappyNumber(int n) {
        int fastPointer = n;
        int slowPointer = n;

        // Do this two times
        while (fastPointer != 1) {
            for (int i = 0; i < 2 ; i++) {
                int tempNum = 0;
                while (fastPointer > 9) {
                    int lastDigit = fastPointer % 10;
                    fastPointer /= 10;
                    tempNum += lastDigit * lastDigit;
                }
                tempNum += fastPointer * fastPointer;
                fastPointer = tempNum;
            }

            if (fastPointer == 1) {
                return true;
            }

            int tempNum = 0;
            while (slowPointer > 9) {
                int lastDigit = slowPointer % 10;
                slowPointer /= 10;
                tempNum += lastDigit * lastDigit;
            }
            tempNum += slowPointer * slowPointer;
            slowPointer= tempNum;

            if (fastPointer == slowPointer) {
                return false;
            }

        }
        return true;
    }

























}


































