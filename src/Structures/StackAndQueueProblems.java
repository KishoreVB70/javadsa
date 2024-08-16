package Structures;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class StackAndQueueProblems {

    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        int param_3 = obj.peek();
        System.out.println(param_3);
        int param_2 = obj.pop();
        System.out.println(param_2);
        boolean param_4 = obj.empty();
        System.out.println(param_4);
    }

    //1) Implementing Queue with stack
    static class MyQueue {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;


        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            // 1) Empty stack 1 into stack 2
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }

            // 2) Add x into stack 1
            stack1.push(x);

            // 3) Empty stack 2 into stack 1
            while (!stack2.empty()) {
                stack1.push(stack2.pop());
            }
        }

        public int pop() {
            // Ezz
            return stack1.pop();
        }

        public int peek() {
            return stack1.peek();
        }

        public boolean empty() {
            return stack1.empty();
        }
    }

    // 2) Implement Stack with Queue -> Incomplete
    class MyStack {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        private Queue<Integer> queue1;
        private Queue<Integer> queue2;


        public MyStack() {
//            queue1 = new Queue<Integer>();
//            queue2 = new Queue<Integer>();
        }

        public void push(int x) {
            // 1) Empty stack 1 into stack 2
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }

            // 2) Add x into stack 1
            stack1.push(x);

            // 3) Empty stack 2 into stack 1
            while (!stack2.empty()) {
                stack1.push(stack2.pop());
            }
        }

        public int pop() {
            // Ezz
            return stack1.pop();
        }

        public int top() {
            return stack1.peek();
        }

        public boolean empty() {
            return stack1.empty();
        }
    }

    // 3) Game of two stacks
    //https://www.hackerrank.com/challenges/game-of-two-stacks/problem
    public static int twoStacks(int maxSum, List<Integer> a, List<Integer> b) {
        return helperTwoStacksRecursion(a, b, 0, 0, maxSum);
    }

    public static int helperTwoStacksRecursion(List<Integer> a, List<Integer> b, int sum, int count, int maxSum) {
        // Base condition for size
        if (sum > maxSum) {
            return  --count;
        }

        // Base condition for empty
        if (a.isEmpty() && b.isEmpty()) {
            return count;
        }

        int progress1 = 0;
        int progress2 = 0;

        // If both are not empty, two recursion call
        if (!a.isEmpty() && !b.isEmpty()) {
            int aValue = a.getFirst();
            progress1 = helperTwoStacksRecursion(a.subList(1, a.size()), b, sum + aValue, count+1, maxSum);

            int bValue = b.getFirst();
            progress2 = helperTwoStacksRecursion(a, b.subList(1, b.size()), sum + bValue, count+1, maxSum);
        }

        if (a.isEmpty()) {
            int bValue = b.getFirst();
            progress1 = 0;
            progress2 = helperTwoStacksRecursion(a, b.subList(1, b.size()), sum + bValue, count+1, maxSum);
        }

        if (b.isEmpty()) {
            int aValue = a.getFirst();
            progress2 = 0;
            progress1 = helperTwoStacksRecursion(a.subList(1, a.size()), b, sum + aValue, count+1, maxSum);
        }


        if (progress1 > progress2) {
            return  progress1;
        } else {
            return  progress2;
        }
    }


}

