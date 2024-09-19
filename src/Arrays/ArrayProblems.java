package Arrays;

import java.util.*;

public class ArrayProblems {
    public static void main(String[] args) {
    }


    // Striver Medium problems




    // 6) Re arrange positives and negatives
    // https://leetcode.com/problems/rearrange-array-elements-by-sign/
    public static int[] rearrangeArray(int[] nums) {
        int i = 0;
        int pos = 2;
        int neg = 1;
        int n = nums.length;

        while(i < n) {
            // Even -> must be positive
            boolean swaper = false;
            if(i % 2 == 0) {
                if(nums[i] < 0) {
                    swap(i, neg, nums);
                    swaper = true;
                    neg += 2;
                }
            }
            // Odd -> must be negative
            else {
                if(nums[i] > 0) {
                    swap(i, pos, nums);
                    swaper = true;
                    pos += 2;
                }
            }
            if(!swaper) {
                i++;
            }
        }

        return nums;
    }

    // 5) Sum of sub array minimums
    // Medium https://leetcode.com/problems/sum-of-subarray-minimums/
    public int sumSubarrayMins(int[] arr) {
        int min = Integer.MAX_VALUE;
        long sum = 0;
        for(int i = 0; i < arr.length; i++) {
            min = Integer.MAX_VALUE;
            for(int j = i; j < arr.length; j++ ) {
                min = Math.min(min, arr[j]);
                sum += min;
            }
        }
        long mod = (long)(1e9 + 7);
        return (int) ((long)sum % mod );
    }

    // 4) Maximum sub array -> Kadane's algo
    // Medium https://leetcode.com/problems/maximum-subarray/
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(sum, max);
            if(sum < 0) {
                sum = 0;
            }
        }
        return max;
    }


    // 3) Majority element in array -> Moore's voting algo
    // Easy https://leetcode.com/problems/majority-element/description/
    public int majorityElement(int[] nums) {
        int num = 0;
        int count = 0;
        for (int i : nums) {
            if (count == 0) {
                num = i;
                count++;
            } else {
                if (i == num) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return num;
    }

    // 2) Sort 0,1,2 -> Dutch flag algorithm
    // Medium https://leetcode.com/problems/sort-colors/
    public void sortColors(int[] nums) {
        int n = nums.length;
        int low = 0;
        int mid = 0;
        int high = n-1;

        while(mid <= high) {
            int v = nums[mid];
            if(v == 0) {
                swap(low, mid, nums);
                low++;
                mid++;
            }
            else if(v == 1) {
                mid++;
            }
            else {
                swap(mid, high, nums);
                high--;
            }
        }
    }
    public static void swap(int i, int j, int[] n) {
        int t = n[i];
        n[i] = n[j];
        n[j] = t;
    }
    // 1) Find the longest sub array with the given sum - Positives edition
    public static int longestSubArrayWithSumNegatives(int[] arr, int k) {
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

    // 1 B) 2 pointer approach with only positives
    public static int longestSubArrayWithSumPositives(int[] arr, int k) {
        int result = 0;
        int sum = 0;
        int members = 0;
        int i = 0;
        int j = 0;
        for(; i < arr.length; i++) {
            sum+= arr[i];
            members++;

            if(sum == k) {
                result = Math.max(result, members);
            }
            while (sum > k && members > result && j < i) {
                sum = sum - arr[j];
                members--;
                j++;
                if(sum < k) {
                    break;
                }
                if(members <= result) {
                    break;
                }
                if(sum == k) {
                    result = Math.max(result, members);
                    break;
                }
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
