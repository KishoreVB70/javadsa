package Sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Bubble {
    public static void main(String[] args) {
        int[] arr = {4,7,2,4,6,8,9,2};
        int[] result = SimpleBubbleSortWithForLoop(arr);
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

    static int[] SimpleBubbleSortWithForLoop(int[] nums) {
        int end = nums.length - 1;
        int swap = 1;
        for (int i = 0; i < nums.length; i++) {
            if (swap == 0) {
                break;
            }
            for (int j = 0; j < end; j++) {
                swap = 0;
                if (nums[j] > nums[j+1]) {
                    // Swap numbers
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j+1] = temp;
                    swap++;
                }
            }
            end--;
        }
        return nums;
    }
}
