package searching;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {4,5,6,0,1,2};
        int result = findInRotatedSortedArray(arr, 9);
        System.out.println(result);
    }

    static int findInRotatedSortedArray(int[] nums, int target) {
        String url = "https://leetcode.com/problems/search-in-rotated-sorted-array/";
        int end = nums.length - 1;

        // 1) Find the rotation point
        int peakIndex = findTheRotationIndexOfRotatedSortedArray(nums);
        if (nums[peakIndex] == target) {
            return peakIndex;
        }

        // 2) Apply binary search to the left half
        int firstHalf = helperOrderAgnosticBinarySearch(nums, 0, peakIndex , target, true);
        if (firstHalf != -1 ) {
            return firstHalf;
        }

        // 3) Apply binary search to the right half
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
        int start = 0;
        int end = nums.length - 1;
        //Kind of a helper function
        while (start <= end) {
            int n = (start + end) /2;

            // If peak is at the start
            if (n == 0) {
                return 0;
            }

            // Check 1
            if (nums[n - 1] > nums[n]) {
                return n-1;
            }

            // Check 2
            if (nums[n] < nums[start]) {
                end = n - 1;
            } else if(nums[n] > nums[start]) {
                start = n;
            }
            // If nums[n] == nums[start]
            else  {
                start = n+1;
            }
        }
        return 0;
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
