package Arrays;

public class SlidingTwoPointer {
    // Striver medium problems

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
