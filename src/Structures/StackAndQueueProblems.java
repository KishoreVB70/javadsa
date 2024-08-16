package Structures;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class StackAndQueueProblems {

    public static void main(String[] args) {
        String bot = "()";
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
            return --count;
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
            progress1 = helperTwoStacksRecursion(a.subList(1, a.size()), b, sum + aValue, count + 1, maxSum);

            int bValue = b.getFirst();
            progress2 = helperTwoStacksRecursion(a, b.subList(1, b.size()), sum + bValue, count + 1, maxSum);
        }

        if (a.isEmpty()) {
            int bValue = b.getFirst();
            progress1 = 0;
            progress2 = helperTwoStacksRecursion(a, b.subList(1, b.size()), sum + bValue, count + 1, maxSum);
        }

        if (b.isEmpty()) {
            int aValue = a.getFirst();
            progress2 = 0;
            progress1 = helperTwoStacksRecursion(a.subList(1, a.size()), b, sum + aValue, count + 1, maxSum);
        }


        if (progress1 > progress2) {
            return progress1;
        } else {
            return progress2;
        }
    }

    // 4) Largest rectangle in histogram
    // https://leetcode.com/problems/largest-rectangle-in-histogram/
    // The first 2 are brute force approaches which worked, but was not efficient in terms of time complexity
    public static int FailureLargestRectangleArea(int[] heights) {
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
                    sum += current;
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
    public static int FailureLargestRectangleArea2(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }

        int largestRectangle = 0;
        for (int i = 0; i < heights.length; i++) {
            int sum = heights[i];

            int j = i - 1;
            // Go in the left direction
            while (j >= 0) {
                if (heights[j] >= heights[i]) {
                    sum += heights[i];
                    j--;
                } else {
                    break;
                }
            }

            j = i + 1;
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
    public static int largestRectangle(int[] heights) {
        // Create new stack
        Stack<Integer> stack = new Stack<>();

        // currently there is no max, hence 0
        int max = 0;

        // Push first item into stack
        stack.push(0);

        // 0 is already in the stack so starting off with 1
        // We are looping through all the indices of the array
        for (int i = 1; i < heights.length; i++) {
            // Stack should not be empty
            // The current index element must be greater than the top of the stack -> Ascending order
            // If not ascending order, then break

            // This is the part of finding if it is not ascending, if it is ascending, then stack.push

            // If it is not in ascending order, then find the max till that point
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]) {
                max = getMax(heights, stack, max, i);
            }
            // Ascending order == push
            stack.push(i);
        }


        // When everything is ascending till that point, stack will not be empty, and hence empty it
        // There is nothing special with this i, it is there as the previous i was inside the for loop
        int i = heights.length;
        while (!stack.isEmpty()) {
            max = getMax(heights, stack, max, i);
        }

        return max;
    }
    static int getMax(int[] heights, Stack<Integer> stack, int max, int i) {
        int area;
        // If it is the last element in the stack, then it means, it is the smallest till i. Hence multiply it till i
        int popped = stack.pop();
        if (stack.isEmpty()) {
            area = heights[popped] * i;
        }
        // If the stack is not empty, then only that index + indexes till i should be counted

        // This is essentially getting the individual count
        else {
            // You will get how many elements are adhering to this easily
            // i - 1 indicates that remove the last one -> Till the last one
            // - stack.peek indicates remove the previous one
            // Very simple -> We are accounting for everything from the previous to the last.
            area = heights[popped] * ((i - 1) - stack.peek());
        }

        return Math.max(max, area);
    }

    // 5) Valid parenthesis
    //https://leetcode.com/problems/valid-parentheses/
    public static boolean isValidParenthesis(String s) {
        if (s.isEmpty()) {
            return false;
        }
        String p = s;
        Stack<Character> stackOut = new Stack<Character>();

        while (!p.isEmpty()) {
            char c = p.charAt(0);
            switch (c) {
                case '(':
                    stackOut.push(')');
                    break;
                case '{':
                    stackOut.push('}');
                    break;
                case '[':
                    stackOut.push(']');
                    break;
                default:
                    if (stackOut.isEmpty() || c != stackOut.pop()) {
                        return false;
                    }
                    stackOut.pop();
            }
            p = p.substring(1);
        }
        return stackOut.isEmpty();
    }
    public static int minAddToMakeValid(String s) {
        int count = 0;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) {
                count++;
                continue;
            }
            stack.pop();
        }

        while (!stack.isEmpty()) {
            stack.pop();
            count++;
        }

        return count;
    }













}

