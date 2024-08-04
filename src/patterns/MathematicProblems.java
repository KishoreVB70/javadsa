package patterns;

public class MathematicProblems {
    public static void main(String[] args) {
        int[] nums = {-1,2,3,-4,-2,1,4};
        System.out.println(findOneNonDuplicateNumberInNegativePositiveArray(nums));
        System.out.println(isOdd(21));
    }

    static boolean isOdd(int n) {
        return ((n & 1) == 1);
    }

    static int findOneNonDuplicateNumberInNegativePositiveArray(int[] nums) {
        int sum = 0;
        for(int i: nums) {
            sum += i;
        }
        return sum;
    }
}

