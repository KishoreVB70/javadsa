import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) {

    }

    static class BasicProblems {
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

        static void isPrime() {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();

            for(int i = 2; i <= sqrt(n); i++) {
                if(n % i == 0) {
                    System.out.println("It is not logan paul prime");
                    return;
                }
            }
            System.out.println("It is prime indeed brotha");
        }

        static void printAllThreeDigitArmstrong() {
            int i = 100;
            while (i < 1000) {
                if (isArmStrong(i)) {
                    System.out.println(i);
                }
                i++;
            }
        }

        static boolean isArmStrong(int n) {
            int r = n;

            int sum = 0;

            while (r > 0) {
                int l = r % 10;
                sum += l * l * l;
                r = r/10;
            }

            return sum == n;
        }


    }
    static class BasicLearnings{
        public static void switchFunc() {
            Scanner in = new Scanner(System.in);
            String s = in.next();

            switch (s) {
                case "apple":
                    System.out.println("It is an apple");
                    break;
                case "mango":
                    System.out.println("It is a mango");
                    break;
                case "palapalam":
                    System.out.println("It is a palapalam");
                    break;
                default:
                    System.out.println("please enter a fruit that I know");
            }

            int day = in.nextInt();
            switch (day) {
                case 1,2,3,4,5 -> System.out.println("Weekday");
                case  6,7 -> System.out.println("Weekend");
                default -> System.out.println("Enter a valid day");
            }

        }
        static class ArrayLearnings {
            public static void arrayLearn() {
                Scanner in = new Scanner(System.in);
                int[] arr = new int[5];
                for (int i = 0; i < arr.length; i++) {
                    arr[i] = in.nextInt();
                }

                System.out.println(Arrays.toString(arr));

//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i] +  " ");
//        }

                for (int i: arr) {
                    System.out.print(i + " ");
                }
            }

            public static void array2d() {
                Scanner in = new Scanner(System.in);
                int[][] arr = new int[5][3];
                for (int i = 0; i < arr.length; i++) {
                    for (int j = 0; j < arr[i].length; j++) {
                        arr[i][j] = in.nextInt();
                    }
                }

                System.out.println(Arrays.toString(arr));

//        for (int i = 0; i < arr.length; i++) {
//            for(int j = 0; j < arr[i].length; j++) {
//                System.out.print(arr[i][j] +  " ");
//            }
//            System.out.println(" ");
//        }

                for (int[] i: arr) {
                    for(int j: i) {
                        System.out.print(j + " ");
                    }
                    System.out.println(" ");
                }

//        for(int i = 0; i < arr.length; i++) {
//            System.out.println(Arrays.toString(arr[i]));
//        }

                for(int[] i: arr) {
                    System.out.println(Arrays.toString(i));
                }


            }

            public static void arrayList2d() {
                Scanner in = new Scanner(System.in);
                ArrayList<ArrayList<Integer>> arr = new ArrayList<>(2);

                for (int i = 0; i < 3; i ++) {
                    ArrayList<Integer> temp = new ArrayList<>(3);
                    for (int j = 0; j < 2; j++) {
                        temp.add(in.nextInt());
                    }
                    arr.add(temp);
                }

                for(ArrayList<Integer> i: arr) {
                    for(int j: i ) {
                        System.out.print(j + " ");
                    }
                    System.out.println(" ");
                }

//            for (int i = 0; i < arr.size(); i++) {
//                ArrayList<Integer> temp = new ArrayList<>(3);
//                temp = arr.get(i);
//                for(int j = 0; j < temp.size(); j++) {
//                    System.out.print(temp.get(j) + " ");
//                }
//                System.out.println(" ");
//            }
            }

            public static void arrayListLearn() {
                Scanner in = new Scanner(System.in);

                ArrayList<Integer> arr = new ArrayList<Integer>(5);

                for(int i = 0; i < 6; i++) {
                    arr.add(in.nextInt());
                }

                for(int i: arr) {
                    System.out.print(i+ " ");
                }

            }


        }
    }
}

