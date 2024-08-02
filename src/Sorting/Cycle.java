package Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cycle {
    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};

//        System.out.println(findAllTheMissingNumbersIn1toNWithDuplicates(nums));
//        System.out.println(Arrays.toString(findAllTheMissingNumbersIn1toNWithDuplicates(nums)));

        helperCycleSortForMultipleMissingDuplicateNumbers1ToN(nums);
        System.out.println(findOneDuplicateNumberIn1ToN(nums));
    }

    static int findOneDuplicateNumberIn1ToN(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            // If it is correctly sorted, move ahead
            int currentNumber = nums[i];
            if (currentNumber == i+1) {
                i++;
            }
            else {
                // Check if it is a duplicate number
                if (nums[currentNumber-1] == currentNumber ) {
                    return currentNumber;
                }
                else{
                    helperSwap(nums, i, (currentNumber-1));
                }
            }
        }
        return 0;
    }
    static void helperSwap(int[] nums, int indexA, int indexB) {
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
    }
    static void helperCycleSortForMultipleMissingDuplicateNumbers1ToN(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            // If it is correctly sorted, move ahead
            int currentNumber = nums[i];
            if (currentNumber == i+1) {
                i++;
            }
            else {
                // Check if it is a duplicate number or the final element
                if (nums[currentNumber-1] == currentNumber ) {
                    i++;
                }
                else{
                    helperSwap(nums, i, (currentNumber-1));
                }
            }
        }
    }



    static List<Integer> findAllTheMissingNumbersIn1toNWithDuplicates(int[] nums) {
        // 1 -> sort
        helperCycleSortForMultipleMissingDuplicateNumbers1ToN(nums);

        // 2 -> add duplicates to list
        List<Integer> list = new ArrayList<>(1);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1) {
                list.add(i+1);
            }
        }
        return list;
    }
    static int findMissingNumberIn0toN(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
            }
            else {
                if (nums[i] == nums.length) {
                    i++;
                }
                else{
                    helperSwap(nums, i, nums[i]);
                }
            }
        }

        for(int j = 0; j < nums.length; j++) {
            if (nums[j] != j) {
                return j;
            }
        }
        return nums.length;
    }
    static int findMissingNumberIn0toNnotOptimized(int[] nums) {
        int i = 0;
        int swaps = 0;
        while (i < nums.length) {
            // Checking for number of swaps
            if (swaps > nums.length - i) {
                return i;
            }

            // Check if the number is correctly placed
            if (nums[i] == i) {
                i++;
                swaps = 0;
            }
            // If not correctly placed
            else {
                // If it is meant to be the last index -> some checks
                if (nums[i] == nums.length) {
                    // Optimal index would be it
                    boolean terminated = true;
                    int optimalIndex = nums.length -1;
                    while (optimalIndex > i) {
                        // If optimal index is not right
                        if (nums[optimalIndex] != optimalIndex) {
                            helperSwap(nums, i, optimalIndex);
                            terminated = false;
                            break;
                        }
                        // else If optimal index is rightly placed
                        else {
                            optimalIndex--;
                        }
                    }

                    // Once the loop breaks
                    if (terminated) {
                        return i;
                    }
                }
                // If it is not the last index -> swap
                else {
                    helperSwap(nums, i, nums[i]);
                    swaps++;
                }
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

}
