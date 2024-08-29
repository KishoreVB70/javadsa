package searching;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {2,3,4,5,9,14,16};
        System.out.println(floorOfNumber(arr, 1));
    }

//-----------------------------Revision------------------------------------------------------------


    // 3)Find the next greater char than the target
    // Leet easy https://leetcode.com/problems/find-smallest-letter-greater-than-target/
    static char nextGreatestLetter(char[] letters, char target) {
        String url = "https://leetcode.com/problems/find-smallest-letter-greater-than-target/";
        int s  = 0;
        int e = letters.length - 1;
        while (s <= e) {
            int m =  s +(e -s) / 2;

            if (letters[m] == target) {
                // If the target is the last element in the array
                if(m == letters.length - 1) {
                    return letters[0];
                }
                // return the greatest
                return letters[m+1];
            }
            else if (target > letters[m]) {
                s = m + 1;
            } else {
                e = m- 1;
            }
        }

        return letters[s];
    }

    // 2) Floor of a number
    static int floorOfNumber(int[] arr, int target) {
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
    static int ceilingOfNumber(int[] arr, int target) {
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
    static int numberOfRotations(int[] nums) {
        return (findTheRotationIndexOfRotatedSortedArray(nums)) + 1;
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
    static int findInRotatedSortedArrayWithDuplicates(int[] nums, int target) {
        String url = "https://leetcode.com/problems/search-in-rotated-sorted-array/";
        int end = nums.length - 1;

        // 1) Find the pivot
        int pivotIndex = findTheRotationIndexOfRotatedSortedArrayWithDuplicates(nums);

        // If no pivot, then run binary search for entire array

        // If the pivot is at the end, then it is a normal ascending array, run binary search along the array
        if (pivotIndex == -1) {
            return helperOrderAgnosticBinarySearch(nums, 0, end , target, true);
        }

        // If there is a pivot

        // 1)Check if the pivot is the target
        if (nums[pivotIndex] == target) {
            while (pivotIndex > 0) {
                if (nums[pivotIndex -1] == nums[pivotIndex]) {
                    pivotIndex -=1;
                }else  {
                    break;
                }

            }
            return pivotIndex;
        }

        // 2) Only if the target is lesser than the peak and greater than the start, it can lie in the left half -> Apply binary search to the left half
        if (target >= nums[0] && target <= nums[pivotIndex] ) {
            // Check if pivot is at the start, if so, the target won't be present in the left
            if (pivotIndex == 0) {
                return -1;
            }

            return helperOrderAgnosticBinarySearch(nums, 0, pivotIndex -1, target, true);
        }

        // 3) Apply binary search to the right half, since that's the only thing left
        return helperOrderAgnosticBinarySearch(nums, pivotIndex + 1, end, target, true);
    }
    static int findInRotatedSortedArray(int[] nums, int target) {
        String url = "https://leetcode.com/problems/search-in-rotated-sorted-array/";
        int end = nums.length - 1;

        // 1) Find the pivot
        int peakIndex = findTheRotationIndexOfRotatedSortedArray(nums);

        // If no pivot, then run binary search for entire array

        // If the peak is at the end, then it is a normal ascending array, run binary search along the array
        if (peakIndex == -1) {
            return helperOrderAgnosticBinarySearch(nums, 0, end , target, true);
        }

        // If there is a pivot

        // 1)Check if the peak is the target
        if (nums[peakIndex] == target) {
            return peakIndex;
        }

        // 2) Only if the target is lesser than the peak and greater than the start, it can lie in the left half -> Apply binary search to the left half
        if (target >= nums[0] && target <= nums[peakIndex] ) {
            // Check if pivot is at the start, if so, the target won't be present in the left
            if (peakIndex == 0) {
                return -1;
            }

            return helperOrderAgnosticBinarySearch(nums, 0, peakIndex -1, target, true);
        }

        // 3) Apply binary search to the right half, since that's the only thing left
        return helperOrderAgnosticBinarySearch(nums, peakIndex + 1, end, target, true);
    }
    interface MountainArray {
        int[] arr = {0,10,50,2,0};

        public default int get(int index) {
          return arr[index];
        }
        public default int length() {
            return arr.length;
        }
    }
    static int findInMountainArrayLeetHardVersion(MountainArray mountainArr, int target) {
        String url = "https://leetcode.com/problems/find-in-mountain-array/";
        int start = 0;
        int end = mountainArr.length() - 1;

        // 1) Find the peak point
        int n = 0;
        while (start < end) {
            n = (start + end) / 2;
            if (mountainArr.get(n+1) > mountainArr.get(n)) {
                start = n + 1;
            } else {
                end = n;
            }
        }
        int peakIndex = start;

        start = 0;
        end = mountainArr.length() - 1;

        // 2) Do 2 binary search, one for the left of the peak and one for the right of the peak
        int indexInAscendingHalf = helperForMountainFinderLeetVersion(mountainArr, 0, peakIndex, target, true);
        int indexInDescendingHalf = helperForMountainFinderLeetVersion(mountainArr, peakIndex, end, target, false);

        // 3) Determine the return value
        if (indexInDescendingHalf == Integer.MAX_VALUE &&  indexInAscendingHalf == Integer.MAX_VALUE) {
            return  -1;
        } else return Math.min(indexInDescendingHalf, indexInAscendingHalf);

    }
    private static int helperForMountainFinderLeetVersion(MountainArray mountainArr, int start, int end, int target, boolean isAscending) {
        while (start <= end) {
            int n = (start + end )/2;
            if (mountainArr.get(n) == target) {
                return n;
            }

            if (isAscending) {
                if(target > mountainArr.get(n)) {
                    start = n+1;
                } else {
                    end = n-1;
                }
            }

            else {
                if(target > mountainArr.get(n)) {
                    end = n-1;
                } else {
                    start = n + 1;
                }
            }
        }
        return Integer.MAX_VALUE;
    }
    static int findInMountainArray(int[] nums, int target) {
        int end = nums.length -1;

        // Find the peak point
        int peakIndex = peakIndexInMountainArray(nums);

        // 2) Check if peak is what we're looking for
        if(target == nums[peakIndex]) {
            return peakIndex;
        }

        // 3) Do binary search for ascending
        int indexInAscendingHalf = helperForMountainFinder(nums, 0, peakIndex, target, true);

        // If gotten result, return it
        if (indexInAscendingHalf != -1) {
            return indexInAscendingHalf;
        }

        // 4) return the result of the reverse search
        return helperForMountainFinder(nums, peakIndex, end, target, false);

    }
    private static int helperForMountainFinder(int[] arr, int start, int end, int target, boolean isAscending) {
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
        return Integer.MAX_VALUE;
    }
    static int peakIndexInMountainArray(int[] nums) {
        String url = "https://leetcode.com/problems/peak-index-in-a-mountain-array/description/";
        String url2Medium = "https://leetcode.com/problems/find-peak-element/description/";
        int start = 0;
        int end = nums.length - 1;
        int n = 0;

        while (start < end) {
            n = (start + end) / 2;
            if (nums[n+1] > nums[n]) {
                start = n + 1;
            } else {
                end = n;
            }
        }
        return start;
    }
    static int positionOfElementInSortedInfiniteArray(int[] nums, int target) {
        int start = 0;
        int end = 1;

        if (nums[end] == target) {
            return end;
        }

        while(target > nums[end]) {
            start = end + 1;
            end = (end) + (end * 2);
        }

        while(start <= end) {
            int n = (start + end) / 2;
            if(nums[n] == target) {
                return n;
            }
            else if (target > nums[n]) {
                start = n+1;
            }
            else if (target < nums[n]) {
                end = n-1;
            }
        }

        return -1;
    }
    static int[] firstAndLastPosition(int[] nums, int target) {
        int[] position = new int[2];

        position[0] = helperForFirstAndLastPositionSearch(nums, target, true);
        position[1] = helperForFirstAndLastPositionSearch(nums, target, false);

        return position;
    }
    static int helperForFirstAndLastPositionSearch(int[] nums, int target, boolean first) {
        int ans = -1;
        int start = 0;
        int end = nums.length - 1;
        while(start <= end) {
            int n = (start + end) / 2;
            if (nums[n] == target) {
                ans = n;
                if (first) {
                    end = n - 1;
                } else {
                    start = n +1;
                }
            }
            if(target > nums[n] ) {
                start += 1;
            } else if (target < nums[n]) {
                end -= 1;
            }
        }
        return ans;

    }
    static int[] firstAndLastPositionLinearSearch(int[] nums, int target) {
        int[] position = {-1,-1};
        for(int i = 0; i < nums.length;i++) {
            if(nums[i] == target) {
                if (position[0] == -1) {
                    position[0] = i;
                }
                position[1] = i;
            }
        }
        return position;
    }



    // Helper functions
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
}