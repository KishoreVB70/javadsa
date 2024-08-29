package searching;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        int[] arr1 = {4,5,1,2,3};
        System.out.println(searchDupe(arr, 0));

//        System.out.println(Arrays.toString(searchRange(arr, 7)));
    }

//-----------------------------Revision------------------------------------------------------------

    public static int findPivotDuplicates(int[] arr) {
        int s = 0;
        int e = arr.length -1;

        // No pivot
        if (arr[s] < arr[e]) {
            return e;
        }

        while (s <= e) {
            int m = s + (e -s)/2;

            // Success conditions
            if (m < e &&  arr[m] > arr[m+1]) {
                return m;
            }
            if (m > s && arr[m-1] > arr[m]) {
                return m-1;
            }

            // Skip conditions

            // In case of duplicates, it might be that the start, mid and end will all be the same element
            if (arr[s] == arr[m] && arr[m] == arr[e]) {
                // Reduce the array

                // Check if either start or end is the pivot
                if (arr[s] > arr[s+1]) {
                    return s;
                }
                s++;

                if (e < arr.length-1 && arr[e] > arr[e+1]) {
                    return  e;
                }
                e--;
            }


            // If start and middle are equal, and the end is not equal
            // then it means that the pivot can't be between start and mid
            else if (arr[s] == arr[m]) {
                s = m+1;
            }

            // If the middle and end are equal, then it must be in the start
            else if (arr[m] == arr[e]) {
                e = m-1;
            }

            else if (arr[s] > arr[m]) {
                e = m-1;
            }
            // If the start is lesser than the middle element, then
            // Pivot must definitely be on it's right

            // arr[s] < arr[m]
            else {
                s = m+1;
            }
        }

        return 0;
    }


    // 11)

    // 10) How many times array is rotated
    static int numberOfRotations(int[] nums) {
        int pivot = findPivot(nums);
        if (pivot == nums.length -1) {
            return 0;
        } else {
            return pivot +1;
        }
    }

    // 9) Rotated sorted array with duplicates
    public static boolean searchDupe(int[] nums, int target) {
        // 1 -> Find the pivot index
        int pivot = findPivotDuplicates(nums);

        if(nums[pivot] == target) {
            return true;
        }

        // No Pivot
        if (pivot == nums.length -1) {
            return simpleBinarySearch(nums, target) != -1;
        }

        // Left side
        if (target >= nums[0]) {
            return binarySearchSpecificIndex(nums, 0, pivot-1, target) != -1;
        }
        // Right side
        else {
            return binarySearchSpecificIndex(nums, pivot+1, nums.length-1, target) != -1;
        }

    }


    // 8) Search in rotated sorted array
    // Medium https://leetcode.com/problems/search-in-rotated-sorted-array/
    public static int search(int[] nums, int target) {
        // 1 -> Find the pivot index
        int pivot = findPivot(nums);

        if(nums[pivot] == target) {
            return pivot;
        }

        // No Pivot
        if (pivot == nums.length -1) {
            return simpleBinarySearch(nums, target);

        }

        // Left side
        if (target >= nums[0]) {
            return binarySearchSpecificIndex(nums, 0, pivot-1, target);
        }
        // Higher than pivot
        else {
            return binarySearchSpecificIndex(nums, pivot+1, nums.length-1, target);
        }

    }



    // 7) Find in mountain array
    // Hard https://leetcode.com/problems/find-in-mountain-array/submissions/
    static public int findInMountainArray(int target, MountainArray mountainArr) {
        // 1) Get the peak index
        int s = 0;
        int e = mountainArr.length() -1;

        while (s < e) {
            int m = s + (e-s)/2;
            if (mountainArr.get(m) > mountainArr.get(m+1)) {
                e = m;
            }
            else if (mountainArr.get(m) < mountainArr.get(m+1)) {
                s = m+1;
            }
        }

        int peak = s;


        // 2) Asc binary search in the left side
        s = 0;
        e = peak;
        while (s <= e) {
            int m = s + (e-s)/2;
            if (target == mountainArr.get(m)) {
                return m;
            } else if (target > mountainArr.get(m)) {
                s = m+1;
            } else {
                e = m-1;
            }
        }
        // 3) Dsc binary search in the right side
        s = peak +1;
        e = mountainArr.length()-1;
        while (s <= e) {
            int m = s + (e-s)/2;
            if (target == mountainArr.get(m)) {
                return m;
            } else if (target > mountainArr.get(m)) {
                e = m-1;
            } else {
                s = m+1;
            }
        }
        return -1;
    }

    //6) Peak index in a mountain array
    // Medium https://leetcode.com/problems/peak-index-in-a-mountain-array/
    // Medium https://leetcode.com/problems/find-peak-element/
    static public int peakIndexInMountainArray(int[] arr) {
        int s = 0;
        int e = arr.length -1;

        while (s < e) {
            int m = s + (e-s)/2;
            if (arr[m] > arr[m+1]) {
                e =m;
            } else {
                s = m+1;
            }
        }
        // We can return either s or e, as the termination condition is when both are equal
        return e;
    }

    //5) Position of element in sorted infinite array
    // Using binary search without using the length of the array
    public int positionOfEleInInfiniteArray(int[] nums, int target) {
        int s = 0;
        int e = 2;

        while (nums[e] < target) {
            s = e+1;
            e *= 2;
        }

        // Once it is less than that
        // Perform binary search
        while (s <= e) {
            int m = s + (e-s) /2;
            if (nums[m] == target) {
                return m;
            } else if (target > nums[m]) {
                s = m+1;
            } else {
                e = m-1;
            }
        }
        return -1;
    }

    //4) First and last position of an element in a duplicate array
    //medium https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
    public int[] searchRange(int[] nums, int target) {
        return new int[]{
                firstOccurrenceOfElementInDuplicateArray(nums, target),
                lastOccurrenceOfElementInDuplicateArray(nums,target)
        };
    }
    // This can be subdivided into 2 problems
    // A) Finding the first occurrence of an element in a duplicate array
    public int firstOccurrenceOfElementInDuplicateArray(int[] nums, int target) {
        int s = 0;
        int e = nums.length -1;
        while (s <= e) {
            int m = s + (e -s)/2;
            if (nums[m] == target) {
                e--;
            }
            else if(target > nums[m]) {
                s = m+1;
            } else {
                e = m-1;
            }
        }
        if (s < nums.length) {
            return nums[s]== target?s:-1;
        }
        return -1;
    }
    // B) Finding the last occurrence of an element in a duplicate array
    public int lastOccurrenceOfElementInDuplicateArray(int[] nums, int target) {
        int s = 0;
        int e = nums.length -1;
        while (s <= e) {
            int m = s + (e -s)/2;
            if (nums[m] == target) {
                s++;
            }
            else if(target > nums[m]) {
                s = m+1;
            } else {
                e = m-1;
            }
        }
        if (e >= 0) {
            return nums[e]== target?e:-1;
        }
        return e;
    }

    // 3)Find the next greater char than the target
    // Easy https://leetcode.com/problems/find-smallest-letter-greater-than-target/
    char nextGreatestLetter(char[] letters, char target) {
        int s  = 0;
        int e = letters.length - 1;
        while (s <= e) {
            int m =  s +(e -s) / 2;

            if (letters[m] == target) {

                // Skip over the duplicates
                while (m < letters.length -1 &&  letters[m] == letters[m+1]) {
                    m = m+1;
                }

                // Find if the non duplicate is the last element
                if(m == letters.length - 1) {
                    return letters[0];
                }


                // If it is not the last, return the element next to it
                return letters[m+1];
            }
            else if (target > letters[m]) {
                s = m + 1;
            } else {
                e = m- 1;
            }
        }

        // Final condition
        if (s == letters.length) {
            return letters[0];
        }

        return letters[s];
    }

    // 2) Floor of a number
    int floorOfNumber(int[] arr, int target) {
        int s = 0;
        int e = arr.length -1;
        while (s <= e) {
            int m = s + (e-s)/2;
            if (arr[m] == target) {
                return m;
            }
            else if(target > arr[m]) {
                s = m+1;
            } else {
                e = m-1;
            }
        }
        return e;
    }

    // 1) Ceiling of a number
    int ceilingOfNumber(int[] arr, int target) {
        int s = 0;
        int e = arr.length -1;
        while (s <= e) {
            int m = s + (e-s)/2;
            if (arr[m] == target) {
                return m;
            }
            else if(target > arr[m]) {
                s = m+1;
            } else {
                e = m-1;
            }
        }
        if (s >= arr.length) {
            return -1;
        }

        return s;
    }




