package Sorting;

import java.util.Arrays;

public class Merge {
    public static void main(String[] args) {
        int[] nums = {2,5,4,1,3};
        inPlaceMergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    static void inPlaceMergeSort(int[] nums) {
        helperInPlaceMergeSort(nums, 0, nums.length - 1);
    }

    static void helperInPlaceMergeSort(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }

        int m = (start + end ) / 2;

        // Get result of two arrays
        helperInPlaceMergeSort(nums, start, m);
        helperInPlaceMergeSort(nums, m+1, end);

        // Sort the arrays
        int[] tempArray = new int[(end - start) + 1];

        int i = start;
        int j = m+1;
        int k = 0;

        while (i <= m && j <= end) {
            if (nums[i] < nums[j]) {
                tempArray[k] = nums[i];
                i++;
            } else {
                tempArray[k] = nums[j];
                j++;
            }
            k++;
        }

        while (i <= m) {
            tempArray[k] = nums[i];
            k++;
            i++;
        }

        while (j <= end) {
            tempArray[k] = nums[j];
            k++;
            j++;
        }
        k = 0;

        // Copy over the array
        while (k < tempArray.length ) {
            nums[start] = tempArray[k];
            start++;
            k++;
        }
    }

    static void helperSwap(int[] nums, int indexA, int indexB) {
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
    }
    static int[] simpleMergeSort(int[] nums) {

        // Base termination condition
        if (nums.length <= 1) {
            return nums;
        }


        // Call half and half with recursion
        int n = (nums.length) / 2;
        int[] one =  simpleMergeSort(Arrays.copyOfRange(nums, 0, n));
        int[] two = simpleMergeSort(Arrays.copyOfRange(nums, n, nums.length));
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
        while (j < two.length) {
            merged[k] = two[j];
            j++;
            k++;
        }
        while (i < one.length) {
            merged[k] = one[i];
            i++;
            k++;
        }

        return merged;

    }
}
