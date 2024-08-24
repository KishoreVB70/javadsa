package Sorting;

public class CountSort {
    public static void main(String[] args) {
        int[] arr = {1,1,2,4,4};
        int[] ar = countSort(arr);
        for (int i = 0; i < ar.length; i++) {
            System.out.println(ar[i]);

        }
    }

    public static int[] countSort(int[] arr ) {
        // 1 -> Find the largest value
        int largest = findLargest(arr);

        // 2 -> create array
        int[] countArr = new int[largest + 1];

        // 3 -> Populate the count array
        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i]] += 1;
        }

        // 4 -> Modify the original array
        // Outer loop to go through the count array
        int j = 0;
        for (int i = 0; i < countArr.length; i++) {
            // Inner loop to modify the original array
            while (countArr[i] > 0) {
                arr[j] = i;
                countArr[i]--;
                j++;
            }
        }
        return arr;
    }

    static int findLargest(int[] arr) {
        int largest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            }
        }
        return largest;
    }
}
