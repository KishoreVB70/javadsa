package Arrays;

import java.util.*;

public class ArrayProblems {
    public static void main(String[] args) {
        int[] arr = {-2,0,1,1,2};
        List<String> sts =  new ArrayList<>();
        sts.add("hot");
        sts.add("dot");
        sts.add("dog");
        sts.add("lot");
        sts.add("log");
        sts.add("cog");

        System.out.println(ladderLength("hit", "cog", sts));
    }
    // Square root decomposition ->  Advanced algorithm for Range problems


    // Word ladder
    // Google Hard question
    // https://leetcode.com/problems/word-ladder/description/

    static List<String> worddList;
    static String enddWord;
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // 1 -> check if the end word is in the wordList
        boolean pres = false;
        for (int i = 0; i < wordList.size(); i++) {
            if (Objects.equals(wordList.get(i), endWord)) {
                pres = true;
                break;
            }
        }

        if (!pres) {
            return 0;
        }

        enddWord = endWord;
        worddList = wordList;
        HashMap<Integer, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        set.add(beginWord);
        map.put(-1, beginWord);
        int result = recursionMan(beginWord, 0, 1, set);

        if (result < Integer.MAX_VALUE) {
            return result;
        } else {
            return 0;
        }
    }

    static int recursionMan(String prevWord, int index, int currentTotal, Set<String> set) {
        if (index >= worddList.size()) {
            return Integer.MAX_VALUE;
        }

        String currentWord = worddList.get(index);



        int result1 = Integer.MAX_VALUE;
        int result2;

        if (!set.contains(currentWord)) {
            if (ifOnlyOneChcIsDifferent(prevWord, currentWord)) {
                Set<String> newSet = new HashSet<>(set);
                newSet.add(currentWord);

                if (Objects.equals(currentWord, enddWord)) {
                    return ++currentTotal;
                }

                result1 = recursionMan(currentWord, 0, currentTotal+1, newSet);
            }
        }

        result2 = recursionMan(prevWord, index+1, currentTotal, set);

        if (result1 < result2) {
            return result1;
        } else {
            return result2;
        }

    }

    static boolean ifOnlyOneChcIsDifferent(String st, String current) {
        int total = st.length();
        for (int i = 0; i < st.length(); i++) {
            if (st.charAt(i) == current.charAt(i)) {
                total--;
            }
        }
        return total == 1;
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
