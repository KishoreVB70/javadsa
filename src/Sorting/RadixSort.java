package Sorting;

public class RadixSort {
    static int[] radixSort(int[] arr ) {
        // 1 -> Find the largest value
        int largest = findLargest(arr);

        // 2 -> Sort according to the last digit
        // Doubt
        for (int exp = 1; exp/largest > 0; exp *= 10) {


        }
        return arr;
    }

    static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Populating count array
        for (int j : arr) {
            count[(j / exp) % 10]++;
        }

        // Mapping for the original digit
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
