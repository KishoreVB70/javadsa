package searching;

public class LinearSearch {
    public static void main(String[] args) {

        int[] bot = {1,2,3,4,6,7};
        int t = 2;
        boolean result = simpleLinearSearchInt(bot, t);
        System.out.println(result);
    }

    static boolean simpleLinearSearchInt(int[] arr, int target) {
        for (int i: arr) {
            if (i == target) {
                return true;
            }
        }
        return false;
    }
}
