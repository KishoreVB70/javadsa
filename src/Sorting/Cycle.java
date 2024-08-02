package Sorting;

public class Cycle {
    public static void main(String[] args) {
        int[] nums = {0,27,40,43,47,22,42,34,33,31,49,10,24,28,1,46,20,37,41,11,29,4,30,13,19,3,12,23,39,44,17,25,26,5,38,2,18,48,14,16,32,21,8,6,35,7,15,36,9};
        int result = findMissingNumberIn0toN(nums);
        System.out.println(result);
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
    static void helperSwap(int[] nums, int indexA, int indexB) {
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
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
