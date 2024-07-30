package searching;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LinearSearch {
    public static void main(String[] args) {

        int[] arr = {12, 345, 1234, 12345, 12};
        int[][] twoDArray = {{1,2,3}, {4,5,6}, {7,8,9}};
        int target = 10;


        String s = "Motorola";
        char ts = 'o';

//         String

//        int result = stringLinearSearch(s, target);
//        System.out.println(result);

//          Array
//        int[] result = maxAndMinNumber(arr);
//        System.out.println(Arrays.toString(result));

//        boolean result = twoDArraySearch(twoDArray, target);

        int result = evenNumOfDigits(arr);
        System.out.println(result);

    }

    static int evenNumOfDigits(int[] arr) {
        int result = 0;

        for(int i: arr) {

            int digit = (int) Math.log10(i) + 1;

            if(digit % 2 == 0) {
                result++;
            }
        }

        return  result;
    }

    static boolean twoDArraySearch(int[][] arr, int target) {
        for(int[] i: arr) {
            for (int j: i) {
                if (j == target) {
                    return true;
                }
            }
        }
        return false;
    }

    static int[] maxAndMinNumber(int[] arr) {
        int max = 0;
        int min = arr[0];

        for (int i: arr) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }
        return new int[]{max, min};
    }

    static int stringLinearSearch(String s, char target) {
        if (s.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == target) {
                return i;
            }
        }

        return -1;
    }

    static boolean simpleLinearSearchIntBoolean(int[] arr, int target) {
        for (int i: arr) {
            if (i == target) {
                return true;
            }
        }
        return false;
    }

    static int simpleLinearSearchIntIndex(int[] arr, int target) {
        if (arr.length == 0) {
            return -1;
        }

        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }
}
