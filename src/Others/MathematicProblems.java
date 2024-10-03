package Others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathematicProblems {
    public static void main(String[] args) {

        System.out.println(numberOfSetBits(3));
    }

    //-----------------Striver Bit manipulation series-------------------

    // 3) -------------------Small bit manipulation problems -----------------------------------

    // 7) Count the number of set bits
    public static  int numberOfSetBits(int a) {
        int count = 0;
        for (int i = 0; i< 30; i++) {
            int screen = 1 << i;
            if ((a & screen) > 0) count++;
        }
        return count;
    }
    // 6) Find if number is power of 2
    public static boolean isPowerOfTwo(int a) {
        if (a == 1) return false;
        return (a & (a-1)) == 0;
    }
    // 5) Removing the last set bit (Right most)
    public static int removeRightMostBit(int a) {
        return a & a-1;
    }


    // 4) Flip the ith bit
    public static int flipBit(int a, int i) {
        return a ^ (1 << i);
    }


    // 3) Setting and clearing a bit
    // a) Setting a bit
    public static int setBit(int a, int i) {
        int screen = 1 << i;
        return a | screen;
    }
    // b) Clearing a bit
    public static int clearBit(int a, int i) {
        int screen = 1 << i;
        screen = ~screen;
        return a & screen;

    }

    // 2) Find if the ith bit is set or not
    // a) Using right shift
    public static boolean ithBitSetRight(int a, int i) {
        int screen = 1;
        a = a >> i;
        a = a & screen;
        return a == 1;
    }

    // b) Using left shift
    public static boolean ithBitSetLeft(int a, int i) {
        int screen = 1;
        screen = screen << i;
        a = a & screen;
        return a >= 1;
    }
    // 1) Swap 2 numbers without a temporary variable
    public static int[] directSwap(int a, int b) {
        a = a ^ b;
        b = a^b;
        a = a^b;
        return new int[]{a,b};
    }

    // 2) Base 2 into base 10
    public static int getBase10(String s) {
        int total = 0;
        for(int i = s.length()-1; i>= 0; i--) {
            int power = s.length()-1 - i;
            if (s.charAt(i) == '1') total += (int)Math.pow(2, power);
        }
        return total;
    }
    // 1) Base 10 into base 2
    public static String getBase2(int i) {
        StringBuilder b2 = new StringBuilder();
        while (i > 1) {
            int rem = i % 2;
            i/=2;
            b2.insert(0, Integer.toString(rem));
        }
        b2.insert(0, "1");

        return b2.toString();
    }



    class Kunal {
        // Part 2
        static int lcm(int a, int b) {
            int d = hcf(a, b);
            return (a * b) / d;
        }
        static int hcf(int a, int b) {
            if (a == 0) {
                return  b;
            }
            return  hcf(b % a, a);
        }
        static boolean isOdd(int n) {
            return ((n & 1) == 1);
        }
        static void findTheFactorsOfNumber(int n) {
            List<Integer> roots = new ArrayList<>();

            for (int i = 1; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    System.out.print(i + " ");
                    roots.add(n/i);
                }
            }

            for (int i = roots.size() - 1; i >= 0; i--) {
                System.out.print(roots.get(i) + " ");
            }
        }
        static int findTheSquareRootOfPerfectSquareNumbers(int n) {
            int start = 0;
            int end = n/2;

            while (start < end) {
                int m = (start + end)/2;

                if (m*m == n) {
                    return m;
                } else if (m*m > n) {
                    end = m -1;
                } else {
                    start = m+1;
                }
            }

            return end;

        }
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


        // Part 1
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



}

