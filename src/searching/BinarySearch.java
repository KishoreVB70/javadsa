package searching;

import java.util.Arrays;

public class BinarySearch {
    public static void main(String[] args) {
//        char[] arr = {'e', 'e','g', 'g'};
        int[] descArr = {17,12,11,10,9,8,7,6,5,4,3,2,1};
        int[] arr = {1,2,3,4,5,5,7,8,9,9,10,11,12};

        char target = 8;

//        int result = simpleBinarySearch(arr, target);
//        char result = nextGreatestLetter(arr, target);
        int result = positionOfElementInSortedInfiniteArray(arr, target);
        System.out.println(result);



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
