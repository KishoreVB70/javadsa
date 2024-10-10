package Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlidingTwoPointer {

    public static void main(String[] args) {
        int[] arr = {1,0,1,0,1};
//        System.out.println(numSubarraysWithSum(arr, 2));
    }


    // Striver medium problems



    public static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int max = 0;
        int l = 0;
        int r = 0;
        int largest = 0;

        while(r < s.length()) {
            count[s.charAt(r) - 'A']++;
            largest = Math.max(largest, count[s.charAt(r) - 'A']);
            int len = r-l+1;
            int bad =  r-l+1 - largest;

            while(bad > k) {
                len--;
                count[s.charAt(l) - 'A']--;
//                for(int i: count) largest = Math.max(largest, i);
                bad = len - largest;
                l++;
            }

            max = Math.max(max, len);
            r++;
        }


        return max;
    }

    public static int totalFruit(int[] fruits) {
        int t1 = -1;
        int t1c = 0;
        int t1i = -1;
        int t2i = -1;
        int t2 = -1;
        int t2c = 0;
        int max = 0;
        for(int i = 0; i< fruits.length; i++) {
            // Not set already
            if( (t1 == -1 || t2 == -1) && fruits[i] != t1) {
                if(t1 == -1) {
                    t1 = fruits[i];
                    t1c = 1;
                    t1i = i;
                }
                else {
                    t2 = fruits[i];
                    t2c = 1;
                    t2i = i;
                }
            }
            // Both already set

            // Matches with either
            else if (fruits[i] == t1) t1c++;
            else if(fruits[i] == t2) t2c++;
                // Doesn't match
            else {
                if(t1i < t2i) {
                    t1i = i;
                    t1c = 1;
                    t1 = fruits[i];
                } else {
                    t2i = i;
                    t2c = 1;
                    t2 = fruits[i];
                }
            }
            max = Math.max(max, t1c+t2c);
        }

        return max;
    }

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
