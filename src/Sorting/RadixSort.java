package Sorting;

public class RadixSort {
    static int[] countSoort(int[] arr ) {
        // 1 -> Find the largest value
        int largest = findLargest(arr);

        // 1 B -> get the number of digits for that number
        int largestDigits = findLargestDigits(largest);

        // 2 -> Sort according to the last digit

        // Outer loop for the digit
        for (int i = 0; i <= largestDigits; i++) {

            // Inner loop for going through the array
            for (int j = 0; j < arr.length; j++) {
                int lastDigit = arr[i];
            }
        }
    }

    private static int findLargestDigits(int largest) {
        int count = 0;
        while (largest >=  1) {
            largest /= 10;
            count++;
        }
        return count;
    }

    static int findLargest(int[] arr) {
        int largest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                largest = i;
            }
        }
        return largest;
    }
}
