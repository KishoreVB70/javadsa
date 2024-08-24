package Arrays;

public class ArrayProblems {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        sumOfRangeSqrt(arr, 2, 5);
    }
    // Square root decomposition ->  Advanced algorithm for Range problems

    // Q -> find the sum of range in an array
    static int sumOfRangeSqrt(int[] arr, int s, int e) {
        // 1 -> Create the block array
        int sqrt = (int)Math.sqrt(arr.length);

        int[] blocks = new int[sqrt + 1];
        int blockIndex = -1;
        for (int i = 0; i < arr.length; i++) {
            if (i % sqrt == 0) {
                blockIndex++;
            }
            blocks[blockIndex] += arr[i];
        }

        for (int i = 0; i < blocks.length; i++) {
            System.out.println(blocks[i]);
        }



        return 1;
    }
}
