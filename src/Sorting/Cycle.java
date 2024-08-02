package Sorting;

import java.util.Arrays;

public class Cycle {
    public static void main(String[] args) {
        int[] nums = {6,2,4,5,3,1,};
        simpleCycleSort(nums);
        System.out.println(Arrays.toString(nums));
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
