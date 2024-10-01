import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class StringProblems {
    public static void main(String[] args) {
        String s = "52";
        System.out.println(largestOddNumber(s));
    }

    // Striver Medium problems

    // 2) Max depth of parenthesis
    // Easy Very easy https://leetcode.com/problems/maximum-nesting-depth-of-the-parentheses/
    public int maxDepth(String s) {
        int max = 0;
        int open = 0;
        for(char i: s.toCharArray()) {
            if (i == '(') open++;
            else if(i == ')') open--;
            max = Integer.max(max, open);
        }

        return max;
    }

    // 1) Sort characters by frequency
    // Medium https://leetcode.com/problems/sort-characters-by-frequency/
    public String frequencySort(String s) {
        StringBuilder ans = new StringBuilder();
        HashMap<Character, Integer> map = new HashMap<>();

        // 1) Create a frequency list
        for(char i: s.toCharArray()) {
            if(map.containsKey(i)) {
                map.put(i, map.get(i)+1);
            } else {
                map.put(i, 1);
            }
        }

        // 2) Convert it into a heap
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));

        pq.addAll(map.keySet());

        // 3) Remove from pq and add it into the string builder
        while(!pq.isEmpty()) {
            char c = pq.poll();
            int count = map.get(c);
            while(count > 0) {
                ans.append(c);
                count--;
            }
        }

        return ans.toString();
    }
    // Striver easy problems

    // 6)


    // 5) Isomorphic string
    // Easy https://leetcode.com/problems/isomorphic-strings/
    public boolean isIsomorphic(String s, String t) {
        int[] map1 = new int[200];
        int[] map2 = new int[200];

        for(int i = 0; i < s.length(); i++) {
            if( map1[s.charAt(i)] != map2[t.charAt(i)] ) {
                return false;
            }
            // i+1 to avoid 0 being repeated
            map1[s.charAt(i)] = i+1;
            map2[t.charAt(i)] = i+1;
        }
        return true;
    }

    // 4) Longest common prefix
    public String longestCommonPrefix(String[] strs) {
        Arrays.sort(strs);
        String a = strs[0];
        String b = strs[strs.length -1];

        int i = 0;
        for(; i< a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                break;
            }
        }

        return i == 0 ? "": a.substring(0, i);
    }

    // 3) Easy Largest odd number
    public static String largestOddNumber(String num) {
        StringBuilder ans = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for(char c: num.toCharArray()) {
            int v = (int)(c - '0');
            temp.append(c);
            // If it is an odd number
            if( v % 2 != 0) {
                ans = new StringBuilder(temp);
            }
        }

        return ans.toString();
    }

    static boolean findPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