// -------------------------2D matrix problems ----------------------------------------------------
    // 1) Not strictly sorted Matrix -> Increasing in both row and column
    static int[] binarySearch2DArray(int[][] arr, int target) {
        int[] result = new int[2];
        int row = 0;
        int col = arr[0].length - 1;
        while (row < arr.length && col >= 0) {
            int current = arr[row][col];
            if (current == target) {
                result[0] = row;
                result[1] = col;
                return result;
            }
            else if(target > current) {
                row++;
            } else {
                col--;
            }
        }
        return result;
    }

    // 2) Strictly sorted matrix
    static int[] binarySearchStrictMatrix(int[][] arr, int target) {
        int row = arr.length;
        int col = arr[0].length - 1;

        if (row == 1) {
            return oneDBinarySearch(arr, 0, 0, col, target);
        }

        int rStart = 0;
        int rEnd = row -1;
        int cMid = (col)/2;
        while (rStart != rEnd -1) {
            int rMid = rStart + (rEnd - rStart) /2;

            int val = arr[rMid][cMid];
            if (val == target) {
                return new int[]{rMid, cMid};
            }
            else if (target > val) {
                rStart = rMid;
            } else {
                rEnd = rMid;
            }
        }

        // Only two columns remaining
        if (arr[rStart][cMid] == target) {
            return new int[]{rStart, cMid};
        }
        else if (arr[rEnd][cMid] == target) {
            return new int[]{rStart, cMid};
        }

        // Conditions
        // 1) Quadrant A
        if (target < arr[rStart][cMid]) {
            return oneDBinarySearch(arr, rStart, 0, cMid-1, target);
        }

        // 2) Quadrant B
        else if (target > arr[rStart][cMid] && target < arr[rStart][col]) {
            return oneDBinarySearch(arr, rStart, cMid+1, col, target);
        }

        // 3) Quadrant C
        else if (target < arr[rEnd][cMid]) {
            return oneDBinarySearch(arr, rEnd, 0, cMid -1, target);
        }

        // 4) Quadrant D
        // if (target > arr[rEnd][cMid])
        else {
            return oneDBinarySearch(arr, rEnd, cMid+1, col, target);
        }
    }
    static int[] oneDBinarySearch(int[][] arr, int row, int cStart, int cEnd, int target) {
        while (cStart <= cEnd) {
            int cMid = cStart + (cStart - cEnd)/2;
            int val = arr[row][cMid];
            if (val == target) {
                return new int[] {row, cMid};
            }
            // Target is greater -> start = mid
            else if (target > val) {
                cStart = cMid +1;
            }
            // Target is smaller -> cEnd changes
            else {
                cEnd = cMid -1;
            }
        }
        return new int[] {-1,-1};
    }

