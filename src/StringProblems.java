public class StringProblems {
    public static void main(String[] args) {
        String s = "52";
        System.out.println(largestOddNumber(s));
    }

    // Striver easy problems
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
