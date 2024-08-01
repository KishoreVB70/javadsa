package Sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Bubble {
    public static void main(String[] args) {
        int[] arr = {4,7,2,4,6,8,9,2};
        int[] result = simpleBubbleSort(arr);
        System.out.println(Arrays.toString(result));

    }

    static int[] simpleBubbleSort(int[] arr) {
        int turn = 0;
        int swap = 1;
        int end = arr.length - 1;
        while (turn <= arr.length && swap > 0) {
            swap = 0;
            for (int i = 0; i < end; i ++) {
                if (arr[i] > arr[i+1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i+1] = temp;
                    swap++;
                }
            }
            end--;
            turn++;
        }

        return arr;

    }
}