// -------------------------- 1 D Array problems ---------------------------------------------------
    static int splitArrayLargestSumBinarySearch(int[] nums, int k) {
        String url = "https://leetcode.com/problems/split-array-largest-sum/submissions/1340552974/";
        // 1) Identify the largest and smallest possible value
        int start = 0;
        int end = 0;

        // Finding the largest -> Sum of the array
        //Finding the smallest -> largest value in the array
        for (int i: nums) {
            end += i;
            start = Integer.max(start, i);
        }

        // 2) Apply binary search
        int arrayEndIndex = nums.length - 1;
        while (start < end) {
            int n = (start + end) / 2;
            int numberOfArrays = 1;
            int i = 0;

            // Run the loop for all the elements
            while (i < arrayEndIndex) {
                int sum = nums[i];
                boolean breakLoop = false;
                while (sum <= n) {
                    if (i == arrayEndIndex) {
                        breakLoop = true;
                        break;
                    }
                    sum += nums[i+1];
                    i++;
                }
                if (!breakLoop) {
                    numberOfArrays++;
                }
            }

            if (numberOfArrays > k) {
                start = n+1;
            } else{
                end = n;
            }
        }
        return start;
    }
    static int splitArrayLargestSumBruteForce(int[] nums, int k) {
        int lowest = Integer.MAX_VALUE;
        int end = nums.length -1;
        int numberOfRounds = k -1;
        // Number of arrays = k
        int[] startIndex = new int[k];
        // Populate the start index array
        for (int i = 0; i <= k; k++) {
            startIndex[i] = i;
        }

        int endIndex = k;
        // Start rounds
        for (int round = 0; round < endIndex ; round++) {

            // Define the end condition of each round
            while (startIndex[endIndex -1] < endIndex) {
                // Calculate the array total for each array
                int[] arraySum = new int[k];
                for (int array = 0; array <= startIndex[array + 1]; k++) {
                    arraySum[array] += nums[array];
                }

                // Find the maximum value of all array totals
                int max = arraySum[0];
                for (int i = 1; i < arraySum.length; i++) {
                    max = Math.max(max, arraySum[i]);
                }

                // If it is lower than the previous, set it to it;
                if (max < lowest) {
                    lowest = max;
                }
            }

            // End of round -> round++
            endIndex--;
            round++;
        }

        return lowest;
    }
    static  int findMinimumValueInRotatedSortedArrayWithDuplicates(int[] nums) {
        String url = "https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/";
        // 1) Find the pivot
        int pivot = findTheRotationIndexOfRotatedSortedArrayWithDuplicates(nums);
        if (nums.length == 1) {
            return nums[0];
        }

        // If there is no pivot, then it is normal array, first element will be the smallest
        if (pivot == -1) {
            return nums[0];
        }
        // 2) Only elements that are to the right of the pivot would be smaller
        return nums[pivot + 1];
    }
    static boolean findInRotatedSortedArrayWithDuplicatesReturnBoolean(int[] nums, int target) {
        if (nums.length == 1) {
            return nums[0] == target;
        }
        int end = nums.length -1;
        // 1) Pivot
        int pivot = findTheRotationIndexOfRotatedSortedArrayWithDuplicates(nums);
        int value;


        //2) If the pivot is at the end
        if (pivot == -1) {
            value = helperOrderAgnosticBinarySearch(nums,0, end, target, true);
            if (value == -1) {
                return false;
            } else {
                return true;
            }
        }

        // 3) If there is a pivot, Check if pivot is the target
        if (nums[pivot] == target) {
            return true;
        }

        // 4) Check if value is in the left half or the right half

        //Search the right half
        if (target < nums[0] && target < nums[pivot]) {
            value = helperOrderAgnosticBinarySearch(nums,pivot +1, end, target, true);
        }
        //Search left half
        else {
            value = helperOrderAgnosticBinarySearch(nums, 0, pivot-1, target, true);
        }

        if (value == -1) {
            return false;
        } else {
            return true;
        }
    }
    static int findTheRotationIndexOfRotatedSortedArrayWithDuplicates(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int n = (start + end) /2;


        // If start, middle and end are equal, Only the middle can be the pivot
        if (nums[end] == nums[start] && nums[start] == nums[n]) {
            end--;
        }
        // Check if there is no pivot -> normal ascending array
        else if (nums[end] >= nums[n] && nums[n] >= nums[start]){
            return -1;
        }

        // Ran only if there is a pivot
        while (start < end) {
            n = (start + end) /2;

            // Checking if n or n-1 is the pivot
            // Check if n is the pivot
            // If the number is greater than the right, then it is automatically the pivot, no exceptions
            if (nums[n] > nums[n + 1]) {
                return n;
            }

            //Check if n-1 is the pivot
            if (n > 0) {
                if (nums[n - 1] > nums[n]) {
                    return n-1;
                }
            }

            // If start, middle and end are equal, Only the middle can be the pivot
            if (nums[end] == nums[start] && nums[start] == nums[n]) {
                end--;
                continue;
            }

            // If n or n-1 is not the pivot

            // If n is lesser than the start, then it would lie between
            if (nums[n] < nums[start]) {
                end = n - 1;
            }
            // If n is greater than the start, then it would lie after
            else if(nums[n] >= nums[start]) {
                start = n + 1;
            }
        }
        return start;
    }
    private static int helperOrderAgnosticBinarySearch(int[] arr, int start, int end, int target, boolean isAscending) {
        while (start <= end) {
            int n = (start + end )/2;
            if (arr[n] == target) {
                return n;
            }

            if (isAscending) {
                if(target > arr[n]) {
                    start = n+1;
                } else {
                    end = n-1;
                }
            }

            else {
                if(target > arr[n]) {
                    end = n-1;
                } else {
                    start = n + 1;
                }
            }
        }
        return -1;
    }
    static  int findMinimumValueInRotatedSortedArray(int[] nums) {
        String url = "https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/";
        // 1) Find the pivot
        int pivot = findTheRotationIndexOfRotatedSortedArray(nums);

        // If there is no pivot, then it is normal array, first element will be the smallest
        if (pivot == -1) {
            return nums[0];
        }
        // 2) Only elements that are to the right of the pivot would be smaller
        return nums[pivot + 1];
    }
    static int findTheRotationIndexOfRotatedSortedArray(int[] nums) {
        //Kind of a helper function
        int start = 0;
        int end = nums.length - 1;
        int n = (start + end) /2;


        // Check if there is no pivot -> normal ascending array, return -1
        if (nums[end] >= nums[n] && nums[n] >= nums[start]) {
            return -1;
        }

        // Ran only if there is a pivot
        while (start < end) {
            n = (start + end) /2;

            // Check for the pivot in the middle number or n-1

            // Check if n is the pivot
            if (nums[n] > nums[n + 1]) {
                return n;
            }

            //Check if n-1 is the pivot
            if (nums[n - 1] > nums[n]) {
                return n-1;
            }

            // If n or n-1 is not the pivot

            // If n is lesser than the start, then it would lie between
            if (nums[n] < nums[start]) {
                end = n - 1;
            }
            // If n is greater than the start, then it would lie after
            else if(nums[n] > nums[start]) {
                start = n + 1;
            }
        }
        return 0;
    }



