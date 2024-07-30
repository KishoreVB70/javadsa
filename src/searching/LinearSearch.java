package searching;

public class LinearSearch {
    public static void main(String[] args) {

//        int[] arr = {1,2,3,4,6,7};
//        int target = 0;
        //        boolean result = simpleLinearSearchIntBoolean(bot, t);
//        int result = simpleLinearSearchIntIndex(arr, target);


        String s = "Motorolla";
        char target = 'o';

        int result = stringLinearSearch(s, target);
        System.out.println(result);
    }

    static int stringLinearSearch(String s, char target) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == target) {
                return i;
            }
        }

        return -1;
    }

    static boolean simpleLinearSearchIntBoolean(int[] arr, int target) {
        for (int i: arr) {
            if (i == target) {
                return true;
            }
        }
        return false;
    }

    static int simpleLinearSearchIntIndex(int[] arr, int target) {
        if (arr.length == 0) {
            return -1;
        }

        for(int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }
}
