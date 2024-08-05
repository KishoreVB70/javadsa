public class Recursion {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,9,10,12,13};
        System.out.println(reverseANumber(1342));
    }


    static int reverseANumber(int number) {
        // Base condition
        if (number / 10 < 1) {
            return number;
        }

        // Finding the position of the number
        int position = 1;
        for (int i = number; i >= 10; i= i/10) {
            position *= 10;
        }

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