//--------------------------------------Helper functions---------------------------------
    public static int findPivot(int[] arr) {
        int s = 0;
        int e = arr.length -1;

        // No pivot
        if (arr[s] < arr[e]) {
            return e;
        }

        while (s <= e) {
            int m = s + (e -s)/2;

            // Success conditions
            if (m < e &&  arr[m] > arr[m+1]) {
                return m;
            }
            if (m > s && arr[m-1] > arr[m]) {
                return m-1;
            }

            // Skip conditions

            // If the first element is greater than the middle element,
            // Then definitely the pivot must also be to it's left
            if (arr[s] > arr[m]) {
                e = m-1;
            }
            // If the start is lesser than the middle element, then
            // Pivot must definitely be on it's right

            // arr[s] < arr[m]
            else {
                s = m+1;
            }
        }

        return 0;
    }



    static int binarySearchSpecificIndex(int[] arr, int s, int e, int target) {
        while (s <= e) {
            int m = s + (e-s)/2;
            if (arr[m] == target) {
                return m;
            }
            else if (arr[m] > target) {
                e = m-1;
            } else {
                s = m+1;
            }
        }
        return -1;
    }

    // Will work for the ones with duplicates also

    static int simpleBinarySearch(int[] arr, int target) {
        if (arr.length == 0 ) {
            return -1;
        }

        int start = 0;
        int end = arr.length - 1;

        while (end >= start) {
            int i = (start + end) /2;

            if (arr[i] == target) {
                return i;
            } else if (target > arr[i]) {
                start = i + 1;
            }else {
                end = i - 1;
            }
        }

        return -1;
    }

    static int orderAgnostic(int[] arr, int target) {
        if (arr.length == 0 ) {
            return -1;
        }

        int start = 0;
        int end = arr.length - 1;
        boolean isAsc = arr[start] < arr[end];

        while (end >= start) {
            int i = (start + end) /2;

            if (arr[i] == target) {
                return i;
            }

            if (isAsc) {
                if (target > arr[i]) {
                    start = i + 1;
                }
                else {
                    end = i - 1;
                }
            }
            else {
                if (target > arr[i]) {
                    end = i - 1;
                }
                else {
                    start = i + 1;
                }
            }
        }
        return -1;

    }
    static int descBinarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        if (arr.length == 0 ) {
            return -1;
        }


        while (end >= start) {
            int i = (start + end) /2;

            if (arr[i] == target) {
                return i;
            } else if (target > arr[i]) {
                end = i - 1;
            }else {
                start = i + 1;
            }
        }

        return -1;
    }
// ---------------------------------------------- Stuffs needed---------------------------
static class MountainArray {
    int[] arr;

    MountainArray(int[] arr) {
        this.arr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
    }

    public int get(int index) {
        return this.arr[index];
    }
    public int length() {
        return arr.length;
    }
}
}