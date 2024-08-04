package Sorting;

import java.util.Arrays;

public class Merge {
    public static void main(String[] args) {
        int[] nums = {2,6,5,4,1,3};
        System.out.println(Arrays.toString(simpleMergeSort(nums)));

    }

    static int[] simpleMergeSort(int[] nums) {

        // Base termination condition
        if (nums.length <= 1) {
            return nums;
        }


        // Call half and half with recursion
        int n = (nums.length - 1) / 2;
        int[] one =  simpleMergeSort(Arrays.copyOfRange(nums, 0, n+1));
        int[] two = simpleMergeSort(Arrays.copyOfRange(nums, n+1, nums.length));
        int[] merged = new int[one.length + two.length];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < one.length && j < two.length  ) {
            if (one[i] < two[j]) {
                merged[k] = one[i];
                i++;
            } else {
                merged[k] = two[j];
                j++;
            }
            k++;
        }

        // One array is finished sorted, push the remaining array into the merged
        if (i == one.length) {
            while (j < two.length) {
                merged[k] = two[j];
                j++;
                k++;
            }
        } else if (j == two.length) {
            while (i < one.length) {
                merged[k] = one[i];
                i++;
                k++;
            }
        }

        return merged;

    }
}
