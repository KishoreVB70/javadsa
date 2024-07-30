package searching;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LinearSearch {
    public static void main(String[] args) {

        int[] arr = {3,22,13,98,6,771,2,0};
        int target = 0;


        String s = "Motorola";
        char ts = 'o';

//        int result = stringLinearSearch(s, target);
//        System.out.println(result);
        int[] result = maxAndMinNumber(arr);
        System.out.println(Arrays.toString(result));
    }

    static int[] maxAndMinNumber(int[] arr) {
        int max = 0;
        int min = Integer.MAX_VALUE;

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
