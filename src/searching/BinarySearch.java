package searching;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,3};
//        int result = findTheRotationIndexOfRotatedSortedArray(arr);
        int result = findInRotatedSortedArray(arr, 0);
        System.out.println(result);
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
    static int findTheRotationIndexOfRotatedSortedArray(int[] nums) {
        //Kind of a helper function
        int start = 0;
        int end = nums.length - 1;
        int n = (start + end) /2;


        // Check if there is no pivot -> normal ascending array
        if (nums[end] >= nums[n] && nums[n] >= nums[start]) {
            return 0;
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
        return -1;
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
    static char nextGreatestLetter(char[] letters, char target) {
        String url = "https://leetcode.com/problems/find-smallest-letter-greater-than-target/";
        int start  = 0;
        int end = letters.length - 1;

        while (start <= end) {
            int n = (start + end) / 2;
            if (letters[n] == target) {
                // If the target is the last element in the array
                if(n == letters.length - 1) {
                    return letters[0];
                }
                // return the greatest
                start = n + 1 ;

            }

            if (target > letters[n]) {
                start += 1;
            } else if (target < letters[n]){
                end -= 1;
            }
        }
        if (start > (letters.length - 1)) {
            return letters[0];
        }

        return letters[start];

    }
    static int ceilingOfaNumber(int[] arr, int target) {
        if (arr.length == 0 ) {
            return -1;
        }

        int start = 0;
        int end = arr.length - 1;
        boolean isAsc = arr[start] < arr[end];

        while (end >= start) {
            int i = (start + end) / 2;

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

        if (start > arr.length - 1) {
            return  -1;
        }

        return start;
    }
    static int floorOfaNumber(int[] arr, int target) {
        if (arr.length == 0 ) {
            return -1;
        }

        int start = 0;
        int end = arr.length - 1;
        boolean isAsc = arr[start] < arr[end];

        while (end >= start) {
            int i = (start + end) / 2;

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

        if (start > arr.length - 1) {
            return  -1;
        }

        return end;
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
