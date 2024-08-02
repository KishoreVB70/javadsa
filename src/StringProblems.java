public class StringProblems {
    public static void main(String[] args) {
        String s = "malayalam";
        System.out.println(findPalindrome(s));
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
