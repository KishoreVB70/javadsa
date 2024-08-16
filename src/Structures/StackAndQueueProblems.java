package Structures;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class StackAndQueueProblems {

    public static void main(String[] args) {
        int[] rectangle = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea2(rectangle));
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

    // 4) Largest rectangle in histogram -> Ez Pz
    // https://leetcode.com/problems/largest-rectangle-in-histogram/
    public static int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }

        int largestRectangle = 0;
        boolean isNotOver = false;
        int current = 1;
        int sum = 0;
        while (!isNotOver) {
            if (sum > largestRectangle) {
                largestRectangle = sum;
            }
            isNotOver = true;
            int i = 0;
            sum = 0;

            while (i < heights.length) {
                if (heights[i] >= current) {
                    isNotOver = false;
                    sum+= current;
                } else {
                    if (sum > largestRectangle) {
                        largestRectangle = sum;
                    }
                    sum = 0;
                }
                i++;
            }
            current++;
        }

        return largestRectangle;

    }
    public static int largestRectangleArea2(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }

        int largestRectangle = 0;
        for (int i = 0; i < heights.length; i++) {
            int sum = heights[i];

            int j = i-1;
            // Go in the left direction
            while (j >= 0) {
                if (heights[j] >= heights[i]) {
                    sum += heights[i];
                    j--;
                } else {
                    break;
                }
            }

            j = i+1;
            // Go in the right direction
            while (j < heights.length) {
                if (heights[j] >= heights[i]) {
                    sum += heights[i];
                    j++;
                } else {
                    break;
                }
            }

            largestRectangle = Integer.max(largestRectangle, sum);
        }
        return largestRectangle;

    }

}

