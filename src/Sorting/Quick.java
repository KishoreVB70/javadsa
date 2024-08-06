package Sorting;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Quick {
    public static void main(String[] args) {
        int[] nums  = {5,1,2,3,4};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    static void quickSort(int[] nums) {
        helperQuickSort(nums, 0, nums.length - 1);
    }
    static void helperQuickSort(int[] nums, int low, int high) {
        // Base condition
        if (low >= high) {
            return;
        }

        // Sorting the pivot
        int start = low;
        int end = high;
        int pivot = nums[low + (end - low)/2];

        while (start <= end) {
            while (nums[start] < pivot) {
                ++start;
            }

            while (nums[end] > pivot) {
                --end;
            }

            if (start >= end) {
                ++start;
                --end;
            } else {
                helperSwap(nums, start, end);
            }

        }

        // Quick sort for the right side
        helperQuickSort(nums, low, end);
        helperQuickSort(nums, start, high);
    }
    static void helperQuickSortKunalVersion(int[] nums, int low, int high) {
        // Base condition
        if (low >= high) {
            return;
        }

        // Sorting the pivot
        int start = low;
        int end = high;
        int pivot = nums[low + (end - low)/2];

        while (start <= end) {
            while (nums[start] < pivot) {
                ++start;
            }

            while (nums[end] > pivot) {
                --end;
            }

            if (start <= end) {
                helperSwap(nums, start, end);
                ++start;
                --end;
            }
        }

        // Quick sort for the right side
        helperQuickSort(nums, low, end);
        helperQuickSort(nums, start, high);

    }
    static void helperSwap(int[] nums, int indexA, int indexB) {
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
    }
}
