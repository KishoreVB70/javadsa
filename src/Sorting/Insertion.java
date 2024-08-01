package Sorting;

import java.util.Arrays;

public class Insertion {
    public static void main(String[] args) {
        int[] nums = {4,1,3,67,7,2,-1};
        simpleInsertionSort(nums);
        System.out.println(Arrays.toString(nums));

    }

    static void simpleInsertionSort(int[] nums) {
        int start = 0;
        for (int i = start; i <= nums.length - 2; i++) {
            for (int j = i+1; j > 0; j--) {
                if (nums[j] < nums[j-1]) {
                    helperSwap(nums, j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    static void helperSwap(int[] nums, int indexA, int indexB) {
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
    }
}
