package Arrays;

import java.util.*;

public class ArrayProblems {
    public static void main(String[] args) {
        int[] arr = {-3,4,3,90};
        System.out.println(Arrays.toString(twoSum(arr, 6)));
    }
    // Square root decomposition ->  Advanced algorithm for Range problems


    // 2 sum II


    // Word ladder
    // Google Hard question
    // https://leetcode.com/problems/word-ladder/description/
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 1 -> check if the end word is in the wordList
        if (!wordList.contains(endWord)) {
            return 0;
        }

        // Visited set
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        Set<String> set = new HashSet<>(wordList);
        set.remove(beginWord);
        int result = 0;


        // n loop
        while (!q.isEmpty()) {
            int qSize = q.size();
            result++;

            for (int k = 0; k < qSize; k++) {
                String current = q.poll();

                // m loop
                for (int i = 0; i < current.length(); i++) {
                    char[] chc = current.toCharArray();

                    // Constant loop
                    for (char c = 'a'; c <= 'z'; c++) {
                        chc[i] = c;
                        String temp = new String(chc);

                        // Base condition
                        if (temp.equals(endWord)) return result+1;

                        if (set.contains(temp)) {
                            // Add it to queue
                            q.offer(temp);
                            // Remove it from list
                            set.remove(temp);
                        }
                    }
                }
            }
        }
        return 0;
    }


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

    // 2 sum -> Leet code problem 1
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            if (map.containsKey(sub)){
                result[0] = i;
                result[1] = map.get(sub);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    // 3 Sum
    //https://leetcode.com/problems/3sum/description/
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // 1 -> sort the array
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Base condition
            if (nums[i] > 0) {
                break;
            }

            // Duplicate condition
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int j = i+1;
            int k = nums.length -1;
            // Two sum
            while (j < k) {
                // Checking sum
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    List<Integer> lt = new ArrayList<>(3);
                    lt.add(nums[i]);
                    lt.add(nums[j]);
                    lt.add(nums[k]);
                    result.add(lt);

                    j++;
                    k--;

                    // Continue over duplicates -> J
                    while (j < k && nums[j] == nums[j-1]) {
                        j++;
                    }

                    // Continue over duplicates -> K
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
                else if (sum < 0) {
                    j++;
                }
                else{
                    k--;
                }
            }
        }
        return result;
    }
}
