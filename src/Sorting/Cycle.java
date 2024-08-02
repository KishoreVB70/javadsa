package Sorting;

import java.util.Arrays;

public class Cycle {
    public static void main(String[] args) {
        int[] nums = {1,2,0,4,3};
        int missing = findMissingNumberIn0toN(nums);
        System.out.println(missing);
    }

    static int findMissingNumberIn0toN(int[] nums) {
        int i = 0;
        int swaps = 0;
        while (i < nums.length) {
        if (swaps > nums.length - i) {
            return i;
        }
            if (nums[i] == i) {
                i++;
                swaps = 0;
            } else {
                // If the supposedly last element is in the index
                if (nums[i] == nums.length) {
                    helperSwap(nums, i, ( nums[i] - 1));
                }
                helperSwap(nums, i, nums[i]);
                swaps++;
            }
        }
        return  i;
    }

    static void simpleCycleSort(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i + 1) {
                i++;
            } else {
                helperSwap(nums, i, (nums[i] - 1));
            }
        }
    }

    static void helperSwap(int[] nums, int indexA, int indexB) {
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
    }
}
