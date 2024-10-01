import java.util.Arrays;

public class StringProblems {
    public static void main(String[] args) {
        String s = "52";
        System.out.println(largestOddNumber(s));
    }

    // Striver easy problems

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
