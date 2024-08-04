package patterns;

import java.util.Arrays;

public class MathematicProblems {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,2,1,4};
        System.out.println(Arrays.toString(findAllThePrimeNumbersTillN(20)));
    }

    static boolean isOdd(int n) {
        return ((n & 1) == 1);
    }

    static int[] findAllThePrimeNumbersTillN(int n) {
        int[] result = new int[20];
        int k = 0;
        for (int i = 2; i <= n; i++) {
            boolean continuer = false;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    continuer = true;
                    break;
                }
            }
            if (continuer) {
                continue;
            }
            result[k] = i;
            k++;
        }
        return result;
    }

    static int findOneNonDuplicateNumberInNegativePositiveArray(int[] nums) {
        int sum = 0;
        for(int i: nums) {
            sum += i;
        }
        return sum;
    }

    static  int findOneUniqueNumberInPositiveArray(int[] nums) {
        int unique = 0;
        for(int i: nums) {
            unique = unique ^ i;
        }
        return unique;
    }

    static int findTheIthBit(int num, int n) {
        // 1 -> create mask
        int mask = 1;
        mask = mask << (n - 1);

        // AND
        mask = num & mask;

        // Unshift
        mask = mask >> (n-1);

        return mask;
    }

    static int setTheIthBit(int num, int n) {
        // 1 -> create mask
        int mask = 1 << (n - 1);

        // 2-> OR operation
        mask = mask | num;

        return mask;
    }

    static int unsetTheIthBit(int num, int n) {
        // 1 -> create mask
        int mask = 1 << (n-1);
        // 2 -> Not the mask
        mask = ~mask;
        // 3 -> AND operation
        return(mask & num);
    }

    static int findtheRightMostSetInteger(int num) {
        return 1;
    }

}

