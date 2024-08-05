package patterns;

import java.util.Arrays;

public class MathematicProblems {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,2,1,4};
        findAllThePrimeNumbersTillN(50);
    }

    static boolean isOdd(int n) {
        return ((n & 1) == 1);
    }

//    static int findTheSquareRootOfPerfectSquareNumbers(int n) {
//
//    }

    static void findAllThePrimeNumbersTillN(int n) {
        boolean[] notPrime = new boolean[n+1];
        // Outer for loop
        for (int i = 2; i <= n; i++) {
            if (notPrime[i]) {
                continue;
            }

            boolean primer = true;

            // Check if prime
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    primer = false;
                    break;
                }
            }

            if (primer) {
                // Eliminate all the multiples of i
                for (int k = 2*i; k <= n; k = k+i) {
                    notPrime[k] = true;
                }
            }
        }

        for (int i = 2; i <= n; i++) {
            if (!notPrime[i]) {
                System.out.printf("%d is prime", i);
                System.out.println();
            }
        }
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

