import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class StringProblems {
    public static void main(String[] args) {
        String s = "aabcb";
        System.out.println(beautySum(s));
    }

    // Striver Medium problems

    public static int beautySum(String s) {
        int total = 0;
        for(int i = 0; i < s.length() -1; i++) {
            int[] map = new int[26];
            map[(int)s.charAt(i) - 'a'] = 1;
            int largest = 1;

            for(int j = i+1; j < s.length(); j++) {
                int index = (int)s.charAt(j) - 'a';
                map[index]++;
                largest = Integer.max(largest, map[index]);

                if(j - i > 1) {
                    int smallest = findSmallest(map);
                    total += largest - smallest;
                }
            }
        }

        return total;
    }

    public static int findSmallest(int[] map) {
        int smallest = 500;
        for(int i: map) {
            if(i > 0) smallest = Integer.min(smallest, i);
        }
        return smallest;
    }

    public static String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }

        int maxLen = 1;
        int start = 0;
        int end = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j - 1])) {
                    dp[i][j] = true;
                    if (j - i + 1 > maxLen) {
                        maxLen = j - i + 1;
                        start = i;
                        end = j;
                    }
                }
            }
        }

        return s.substring(start, end + 1);
    }

    public static int myAtoi(String s) {
        if(s.isEmpty()) return 0;

        boolean positive = true;
        int i = 0;

        // 1) Removing white space
        while(s.charAt(i) == ' ') i++;

        // 2) Looking for the sign
        if(s.charAt(i) == '-' || s.charAt(i) == '+') {
            if(s.charAt(i) == '-') positive = false;
            i++;
        }

        // 3) Removing zero
        while(s.charAt(i) == '0') i++;

        int start = i;

        // 4) Counting the numbers
        while( i < s.length() && (s.charAt(i) >= '0' && s.charAt(i) <= '9') ) {
            i++;
        }
        
        // 5) Only digit string
        if(start == i) return 0;
        else s = s.substring(start, i);

        // 6) Check if the limit is over the long value
        if(s.length() >= 11) {
            if (positive) return Integer.MAX_VALUE;
            return Integer.MIN_VALUE;
        }

        // 7) Limit is inside the long value
        long num = Long.parseLong(s);

        // 8) Rounding
        if(num >= Integer.MAX_VALUE) {
            num = Integer.MAX_VALUE;
        }
        // 9) Negative
        if(!positive) num *= -1;

        return (int)num;
    }

    // 3) Roman to Integer, Integer to Roman
    // Roman to Integer
    // easy https://leetcode.com/problems/roman-to-integer/
    public int romanToInt(String s) {
        int sum = 0;
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        for(int i = 0; i < s.length() - 1; i++) {
            if(map.get(s.charAt(i)) < map.get(s.charAt(i+1)) ) {
                sum -= map.get(s.charAt(i));
            }
            else sum += map.get(s.charAt(i));
        }
        return sum+ map.get(s.charAt(s.length()-1));
    }

    // It to roman
    public String intToRoman1(int num) {
        StringBuilder result = new StringBuilder();
        int[] it = {1000,900,500,400,100,90,50,40,10,9, 5, 4,1};
        String[] st = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

        for(int i = 0; i < it.length; i++) {
            while(num >= it[i]) {
                result.append(st[i]);
                num -= it[i];
            }
        }
        return result.toString();
    }
    // In constant time
    public static String intToRoman2(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }

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

    // 2) Reverse all individual words in a string
    // Medium https://leetcode.com/problems/reverse-words-in-a-string/
    public String reverseWords(String s) {
        char[] c = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        int st = -1;
        for(int i = c.length -1; i >= 0; i--) {
            // Not an empty character
            if(c[i] != ' ') {
                if(st == -1) {
                    st = i;
                }
            }
            // Empty character
            else {
                // If start already exists, have to break up
                if( st != -1 ) {
                    int j = i+1;
                    if(!ans.isEmpty()) j = i;

                    for (; j <= st; j++) {
                        ans.append(c[j]);
                    }

                    st = -1;
                }
            }

            // Case for the last character
            if(i == 0 && st != -1) {
                if(!ans.isEmpty()) {
                    ans.append(' ');
                }

                for (int j = i; j <= st; j++) {
                    ans.append(c[j]);
                }

                st = -1;
            }

        }
        return ans.toString();
    }
    // 1) Remove outer parenthesis
    // Easy https://leetcode.com/problems/remove-outermost-parentheses/
    public String removeOuterParentheses(String s) {
        int o = 0;
        int c = 0;
        String removed = "";
        StringBuilder a = new StringBuilder();
        for(char i: s.toCharArray()) {
            // Dont take the starting bracket
            if(o == c) {
                if(i == '(') o++;
                else c++;
                continue;
            }

            if(i == '(') {
                o++;
            }
            else c++;

            if (o != c) {
                a.append(i);
            }
        }
        return a.toString();
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
