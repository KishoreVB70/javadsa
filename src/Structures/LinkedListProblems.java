package Structures;

import java.util.List;
import java.util.ListIterator;

public class LinkedListProblems {
    public static void main(String[] args) {
        int[] bot = {1,2,6,3,4,5,6};


        ListNode head = new ListNode(5);
        ListNode peasant1 = new ListNode(6);
//        ListNode peasant3 = new ListNode(3);
//        ListNode peasant5 = new ListNode(3);
//        ListNode peasant7 = new ListNode(4);
//        ListNode peasant9 = new ListNode(5);
//        ListNode peasant11 = new ListNode(6);

        ListNode head1 = new ListNode(5);
        ListNode peasant2 = new ListNode(4);
        ListNode peasant4 = new ListNode(9);
        head1.next = peasant2;
        peasant2.next = peasant4;

        head.next = peasant1;
//        peasant1.next = peasant3;
//        peasant3.next = peasant5;
//        peasant5.next = peasant7;
//        peasant7.next = peasant9;
//        peasant9.next = peasant11;

        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + "->");
            temp = temp.next;
        }


//        ListNode moto = addTwoNumbers(head, head1);
//
//        System.out.println("");
//
//
//        while (moto != null) {
//            System.out.print(moto.val + "->");
//            moto = moto.next;
//        }

    }

    // Most important in LL is
    // 1) Recursion is superior for some problems
    // 2) Two pointer method will be helpful for finding a node or finding cycle
    // 3) Finding middle is very important -> 2 pointer method
    // 4) Merge sort is good for sorting list
    // 5) Reversing list is important
    // 6) Re ordering is also important -> easy for the most part

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
        // No elements -> no duplicates || Only one element -> No duplicates
        if (head == null || head.next == null) {
            return head;
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
        // This a recursive approach
        if(list1!=null && list2!=null){
            if(list1.val<list2.val){
                list1.next=mergeTwoSinglyLL(list1.next,list2);
                return list1;
            }
            else{
                list2.next=mergeTwoSinglyLL(list1,list2.next);
                return list2;
            }
        }

        // Base condition, if either one is null, then return the other, it will contain all the next
        if(list1==null) return list2;
        return list1;
    }

    // 5) Find if linked list is cycled
    //https://leetcode.com/problems/linked-list-cycle/description/
    static boolean isLinkedListCycled(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = (fast.next).next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    // 5A) Find the length of the cycle
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

    // 6) Find the Start Node of the cycle
    // https://leetcode.com/problems/linked-list-cycle-ii/
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

    // 8) Find middle node of singly linked list
    // https://leetcode.com/problems/middle-of-the-linked-list/
    static ListNode findMiddle(ListNode head) {
        // Without using the length
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    // 9) Sort linked list
    //https://leetcode.com/problems/sort-list/
    static ListNode bubbleSortLinkedList(ListNode head) {
        // No element or only one element
        if (head == null || head.next == null) {
            return head;
        }

        ListNode avoider = null;

        while (avoider != head) {
            // We know first and second elements are not null
            ListNode fast = head.next;
            ListNode avg = head;
            ListNode slow = null;
            while (fast != avoider) {
                if (fast.val < avg.val) {
                    // Change the head to keep track of this clutter
                    if (avg == head) {
                        head = fast;
                    }

                    avg.next = fast.next;
                    fast.next = avg;

                    if (slow != null) {
                        slow.next = fast;
                    }

                    // Slow will become the fast as it is now behind
                    slow = fast;

                    // Move fast forward by one
                    fast = avg.next;

                    // avg will stay right there
                } else {
                    // If order is correct move everything forward by one
                    slow = avg;
                    avg = fast;
                    fast = fast.next;
                }
            }
            avoider = avg;
        }
        return head;
    }
    static ListNode mergeSortList(ListNode head) {
        // If only one -> return
        if (head == null || head.next == null) {
            return head;
        }

        ListNode mid = findMiddleBreak(head);
        ListNode left = mergeSortList(head);
        ListNode right = mergeSortList(mid);

        // Merge the two
        return mergeTwoSinglyLL(left, right);
    }
    static ListNode findMiddleBreak(ListNode head) {
        // Without using the length
        ListNode fast = head;
        ListNode slow = head;
        ListNode breaker = head;

        while (fast != null && fast.next != null) {
            breaker = slow;
            fast = fast.next.next;
            slow = slow.next;
        }

        breaker.next = null;
        return slow;
    }

    // 10) Reversal of linked list
    // Approach 1 -> bad approach =>  Same algorithm as bubble sort -> place the first element at last, and so on
    // Approach 2 -> set the current.next in recursion
    //https://leetcode.com/problems/reverse-linked-list/
    static ListNode reverseLinkedListRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        return reverseLinkedListRecursion(null, head);
    }
    static ListNode reverseLinkedListRecursion(ListNode previous, ListNode current) {
        if (current.next == null) {
            current.next = previous;
            return  current;
        }
        ListNode next = current.next;
        current.next = previous;
        return reverseLinkedListRecursion(current, next);
    }


    // Using iteration
    static ListNode reverseLinkedListIteration(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode current = head;
        ListNode next = head.next;
        while (current != null) {
            current.next = prev;
            prev = current;
            current = next;
            if (next != null) {
                next = next.next;
            }
        }
        return prev;
    }

    // 11) Only specific part of the linked list reverse
    //https://leetcode.com/problems/reverse-linked-list-ii/
    static ListNode reverseLeftAndRightNode(ListNode head, int left, int right) {

        // If there is no elements, one element, reverse the same element
        if (left == right || head == null || head.next == null) {
            return head;
        }

        ListNode temp = head;
        ListNode leftNode = null;
        ListNode rightNode = null;
        ListNode rightNext = null;
        ListNode leftPrevious = null;

        int length = 1;
        while (temp != null) {
            if (length == left - 1) {
                leftPrevious = temp;
                leftNode = temp.next;
            }

            if (length == left) {
                leftNode = temp;
            }

            if (length == right) {
                rightNode = temp;
                // Check for the last element
                rightNext = temp.next;
                rightNode.next = null;
                break;
            }
            length++;
            temp = temp.next;
        }

        if (leftNode == null) {
            return head;
        }

        ListNode reversedHead =  reverseLinkedListRecursion(leftNode);
        leftNode.next = rightNext;

        if (leftPrevious != null) {
            leftPrevious.next = reversedHead;
        } else {
            head = reversedHead;
        }

        return head;
    }

    // 12) Is linked list palindrome
    //https://leetcode.com/problems/palindrome-linked-list/
    static boolean isLinkedListPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }

        if (head.next == null) {
            return  true;
        }

        // 1) Find the mid
        ListNode mid = findMiddleBreak(head);

        // 2) Reverse the right part
        ListNode reversed = reverseLinkedListIteration(mid);

        // 3)Compare one by one and if same palindrome
        while (head != null && reversed != null) {
            if (head.val != reversed.val) {
                return false;
            }
            head = head.next;
            reversed = reversed.next;
        }

        return true;
    }

    // 13) Re order linked list
    // https://leetcode.com/problems/reorder-list/
    static void reOrderLL (ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        if (head.next.next == null) {
            return;
        }

        // 1) Find mid
        ListNode mid = findMiddleBreak(head);

        // 2) Reverse second half
        ListNode reversed = reverseLinkedListIteration(mid);

        ListNode newList = head;
        ListNode currentNode = head.next;
        // 3) Create new LL, first element will be from the first half, second element will be from the second half
        while (currentNode != null && reversed != null) {
            ListNode leftNext = currentNode.next;
            ListNode rightNext = reversed.next;

            currentNode.next = null;
            reversed.next = null;
            newList.next = reversed;
            reversed.next = currentNode;
            newList = currentNode;
            currentNode = leftNext;
            reversed = rightNext;
        }

        while (currentNode != null) {
            newList.next = currentNode;
            newList = newList.next;
            currentNode = currentNode.next;
        }
        while (reversed != null) {
            newList.next = reversed;
            newList = newList.next;
            reversed = reversed.next;
        }
    }

    // 14) Hard -> Reverse Nodes in K group
    //https://leetcode.com/problems/reverse-nodes-in-k-group/
    static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) {
            return  head;
        }

        ListNode previous = null;
        ListNode current = head;
        ListNode next = head.next;
        int i = 1;

        ListNode lp = null;
        ListNode l = null;
        ListNode r = null;
        ListNode rn = null;

        while (current != null) {
            // 1 -> identify the left node
            if (i == 1) {
                lp = previous;
                l = current;
            }

            // 2 -> identify the right node
            if (i == k) {
                r = current;
                rn = next;
                r.next = null;

                // 3 -> once both identified, rotate
                ListNode reversed = reverseLinkedListIteration(l);

                // If null, head will be pointing to it, if not null, previous would be pointing

                // Reversed will be the first element
                if (lp != null) {
                    lp.next = reversed;
                } else {
                    head = reversed;
                }

                // Left will become the last element and hence point to the right next
                l.next = rn;
                i = 1;
                previous = l;
                current = rn;
                if (rn != null) {
                    next = rn.next;
                }
                continue;
            }

            // Move on if right not acquired
            current = next;
            previous = current;
            if (next != null) {
                next = next.next;
            }
            i++;
        }
        return  head;
    }

    // 15) Rotate linked list
    static ListNode rotateLLBasedOnK(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 1-> find the size of the linked list
        Object[] lengthAndTail = lengthAndTail(head);
        int length = (int) lengthAndTail[0];
        ListNode actualTail = (ListNode) lengthAndTail[1];


        // Base condition
        if (k % length == 0) {
            return head;
        }

        // 2-> Find the index of the element that is to become head
        int indexOfHead;

        // in case k is larger than length
        if (k > length) {
            k = k % length;
        }

        // Note -> index starts from 1 and not 0
        indexOfHead = length - k;

        // 3-> obtain the node that is to become tail and the node that is to become head
        ListNode tail = head;
        ListNode newHead;

        for (int i = 1; i <indexOfHead ; i++) {
            tail = tail.next;
        }
        // end of the cycle, tail will become tail
        newHead = tail.next;
        tail.next = null;
        actualTail.next = head;
        head = newHead;

        return  head;
    } 
    static Object[] lengthAndTail(ListNode head) {
        int i = 1;
        while (head.next != null ) {
            head = head.next;
            i++;
        }

        return  new Object[]{i, head};
    }
    static int lengthOfNormalLL(ListNode head) {
        if (head == null) {
            return 0;
        }

        int i = 1;
        while (head.next != null ) {
            head = head.next;
            i++;
        }


        return i;
    }
    static ListNode rotateLLOneTime(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 1 -> find the tail
        ListNode prev = null;
        ListNode tail = head;
        while (tail.next != null) {
            prev = tail;
            tail = tail.next;
        }
        // Once the loop breaks, prev will be t-1 -> new tail
        prev.next = null;
        tail.next = head;
        head = tail;
        return  head;
    }

    // 16) Easy easy problem doing for streak
    public static ListNode removeElements(ListNode head, int val) {
        while(head != null) {
            if (head.val == val) {
                head = head.next;
                continue;
            }
            break;
        }

        if (head == null || head.next == null) {
            return head;
        }


        ListNode previous = head;
        ListNode current = head.next;

        while (current !=  null) {
            if (current.val == val) {
                previous.next = current.next;
            } else {
                previous = current;
            }
            current = current.next;
        }
        return head;
    }

    // 17) Medium problem -> 2nd problem of leet code
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(0);
            ListNode tail = dummyHead;
            int carry = 0;

            while (l1 != null || l2 != null || carry != 0) {
                int digit1 = (l1 != null) ? l1.val : 0;
                int digit2 = (l2 != null) ? l2.val : 0;

                int sum = digit1 + digit2 + carry;
                int digit = sum % 10;
                carry = sum / 10;

                ListNode newNode = new ListNode(digit);
                tail.next = newNode;
                tail = tail.next;

                l1 = (l1 != null) ? l1.next : null;
                l2 = (l2 != null) ? l2.next : null;
            }

            ListNode result = dummyHead.next;
            dummyHead.next = null;
            return result;
        }
    }
























}