package searching;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {0,10,50,2,0};
        int result = findInMountainArray(arr, 2);
        System.out.println(result);
    }

    static int findInMountainArray(int[] mountainArr, int target) {
        int end = mountainArr.length -1;

        // Find the peak point
        int peakIndex = peakIndexInMountainArray(mountainArr);

        // Do 2 binary search, one for the left of the peak and one for the right of the peak
        int indexInAscendingHalf = helperForMountainFinder(mountainArr, 0, peakIndex, target, true);
        int indexInDescendingHalf = helperForMountainFinder(mountainArr, peakIndex, end, target, false);

        if (indexInDescendingHalf == Integer.MAX_VALUE &&  indexInAscendingHalf == Integer.MAX_VALUE) {
            return  -1;
        } else return Math.min(indexInDescendingHalf, indexInAscendingHalf);

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

    static int peakIndexInMountainArray(int[] arr) {
        String url = "https://leetcode.com/problems/peak-index-in-a-mountain-array/description/";
        int start = 0;
        int end = arr.length - 1;
        int n = 0;

        while (start < end) {
            n = (start + end) / 2;
            if (arr[n+1] > arr[n]) {
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
