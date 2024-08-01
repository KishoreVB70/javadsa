package Sorting;

import java.util.Arrays;

public class Selection {
    public static void main(String[] args) {
        int[] nums = {6,5,7,21,1,7, -1};
        int[] result = simpleSelectionSort(nums);
        System.out.println(Arrays.toString(result));

    }

    static int[] simpleSelectionSort(int[] nums) {
        int start = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int smallIndex =start;

            // 1) find the smallest
            for (int j = start; j < nums.length; j ++) {
                if (nums[j] < nums[smallIndex]) {
                    smallIndex = j;
                }
            }

            // 2) Swap it to the appropriate position
            int temp = nums[i];
            nums[i] = nums[smallIndex];
            nums[smallIndex] = temp;

            //3) If swapped, then it would be in the first index
            start++;
        }
        return nums;
    }
}
