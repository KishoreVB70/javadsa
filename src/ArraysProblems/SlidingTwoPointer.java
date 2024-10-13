package ArraysProblems;

import java.util.HashMap;
import java.util.Map;

public class SlidingTwoPointer {

    public static void main(String[] args) {
        int[] arr = {1,2,1,2,3};
//        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sGas = 0, sCost = 0, res = 0, total = 0;
        for (int i = 0; i < gas.length; i++) {
            sGas += gas[i];
            sCost += cost[i];
        }
        if (sGas < sCost) return -1;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            if (total < 0) {
                total = 0;
                res = i + 1;
            }
        }
        return res;
    }

    public int maxProfit(int[] prices) {
        int max = 0;
        int start = prices[0];
        int len = prices.length;
        for(int i = 1;i<len; i++){
            if(start < prices[i]) max += prices[i] - start;
            start = prices[i];
        }
        return max;
    }


    // Striver hard problems

    // 3)
    public String minWindow(String s, String t) {
        int[] pres = new int[58];
        int[] source = new int[58];

        int l = 0;
        int r = 0;
        int total = 0;
        int smallest = Integer.MAX_VALUE;
        String smallestS = "";

        for(int i =0 ; i< t.length(); i++) source[t.charAt(i) - 'A']++;

        while(r < s.length()) {
            int ind = s.charAt(r) - 'A';

            // If it is present in the source and is lesser in the pres
            if(source[ind] > 0 && pres[ind] < source[ind]) total++;
            pres[ind]++;

            // Check if it has reached the end goal

            if(total == t.length()) {
                while (l <= r) {
                    ind = s.charAt(l) - 'A';
                    if(source[ind] != 0 && pres[ind] == source[ind])break;
                    --pres[s.charAt(l) - 'A'];
                    l++;
                }
                if (r - l + 1 < smallest) {
                    smallest = r - l + 1;
                    smallestS = s.substring(l, r + 1);
                }
            }
            r++;
        }
        return smallestS;
    }


    // 2)

    public static int subarraysWithKDistinct(int[] nums, int k) {
        int two = helperSubArrKDist(nums, k-1);
        int one = helperSubArrKDist(nums, k);
        return one -two;
    }

    public static int helperSubArrKDist(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int r = 0;
        int l = 0;
        int total = 0;
        while(r < nums.length) {
            map.put(nums[r], map.getOrDefault(nums[r], 0)+1);

            while(map.size() > k && l <= r) {
                map.put(nums[l], map.get(nums[l])-1);
                if(map.get(nums[l]) == 0) map.remove(nums[l]);
                l++;
            }
            total += r-l+1;
            r++;
        }
        return total;
    }

    // 1) Leet premium -> longest sub string with k distinct characters
    // Medium premium https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
    public static int longestSubstringKDistinct(String s, int k) {
        int max = 0;
        int l = 0;
        int r = 0;
        Map<Character, Integer> map = new HashMap<>();
        while(r < s.length()) {
            char c = s.charAt(r);
            map.put(c, map.getOrDefault(c,0)+1);
            if(map.size() > k) {
                c = s.charAt(l);
                map.put(c, map.get(c)-1);
                if(map.get(c) == 0) map.remove(c);
                l++;
            }

            if(map.size() == k) max = Math.max(max, r-l+1);
            r++;
        }
        return max;
    }

    // Striver medium problems

    // 8) Number of sub strings with 3 characters
    // Medium https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] sums = new int[3];
        int l = 0;
        int r = 0;
        long total = 0;

        while(r < s.length()) {
            sums[s.charAt(r) - 'a']++;

            // Find if all 3 are above
            boolean isZero = false;
            for(int i: sums) {
                if(i == 0) isZero = true;
            }

            while(!isZero) {
                sums[s.charAt(l) -'a']--;
                l++;
                for(int i: sums) {
                    if(i == 0) isZero = true;
                }
            }

            total += r-l+1;
            r++;
        }

        long totalSub = (long)n * (n+1) /2;
        return (int)(totalSub - total);
    }

    // 7) Number of sub arrays with k odd numbers
    // Medium https://leetcode.com/problems/count-number-of-nice-subarrays/
    public int numberOfSubarrays(int[] nums, int k) {
        return helperNumberOfSubarrays(nums, k) - helperNumberOfSubarrays(nums, k-1);
    }
    public int helperNumberOfSubarrays(int[] nums, int k) {
        if(k < 0) return 0;
        int l = 0;
        int r = 0;
        int total = 0;
        int sum = 0;

        while(r < nums.length) {
            if( (nums[r] & 1) == 1) sum++;
            while(sum > k) {
                if( (nums[l] & 1) == 1) sum--;
                l++;
            }
            total += r-l+1;
            r++;
        }
        return total;
    }

    // 6) Number of sub arrays with goal sum
    // Medium https://leetcode.com/problems/binary-subarrays-with-sum/
    public static int numSubarraysWithSum(int[] nums, int goal) {
        int one = helperNumSubarraysWithSum(nums, goal-1);
        int two = helperNumSubarraysWithSum(nums, goal);
        return two - one;
    }
    public static int helperNumSubarraysWithSum(int[] nums, int goal) {
        if(goal < 0) return 0;
        int sum = 0;
        int l = 0;
        int r = 0;
        int total = 0;

        for (; r < nums.length; r++) {
            sum += nums[r];

            while (sum > goal) {
                sum -= nums[l];
                l++;
            }

            total += r-l+1;
        }
        return total;
    }


    // 5) Replace k characters
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

    // 4) Total number of fruits in 2 baskets
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

    // 3) Maximum consecutive ones III
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

    // 2) Removing duplicates II
    // Medium https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums)
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        return i;
    }


    // 1) 3rd leet problem - Longest sub string with non repeating characters
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
