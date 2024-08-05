import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {
        int[] nums = {5,1,2,3,4};
        System.out.println(findInRotatedArray(nums, 0));
    }

    // Array problems

    static int findInRotatedArray(int[] nums, int target) {
        // 1) find pivot
        int pivot = findPivotInRotateArray(nums);

        // 2) Check if pivot is the target
        if (nums[pivot] == target) {
            return pivot;
        }

        // 3) If non-rotated array
        if (pivot == nums.length - 1) {
            return binarySearchViaRecursion(nums, 0 , pivot, target);
        }

        // 4) If a target out of bounds is given
        if (target > nums[pivot] || target < nums[pivot+1]) {
            return -1;
        }

        // 5) If rotated array
        return target < nums[0]
                ?binarySearchViaRecursion(nums, pivot+1, nums.length-1, target)
                :binarySearchViaRecursion(nums, 0, pivot -1, target);
    }

    static int findPivotInRotateArray(int[] nums) {
        int end = nums.length-1;
        // If no pivot
        if (nums[0] < nums[end/2] && nums[end/2] < nums[end]) {
            return end;
        }

        return helperFindPivotInRotateArray(nums, 0, end);

    }
    static int helperFindPivotInRotateArray(int[] nums, int start, int end) {

        // Base condition
        if (start >= end) {
            return start;
        }

        int m = (start + end) / 2;

        // If pivot is found
        if (nums[m] > nums[m+1]) {
            return m;
        } else if (nums[m-1] > nums[m]) {
            return  m-1;
        }

        // If no pivot
        if (nums[m] < nums[start]) {
            return helperFindPivotInRotateArray(nums, start, m-2);
        } else {
            return helperFindPivotInRotateArray(nums, m+1, end);
        }
    }

    static List<Integer> linearSearchMultipleIndex(int[] nums, int num) {
        List<Integer> list = new ArrayList<>(1);
        helperLinearSearchMultipleIndex(nums, num, 0, list);
        return list;
    }
    static void helperLinearSearchMultipleIndex(int[] nums, int num, int n, List<Integer> list) {
        if (nums[n] == num) list.add(n);

        if (n == nums.length - 1) {
            return;
        }
        helperLinearSearchMultipleIndex(nums, num, n+1, list);
    }
    static boolean linearSearchBool(int[] nums, int num) {
        return helperLinearSearchBool(nums, num, 0);
    }
    static boolean helperLinearSearchBool(int[] nums, int num, int n) {
        if (n == nums.length -1 ) {
            return (nums[n] == num);
        }

        // Either this num must be num or the next should be n
        return nums[n] == num || helperLinearSearchBool(nums, num, n+1);

    }
    static int linearSearchIndex(int[] nums, int num) {
        return helperLinearSearchIndex(nums, num, 0);
    }
    static int helperLinearSearchIndex(int[] nums, int num, int n) {
        if (n == nums.length -1 ) {
            return (nums[n] == num)?n:-1;
        }

        // If it is the num, return the index, if not, return the next result
        return (nums[n] == num)?n:helperLinearSearchIndex(nums, num, n+1);
    }
    static boolean findIfArrayIsSorted(int[] n) {
        return helperFindIfArrayIsSortedLinear(n, 0);
    }
    static  boolean helperFindIfArrayIsSortedLinear(int[] arr, int n) {
        // Base condition
        if (n == arr.length - 1) {
            return true;
        }
        if (arr[n] <= arr[n+1]) {
            return helperFindIfArrayIsSortedLinear(arr, n+1);
        }
        return false;
    }
    // Flopped problem
    static boolean helperFindIfArrayIsSortedBinary(int[] n, int start, int end) {
        int m = (start + end) / 2;
        // Base Condition
        if (start >= end) {
            return true;
        }

        if (n[start] <= n[m] && n[m] <= n[end] ) {
            return helperFindIfArrayIsSortedBinary(n,start+1, end-1 );
        }
        else {
            return false;
        }
    }


    // Basic easy questions
    static int numberOfStepsToReduceTo0(int n) {
        // Base condition
        if (n == 0) {
            return 0;
        }
        if (n % 2 == 0) {
            return 1 + numberOfStepsToReduceTo0(n/2);
        }
        else return 1+ numberOfStepsToReduceTo0(n-1);
    }
    static int numberOfZeros(int number) {
        // Base condition
        if (number < 10) {
            return number == 0?1:0;
        }

        if (number % 10 == 0) {
            return 1 + numberOfZeros(number/10);
        }
        return numberOfZeros(number/10);
    }
    static boolean palindromeNumber(int number) {
        return  (number == reverseANumber(number));
    }
    static int reverseANumber(int number) {
        // Base condition
        if (number < 10) {
            return number;
        }

        // Finding the position of the number
        int digits =  (int)Math.log10(number);
        int position = (int) Math.pow(10, digits);

        // Obtaining the last number
        int lastDigit = number % 10;
        int newNumber = lastDigit * position;

        // Returning
        return (newNumber + reverseANumber(number/10));
    }
    static int productOfDigitsOfNum(int n) {
        if (n / 10 < 1) {
            return n;
        }
        return (n % 10) * productOfDigitsOfNum(n/10);
    }
    static int sumOfDigitsOfNum(int n) {
        if (n / 10 < 1) {
            return n;
        }
        return (n % 10) + sumOfDigitsOfNum(n/10);
    }
    static int factorialMan(int n) {
        if (n <= 2) {
            return n;
        }
        return n * factorialMan(n-1);
    }
    static int binarySearchViaRecursion(int[] nums, int start, int end, int target) {
        // Base condition -> target is not in the array
        if (start > end) {
            return -1;
        }

        int n = ( start + end ) / 2;
        int currentValue = nums[n];

        if (currentValue == target) {
            // Base condition -> target is found
            return n;
        }
        else if (currentValue > target) {
            return binarySearchViaRecursion(nums, start, n-1, target);
        }
        else  {
            return binarySearchViaRecursion(nums, n+1, end, target);
        }
    }
    static int fibonacciNumber(int n) {
        if (n == 1) {
            return 1;
        }

        if (n <= 0) {
            return n;
        }
        return (fibonacciNumber(n-1) + fibonacciNumber(n-2));
    }
    static void printNumbers(int n) {
        if (n== 0) {
            return;
        }
        printNumbers(n-1);
        System.out.println(n);
    }
    static  void printHelloWorld(int n) {
        if (n == 1) {
            System.out.println("Hello world");
            return;
        }
        System.out.println("Hello world");
        printHelloWorld(n-1);
    }
}