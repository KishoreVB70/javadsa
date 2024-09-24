package Arrays;

import java.util.*;

public class ArrayProblems {
    public static void main(String[] args) {
        List<List<Integer>> res = fourSum(new int[]{1000000000,1000000000,1000000000,1000000000}, -294967296);
        System.out.println(res.toString());
    }




    // Striver Medium problems

    // Sub array with given XOR -> not leet
    public int XORSubarray(int[] nums, int tar) {
        int xor = 0;
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            xor = xor ^ nums[i];
            if(xor == tar) {
                count++;
            } else if(map.containsKey(xor ^ tar)) {
                count += map.get(xor ^ tar);
            }
            if(map.containsKey(xor)) {
                map.put(xor, map.get(xor) +1);
            } else {
                map.put(xor, 1);
            }
        }
        return count;
    }

    // Largest sub array with 0 sum
    // Geek problem
    public int largestSubArrayZeroSum(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int longest = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum)) {
                longest = Integer.max(longest, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return longest;
    }

    // 4Sum
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int i = 0; i< nums.length; i++) {
            if(i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for(int j = i+1; j < nums.length; j++) {
                if(j > i+1 && nums[j] == nums[j-1]) {
                    continue;
                }
                int k = j+1;
                int l = nums.length -1;
                while(k < l) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if(sum == target) {
                        List<Integer> lt = new ArrayList<>();
                        lt.add(nums[i]);
                        lt.add(nums[j]);
                        lt.add(nums[k]);
                        lt.add(nums[l]);
                        result.add(lt);
                        k++;
                        l--;
                        while(k < l && nums[k] == nums[k-1]) {
                            k++;
                        }
                        while(k < l && nums[l] == nums[l+1]) {
                            l--;
                        }
                    }

                    if(sum > target) {
                        l--;
                        while(k < l && nums[l] == nums[l+1]) {
                            l--;
                        }
                    }

                    if(sum < target) {
                        k++;
                        while(k < l && nums[k] == nums[k-1]) {
                            k++;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static List<Integer> majorityElement2(int[] nums) {
        List<Integer> s = new ArrayList<>();
        int count1 = 0;
        int count2 = 0;

        int e1 = Integer.MAX_VALUE;
        int e2 = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++) {
            if(count1 == 0 && nums[i] != e2) {
                count1 = 1;
                e1 = nums[i];
            }
            else if(count2 == 0 && nums[i] != e1) {
                e2 = nums[i];
                count2 = 1;
            }
            else if(nums[i] == e1) {
                count1++;
            }
            else if(nums[i] == e2) {
                count2++;
            }
            else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;
        for(int i: nums) {
            if(i == e1) {
                count1++;
            }
            if(i == e2) {
                count2++;
            }
        }

        if(count1 > nums.length/3) {
            s.add(e1);
        }
        if(count2 > nums.length/3) {
            s.add(e2);
        }
        Collections.sort(s);
        return s;
    }

    // 15) Pascal's triangle -> print a row
    public static int[] pascalRow(int n) {
        int[] result = new int[n];
        int prev = 1;
        result[0] = 1;
        for(int i = 1; i < n; i++) {
            int s = prev * (n-i);
            prev = s / i;
            result[i] = prev;
        }
        return result;
    }

    // 15 B) Print everything
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> end = new ArrayList<>();
        for(int n = 1; n <= numRows; n++) {
            List<Integer> result = new ArrayList<>(n);
            int prev = 1;
            result.add(1);
            for(int i = 1; i < n; i++) {
                int s = prev * (n-i);
                prev = s / i;
                result.add(prev);
            }
            end.add(result);
        }
        return end;
    }

    // 15 C) Print one value in pascal triangle
    public static int printOnePascal(int r, int c) {
        int s = 1;
        for (int i = 1; i < c; i++) {
            s = s * ((r)-i);
            s = s/i;
        }
        return s;
    }

    // 14) Product except self
    // Medium https://leetcode.com/problems/product-of-array-except-self
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int ans[] = new int[n];
        Arrays.fill(ans, 1);
        int curr = 1;
        for(int i = 0; i < n; i++) {
            ans[i] *= curr;
            curr *= nums[i];
        }
        curr = 1;
        for(int i = n - 1; i >= 0; i--) {
            ans[i] *= curr;
            curr *= nums[i];
        }
        return ans;
    }
    // 13) H Index
    // Medium https://leetcode.com/problems/h-index/
    public int hIndex(int[] citations) {
        int h = citations.length; int maxi=0;
        Arrays.sort(citations);
        for (int i=0; i<citations.length; i++){
            if (citations[i]>= h-i){
                maxi = Math.max(maxi,h-i);

            }
        } return maxi;
    }
    //12) Can jump
    // Medium https://leetcode.com/problems/jump-game/description/
    public boolean canJump(int[] nums) {
        int reachable = 0;
        for(int i = 0; i < nums.length; i ++) {
            if(i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }
    //11) Number of sub arrays that equal the sum
    // Medium https://leetcode.com/problems/subarray-sum-equals-k/
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int total = 0;
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(sum == k) {
                total++;
            }

            if(map.containsKey(sum - k)) {
                total += map.get(sum-k);
            }

            if(map.containsKey(sum)) {
                int val = map.get(sum);
                map.put(sum, val+1);
            }
            else {
                map.put(sum, 1);
            }


        }
        return total;
    }

    // 10) Printing matrix in spiral order
    // Medium https://leetcode.com/problems/spiral-matrix/description/
    public List<Integer> spiralOrder(int[][] matrix) {
        int iHigh = matrix.length-1;
        int jHigh = matrix[0].length-1;
        int iLow = 0;
        int jLow = 0;
        List<Integer> result = new ArrayList<>();

        while(iLow <= iHigh && jLow <= jHigh) {
            for(int i = jLow; i <= jHigh; i++) {
                result.add(matrix[iLow][i]);
            }
            iLow++;

            for(int i = iLow; i <= iHigh; i++) {
                result.add(matrix[i][jHigh]);
            }
            jHigh--;

            if(iLow <= iHigh) {
                for(int i = jHigh; i >= jLow; i--) {
                    result.add(matrix[iHigh][i]);
                }
                iHigh--;
            }

            if(jLow <= jHigh) {
                for(int i = iHigh; i >= iLow; i--) {
                    result.add(matrix[i][jLow]);
                }
                jLow++;
            }

        }
        return result;
    }

    // 9) Rotate matrix
    // Medium https://leetcode.com/problems/rotate-image/
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 1) Transpose
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] =  matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 2) Reverse
        for(int i = 0; i < n; i++) {
            int s = 0;
            int e = n-1;
            while(s < e) {
                int temp = matrix[i][s];
                matrix[i][s] = matrix[i][e];
                matrix[i][e] = temp;
                s++;
                e--;
            }
        }
    }

    // 8) Set Matrix 0
    // Medium https://leetcode.com/problems/set-matrix-zeroes/
    public void setZeroes(int[][] matrix) {
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0 ; j < matrix[0].length; j++) {
                if(matrix[i][j] == 0) {
                    ArrayList<Integer> lt = new ArrayList<>();
                    lt.add(i);
                    lt.add(j);
                    arr.add(lt);
                }
            }
        }

        for(ArrayList<Integer> lt: arr) {
            int r = lt.get(0);
            int c = lt.get(1);

            // Change all the row and column
            for(int i = 0; i < matrix.length; i++) {
                matrix[i][c] = 0;
            }
            for(int i = 0; i < matrix[0].length; i++) {
                matrix[r][i] = 0;
            }


        }
    }

    // 7) Next permutation
    // Medium https://leetcode.com/problems/next-permutation/
    public void nextPermutation(int[] nums) {
        int i = nums.length -1;
        int target = -1;
        while(i > 0) {
            if(nums[i -1] < nums[i]) {
                target = i-1;
                break;
            }
            i--;
        }

        if(target == -1) {
            reverse(0, nums.length-1, nums);
            return;
        }

        int lowestV = Integer.MAX_VALUE;
        int lowest = -1;

        for(int j = target; j < nums.length; j++) {
            if(nums[j] > nums[target] && nums[j] <= lowestV) {
                lowest = j;
                lowestV = nums[j];
            }
        }


        int t = nums[target];
        nums[target] = nums[lowest];
        nums[lowest] = t;
        reverse(target+1, nums.length-1, nums);


    }
    public void reverse(int s, int e, int[] nums) {
        while (s < e) {
            int t = nums[s];
            nums[s] = nums[e];
            nums[e] = t;
            s++;
            e--;
        }
    }

    // 6) Re arrange positives and negatives
    // Medium https://leetcode.com/problems/rearrange-array-elements-by-sign/
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
