package Arrays;

import java.util.*;

public class ArrayProblems {
    public static void main(String[] args) {
        int[] arr = {1,2,3, 1, 1, 1, 1, 4, 2,3};
        System.out.println(longestSubArrayWithSum(arr,3));
    }


    // Striver problems
    // 1) Find the longest sub array with the given sum - Positives edition
    public static int longestSubArrayWithSum(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int result = 0;
        int sum = 0;
        int members = 0;
        for(int i = 0; i < arr.length; i++) {
            sum+= arr[i];
            members++;
            if(!map.containsKey(sum)) {
                map.put(sum, i);
            }
            if(sum == k) {
                result = Math.max(result, members);
            }
            if(map.containsKey(sum-k)) {
                result = Math.max(result, Math.abs( map.get(sum-k) - i));
            }
        }
        return result;
    }









    // ----------------------------------------------

    // Rotate array
    //https://leetcode.com/problems/rotate-array/
    public static void rotate(int[] nums, int k) {
        int lt = nums.length;
        int rem;
        if (k > lt) {
            rem = k % lt;
        } else {
            rem = k;
        }
        int startIndex = lt - rem;
        if (startIndex == 0) {
            return;
        }

        int j = 0;
        int l = startIndex-1;
        for (int i = startIndex; i < lt; i++) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = nums[l];
            nums[l] = temp;
            j++;
            l--;
        }
    }
    // Square root decomposition ->  Advanced algorithm for Range problems



    // 2 elements in a sorted array are swapped,find and swap them back
    static void swapArray(ArrayList<Integer> arr) {
        ArrayList<Integer> swappers = new ArrayList<>();
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) > arr.get(i+1)) {
                swappers.add(i);
                swappers.add(i + 1);
            }
        }

        if (swappers.size() == 2) {
            int oneId = swappers.get(0);
            int twoId = swappers.get(1);

            int one =  arr.get(oneId);
            int two =  arr.get(twoId);

            // Swapping
            arr.set(oneId, two);
            arr.set(twoId, one);
        } else {
            int oneId = swappers.get(0);
            int fourId = swappers.get(3);

            int one =  arr.get(oneId);
            int four =  arr.get(fourId);

            arr.set(oneId, four);
            arr.set(fourId, one);
        }
    }



    // Q -> find the sum of range in an array
    static int sumOfRangeSqrt(int[] arr, int s, int e) {
        // 1 -> Create the block array
        int sqrt = (int)Math.sqrt(arr.length);

        int[] blocks = new int[sqrt + 1];
        int blockIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (i % sqrt == 0) {
                blockIndex++;
            }
            blocks[blockIndex] += arr[i];
        }

        for (int i = 0; i < blocks.length; i++) {
            System.out.println(blocks[i]);
        }



        return 1;
    }

    // 3 Sum
    //https://leetcode.com/problems/3sum/description/
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // 1 -> sort the array
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Base condition
            if (nums[i] > 0) {
                break;
            }

            // Duplicate condition
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int j = i+1;
            int k = nums.length -1;
            // Two sum
            while (j < k) {
                // Checking sum
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> lt = new ArrayList<>(3);
                    lt.add(nums[i]);
                    lt.add(nums[j]);
                    lt.add(nums[k]);
                    result.add(lt);

                    j++;
                    k--;

                    // Continue over duplicates -> J
                    while (j < k && nums[j] == nums[j-1]) {
                        j++;
                    }

                    // Continue over duplicates -> K
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
                else if (sum < 0) {
                    j++;
                }
                else{
                    k--;
                }
            }
        }
        return result;
    }

    // 2 sum -> Leet code problem 1
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            if (map.containsKey(sub)){
                result[0] = i;
                result[1] = map.get(sub);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }
}
