package Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlidingTwoPointer {

    public static void main(String[] args) {
        int[] arr = {5, 5, 5, 5};
//        System.out.println(largestTimeFromDigits(arr));
    }


    // Striver medium problems

    // Maximum consecutive ones III
    public static int longestOnes(int[] nums, int k) {
        int zero = 0;
        int max = 0;
        int l = 0;
        for(int i =0; i< nums.length; i++) {
            if(nums[i] == 0) zero++;
            if(zero > k && nums[l++] == 0) zero--;
            if(zero <= k) max = Math.max(max, (i +1) - l);
        }
        return max;

    }

    // Removing duplicates II
    // Medium https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        return i;
    }


    // 3rd leet problem - Longest sub string with non repeating characters
    // Medium https://leetcode.com/problems/longest-substring-without-repeating-characters
    public int lengthOfLongestSubstring(String s) {
        if(s.isEmpty()) return 0;
        int max = 0, l= 0, r = 0;
        int[] map = new int[128];

        while(r < s.length()) {
            char c = s.charAt(r);
            l = Math.max(l, map[c]);
            map[c] = r+1;
            max = Math.max(max, r-l+1);
            r++;
        }
        return max;
    }
}
