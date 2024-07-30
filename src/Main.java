import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        greatestOfThree();
//        upperOrLower();
//        nthFibonacciNum();
//        countingOccurrence();
//        BasicLoopProblems.reverseTheNum();

    }

    public static class BasicLoopProblems {
        public static void reverseTheNum() {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the number of which you want to reverse");
            int n = in.nextInt();
            int reverse = 0;
            while (n > 0) {
                int lastDigit = n % 10;
                n = n / 10;
                reverse = (reverse * 10 ) + lastDigit;
            }
            System.out.println("Reversed string" + reverse);
        }

        public static void countingOccurrence() {
            Scanner in = new Scanner(System.in);
            long num = 125164L;
            System.out.println("Enter the number of which you need to find the occurrence");
            int n = in.nextInt();
            int count = 0;
            while (num > 0) {
                long digit = num % 10;
                num = num/10;
                if (digit == n) {count++;}
            }
            System.out.printf("Count of %d is %d", n, count);
        }

        private static void nthFibonacciNum() {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the number of fibonacci you want to find");
            int n = in.nextInt();
            if (n < 2) {
                System.out.printf("the %d th fibonacci number is %d", n, n);
                return;
            }

            int previous = 0;
            int result = 1;
            int temp = 0;
            for (int i = 2; i < n; i++) {
                temp = result;
                result = result + previous;
                previous = temp;
            }

            System.out.printf("the %d th fibonacci number is %d", n, result);
        }

        private static void upperOrLower() {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter a character: ");
            char c = in.next().trim().charAt(0);
            if (c > 90) {
                System.out.println("Character is lower case");
            } else {
                System.out.println("character is upper case");
            }
        }

        private static void greatestOfThree() {
            Scanner in = new Scanner(System.in);
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();

            if (a >= b && a >= c) {
                System.out.println("a is the largest");
            } else if (b >= a && b >= c) {
                System.out.println("b is the largest");
            } else {
                System.out.println("c is the largest");
            }
        }

    }
}


