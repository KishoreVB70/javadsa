import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {
        int[] nums = {2,2};
        int[][] maze = {{0,0}, {1,1}, {1,2}, {1,3}};

        List<String> result = findAllUniquePathsOfMazeString(nums);
        for(String i: result) {
            System.out.println(i);
        }
    }


    // Backtracking problems

    // 1) Finding the number of unique paths
    static int findNumberOfUniquePathsBacktracking(int[] goal) {
        int[] start = {0,0};

        return helperFindNumberOfUniquePathsBacktracking(goal, start);
    }
    static int helperFindNumberOfUniquePathsBacktracking(int[] goal, int[] current) {
        int count = 0;

        // Base condition
        if (current[0] == goal[0] || current[1] == goal[1]) {
            return 1;
        }

        // Either add it in the left -> index 0
        int[] temp = new int[2];
        temp[0] = current[0] + 1;
        temp[1] = current[1];
        count += helperFindNumberOfUniquePathsBacktracking(goal, temp);

        // Or add it in the right -> index 1
        current[1] = current[1] + 1;

        // Using the same input array
        count += helperFindNumberOfUniquePathsBacktracking(goal, current);
        return count;
    }

    // 2) Returning all the paths
    static List<List<List<Integer>>> findAllTheUniquePathsOfMaze(int[] goal) {
        List<List<List<Integer>>> returnList = new ArrayList<>();
        List<List<Integer>> processed = new ArrayList<>();
        List<Integer> current  = new ArrayList<>();
        current.add(0);
        current.add(0);
        helperFindAllTheUniquePathsOfMaze(goal, current, processed, returnList);
        return  returnList;
    }
    static void helperFindAllTheUniquePathsOfMaze(int[] goal, List<Integer> current, List<List<Integer>> processed, List<List<List<Integer>>> returnList) {
        List<List<Integer>> newProcessed = new ArrayList<>(processed);
        newProcessed.add(current);

        if (current.get(0) == goal[0] && current.get(1) == goal[1]) {
            returnList.add(newProcessed);
            return;
        }

        // Add to the left
        if (current.get(0) < goal[0]) {
            // Create new List
            List<Integer> temp = new ArrayList<>(current);
            temp.set(0, current.getFirst() + 1);
            helperFindAllTheUniquePathsOfMaze(goal, temp, newProcessed, returnList);
        }

        // Add to the right
        if (current.get(1) < goal[1]) {
            List<Integer> temp2 = new ArrayList<>(current);
            temp2.set(1, current.get(1) + 1);
            helperFindAllTheUniquePathsOfMaze(goal, temp2, newProcessed, returnList);
        }

    }
    static List<String> findAllUniquePathsOfMazeString(int[] goal) {
        List<String> resultList = new ArrayList<>();
        int[] current = {0,0};
        helperFindAllUniquePathsOfMazeString(goal, current, "", resultList);
        return resultList;
    }
    static void helperFindAllUniquePathsOfMazeString(int[] goal, int[] current, String processed, List<String> returnList) {
        if (Arrays.equals(goal,current)) {
            returnList.add(processed);
            return;
        }

        if (current[0] < goal[0]) {
            int[] newCurrent = new int[2];
            newCurrent[0] = current[0] + 1;
            newCurrent[1] = current[1];
            helperFindAllUniquePathsOfMazeString(goal, newCurrent, processed + "R", returnList);
        }

        if (current[1] < goal[1]) {
            int[] newCurrent = new int[2];
            newCurrent[0] = current[0];
            newCurrent[1] = current[1] + 1;
            helperFindAllUniquePathsOfMazeString(goal, newCurrent, processed + "D", returnList);
        }
    }


    static List<List<Integer>> helperFindAllTheUniquePathsOfMazeReturn(int[] goal, List<Integer> current, List<List<Integer>> processed, List<List<List<Integer>>> returnList) {
        List<List<Integer>> newProcessed = new ArrayList<>(processed);

        newProcessed.add(current);
        if (current.get(0) == goal[0] && current.get(1) == goal[1]) {
            return newProcessed;
        }

        // Add to the left
        if (current.get(0) < goal[0]) {
            // Create new List
            List<Integer> temp = new ArrayList<>(current);
            temp.set(0, current.getFirst() + 1);
            newProcessed.addAll(helperFindAllTheUniquePathsOfMazeReturn(goal, temp, newProcessed, returnList));
        }

        // Add to the right
        if (current.get(1) < goal[1]) {
            current.set(1, current.get(1) +1);
            newProcessed.addAll(helperFindAllTheUniquePathsOfMazeReturn(goal, current, newProcessed, returnList));
        }

        return newProcessed;

    }



    // Subset problems

    // Microsoft Dice problem
    static List<List<Integer>> diceCombinationToGetTheValue(int digit) {
        List<List<Integer>> returnList = new ArrayList<>();

        if (digit <= 1 || digit > 12) {
            return returnList;
        }

        List<Integer> processed = new ArrayList<>();
        processed.add(0);

        helperDiceCombinationToGetTheValueRecursionStreamLine(digit, 1, processed, returnList, true, false);
        return returnList;
    }
    static void helperDiceCombinationToGetTheValueRecursionStreamLine(int digit, int currentValue, List<Integer> processed, List<List<Integer>> returnList, boolean isFirst, boolean ignore ) {
        // Base condition
        if (currentValue > 6) {
            if (processed.size() > 1 && processed.get(0) + processed.get(1) == digit) {
                returnList.add(processed);
            }
            return;
        }

        // Another base condition
        if (processed.size() > 1) {
            if (processed.get(0) + processed.get(1) == digit) {
                returnList.add(processed);
            }
            return;
        }

        // Function body
        ArrayList<Integer> temp1 = new ArrayList<>(processed);
        if (processed.get(0) == 0) {
            temp1.set(0,currentValue);
        }
        else {
            temp1.add(currentValue);
        }

        if (isFirst) {
            // Ignore it
            helperDiceCombinationToGetTheValueRecursionStreamLine(digit, currentValue, processed, returnList, false, true);
        } else {
            currentValue++;
            // Ignore it
            helperDiceCombinationToGetTheValueRecursionStreamLine(digit, currentValue, processed, returnList, true, false);
        }

        // If ignore, don't do the addition step
        if (ignore) {
            return;
        }

        // Add step
        helperDiceCombinationToGetTheValueRecursionStreamLine(digit, currentValue, temp1, returnList, false, false);
    }
    static void helperDiceCombinationToGetTheValueRecursion(int digit, int currentValue,List<Integer> processed, List<List<Integer>> returnList,boolean isFirst, boolean ignore ) {
        // Base condition
        if (currentValue > 6) {
            if (processed.size() > 1 && processed.get(0) + processed.get(1) == digit) {
                returnList.add(processed);
                return;
            }
            return;
        }

        // Another base condition
        if (processed.size() > 1) {
            if (processed.get(0) + processed.get(1) == digit) {
                returnList.add(processed);
                return;
            } else {
                return;
            }
        }

        // Function body
        if (isFirst) {
            if (processed.get(0) == 0) {
                // Add it
                ArrayList<Integer> temp1 = new ArrayList<>(processed);
                temp1.set(0,currentValue);
                helperDiceCombinationToGetTheValueRecursion(digit, currentValue, temp1, returnList, false, false);

                // Ignore it
                helperDiceCombinationToGetTheValueRecursion(digit, currentValue, processed, returnList, false, true);
                return;
            }


            // Ignore it
            helperDiceCombinationToGetTheValueRecursion(digit, currentValue, processed, returnList, false, true);

            // Add it
            ArrayList<Integer> temp1 = new ArrayList<>(processed);
            temp1.add(currentValue);
            helperDiceCombinationToGetTheValueRecursion(digit, currentValue, temp1, returnList, false, false);
        } else {
            if (processed.get(0) == 0) {
                // Ignore it
                helperDiceCombinationToGetTheValueRecursion(digit, currentValue+1, processed, returnList, true, false);

                if (ignore) {
                    return;
                }

                // Add it
                ArrayList<Integer> temp1 = new ArrayList<>(processed);
                temp1.set(0,currentValue);
                helperDiceCombinationToGetTheValueRecursion(digit, currentValue+1, temp1, returnList, true, false);
                return;
            }

            // Ignore it
            helperDiceCombinationToGetTheValueRecursion(digit, currentValue+1, processed, returnList, true, false);

            if (ignore) {
                return;
            }

            // Add it
            ArrayList<Integer> temp1 = new ArrayList<>(processed);
            temp1.add(currentValue);
            helperDiceCombinationToGetTheValueRecursion(digit, currentValue+1, temp1, returnList, true, false);
        }


    }
    static void helperDiceCombinationToGetTheValueIteration(int digit, List<List<Integer>> returnList) {
        int[] dieFaces = {1,2,3,4,5,6};

        int start = 0;
        int end = 6;

        for (int i = start; i < end; i++) {
            for (int j = i; j < end; j++) {

                // Making the process efficient
                if (dieFaces[i] + dieFaces[j] > digit) {
                    break;
                }

                // Checking if equal and adding
                if (dieFaces[i] + dieFaces[j] == digit) {
                    List<Integer> tempList = new ArrayList<>(2);
                    tempList.add(dieFaces[i]);
                    tempList.add(dieFaces[j]);
                    returnList.add(tempList);
                }
            }
        }
    }

    // Google problem
    static List<String> letterCombinations(String digits) {
        List<String> returnList = new ArrayList<>();
        String[] letters = {" ", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        if (digits.isEmpty()) {
            return returnList;
        }

        helperLetterCombinations(digits, "", letters,  returnList);
        return returnList;
    }
    static void helperLetterCombinations(String digits, String processed, String[] letters, List<String> returnList) {
        // Base condition
        if (digits.isEmpty()) {
            returnList.add(processed);
            return;
        }

        int currentDigit = digits.charAt(0) - '0';

        for (int i = 0; i < letters[currentDigit].length(); i++) {
            String currentString = letters[currentDigit];
            helperLetterCombinations(
                    digits.substring(1),
                    processed + currentString.charAt(i),
                    letters, returnList
            );
        }

    }

    // I] COMBINATION PROBLEMS
    // 1) Find all combinations -> Array
    static List<List<Integer>> findAllCombinationsArray(int[] original) {
        List<List<Integer>> returnList = new ArrayList<>(original.length*2);

        // Adding empty list
        List<Integer> emptyList = new ArrayList<Integer>();

        helperFindAllCombinationsArrayDuplicate(original, emptyList, returnList, 0, false);
        return returnList;
    }
    static void helperFindAllCombinationsArray(int[] original, List<Integer> processed, int currentIndex, List<List<Integer>> returnList) {
        // Base condition
        if (currentIndex == original.length) {
            returnList.add(processed);
            return;
        }

        //Add it
        List<Integer> argList = new ArrayList<>(processed);
        argList.add(original[currentIndex]);
        helperFindAllCombinationsArray(original, argList, currentIndex+1, returnList);


        // Ignore it
        List<Integer> argList2 = new ArrayList<>(processed);
        helperFindAllCombinationsArray(original, argList2, currentIndex+1, returnList);
    }
    // 2) Find all combinations -> String
    static List<String> findAllCombinationsString(String original) {
        List<String> returnList = new ArrayList<>(original.length() * 2);
        helperFindAllCombinationsString(original, "", returnList);
        helperFindAllCombinationsStringDuplicate(original, "" , returnList, false);
        return returnList;
    }
    static void helperFindAllCombinationsString(String original, String processed, List<String> returnList) {
        // Base condition
        if (original.isEmpty()) {
            returnList.add(processed);
            return;
        }
        // Ignore
        helperFindAllCombinationsString(original.substring(1), processed, returnList);

        // Add
        helperFindAllCombinationsString(original.substring(1), processed + original.charAt(0), returnList);
    }
    // 3) Find all combination with duplicates -> String
    static void helperFindAllCombinationsStringDuplicate(String original, String processed, List<String> returnList, boolean duplicate) {
        // Base condition
        if (original.isEmpty()) {
            returnList.add(processed);
            return;
        }

        // If it is a duplicate, don't do the addition step
        if (duplicate) {
            // Ignore
            helperFindAllCombinationsStringDuplicate(original.substring(1), processed, returnList, false);
            return;
        }


        // If duplicate, add true to only ignore step
        helperFindAllCombinationsStringDuplicate(
                original.substring(1),
                processed, returnList,
                original.length() > 1 && original.charAt(0) == original.charAt(1)
        );
        // Add
        helperFindAllCombinationsStringDuplicate(original.substring(1), processed + original.charAt(0), returnList, false);
    }
    // 4) Find all combination with duplicates -> Array
    static void helperFindAllCombinationsArrayDuplicate(int[] original, List<Integer> processed, List<List<Integer>> returnList, int currentIndex, boolean duplicate) {
        // Base condition
        if (currentIndex == original.length) {
            returnList.add(processed);
            return;
        }

        List<Integer> temp1 = new ArrayList<>(processed);
        boolean nextDuplicate = currentIndex < original.length - 1 && original[currentIndex] == original[currentIndex+1];

        if (duplicate) {
            // Skip adding phase -> only ignore phase
            helperFindAllCombinationsArrayDuplicate(original, temp1, returnList, currentIndex+1, nextDuplicate);
            return;
        }

        // Ignore
        helperFindAllCombinationsArrayDuplicate(original, temp1, returnList, currentIndex+1, nextDuplicate);

        // Add
        List<Integer> temp2 = new ArrayList<>(processed);
        temp2.add(original[currentIndex]);
        helperFindAllCombinationsArrayDuplicate(original, temp2, returnList, currentIndex+1, false);

    }
    // 5) Find all combination -> String -> iteration
    static List<String> findAllCombinationsStringIteration(String original) {
        String processed = "";
        List<String> returnList = new ArrayList<>();
        returnList.add(" ");
        for (int i = 0; i < original.length(); i++) {
            for(int j = 0; j < processed.length(); j++) {
                // Add it
                processed = processed + original.charAt(i);
                // Ignore it
            }
            returnList.add(processed);
        }
        return returnList;
    }
    // 6) Find all combination -> Array -> iteration
    // 7) Find all combination with duplicates -> String -> iteration
    // 8) FInd all combination with duplicates -> array -> iteration


    // II] Permutation problems
    // 1) Find all permutations -> String

    // 2) Find all permutations -> Array
    static List<List<Integer>> findAllPermutations(int[] original) {
        List<List<Integer>> returnList = new ArrayList<>(original.length * 2);
        helperFindAllPermutationsArrayNoReturn(original, new ArrayList<>(), 0, returnList);
        return returnList;
    }
    static List<List<Integer>> helperFindAllPermutationsArrayReturn(int[] original, List<Integer> processed, int current) {
        List<List<Integer>> returnList = new ArrayList<>();

        // Base condition
        if (current == original.length) {
            returnList.add(processed);
            return returnList;
        }

        for (int i = 0; i <= processed.size(); i++) {
            int sizeOfThatArray = processed.size();

            // Start
            List<Integer> argList = new ArrayList<>((processed.subList(0, i)));

            // Middle
            argList.add(original[current]);

            //End
            argList.addAll(processed.subList(i,sizeOfThatArray));

            // Call next
            returnList.addAll(helperFindAllPermutationsArrayReturn(original, argList, current+1 ));
        }
        return returnList;


    }
    static void helperFindAllPermutationsArrayNoReturn(int[] original, List<Integer> processed, int current, List<List<Integer>> returnList) {
        // Base condition
        if (current == original.length) {
            returnList.add(processed);
            return;
        }

        for (int i = 0; i <= processed.size(); i++) {
            int sizeOfThatArray = processed.size();

            // Start
            List<Integer> argList = new ArrayList<>((processed.subList(0, i)));

            // Middle
            argList.add(original[current]);

            //End
            argList.addAll(processed.subList(i,sizeOfThatArray));

            // Call next
            helperFindAllPermutationsArrayNoReturn(original, argList, current+1, returnList);
        }
    }
    static void findAllPermutationsString(String st){
        helperFindAllPermutationsString(st.substring(1), st.substring(0,1));
    }
    static void helperFindAllPermutationsString(String original, String processed) {
        // Base condition
        if (original.isEmpty()) {
            System.out.println(processed);
            return;
        }

        for (int i = 0; i <= processed.length(); i++) {
            String firstHalf = processed.substring(0, i);
            String secondHalf = processed.substring(i);

            helperFindAllPermutationsString(
                    original.substring(1),
                    firstHalf + original.charAt(0) + secondHalf);
        }
    }

    // Combinations
    static List<List<Integer>> allSubsetsIterationWithDuplicateElements(int[] original) {
        List<List<Integer>> processed = new ArrayList<>();
        processed.add(new ArrayList<>());

        int start = 0;
        for (int i = 0; i < original.length; i++) {

            int sizeOfProcessed = processed.size();
            // If it is not the first turn, create new array with each combination
            for (int j = start; j < sizeOfProcessed; j++) {
                List<Integer> temp = new ArrayList<>(processed.get(j));
                temp.add(original[i]);
                processed.add(temp);
            }

            if (i < original.length - 1 && original[i] == original[i+1]) {
                start = processed.size() - sizeOfProcessed;
            } else {
                start = 0;
            }

        }

        return processed;
    }
    static List<List<Integer>> allSubsetsIterationWithDuplicateElementsCheating(int[] original) {
        List<List<Integer>> processed = new ArrayList<>();
        processed.add(new ArrayList<>());


        for(int i: original) {
            // Initial processed size
            int sizeOfProcessed = processed.size();

            // If it is not the first turn, create new array with each combination
            for (int j = 0; j < sizeOfProcessed; j++) {
                List<Integer> temp = new ArrayList<>(processed.get(j));
                temp.add(i);
                if (processed.contains(temp)) {
                    continue;
                }
                processed.add(temp);
            }
        }

        return processed;
    }
    static List<List<Integer>>  allSubsetsIteration(int[] original) {
        List<List<Integer>> processed = new ArrayList<>(original.length * 2);
        // Add empty list
        processed.add(new ArrayList<>());

        for(int i: original) {
            // Initial processed size
            int sizeOfProcessed = processed.size();

            // If it is not the first turn, create new array with each combination
            for (int j = 0; j < sizeOfProcessed; j++) {
                List<Integer> temp = new ArrayList<>(processed.get(j));
                temp.add(i);
                processed.add(temp);
            }
        }

        return processed;
    }
    static List<String> allSubsetsOfString(String original) {
        List<String> stList = new ArrayList<>(original.length() * 2);
        helperPrintAllSubsetsAlongWithAscii(stList, original, "");
        return stList;
    }
    static void helperPrintAllSubsetsAlongWithAscii(List<String> stList, String original, String processed) {
        // Base condition
        if (original.isEmpty()) {
            if (processed.isEmpty()) {
                return;
            }
            stList.add(processed);
            return;
        }
        // Add
        helperPrintAllSubsetsAlongWithAscii(stList, original.substring(1), processed + original.charAt(0));

        // Add the ascii value
        helperPrintAllSubsetsAlongWithAscii(stList, original.substring(1), (processed +  (int)original.charAt(0) ) );

        // Ignore
        helperPrintAllSubsetsAlongWithAscii(stList, original.substring(1), processed);
    }
    static void helperPrintAllSubsetsOfString(List<String> stList, String original, String processed) {
        // Base condition
        if (original.isEmpty()) {
            if (processed.isEmpty()) {
                return;
            }
            stList.add(processed);
            return;
        }
        // Add
        helperPrintAllSubsetsOfString(stList, original.substring(1), processed + original.charAt(0));

        // Ignore
        helperPrintAllSubsetsOfString(stList, original.substring(1), processed);
    }


    // Basic strings
    static String removeTargetStringInString(String original, String target) {
        // Base condition
        if (original.isEmpty()) {
            return "";
        }

        // Obtain the next word
        int blankIndex = original.indexOf(' ');
        // Base condition
        if (blankIndex == -1) {
            if (original.equals(target) ) {
                return "";
            }
            return original;

        }

        String currentString = original.substring(0, blankIndex);
        if (currentString.equals(target) ) {
            return removeTargetStringInString(original.substring(blankIndex+1), target);
        } else {
            return currentString + " " + removeTargetStringInString(original.substring(blankIndex+1), target);
        }



    }
    static String removeTargetCharInStringReturn(String original , char target) {
        // Base condition
        if (original.isEmpty()) {
            return "";
        }

        if (original.charAt(0) == target) {
            return removeTargetCharInStringReturn(original.substring(1) , target);
        } else {
            return original.charAt(0) + removeTargetCharInStringReturn(original.substring(1) , target);
        }
    }
    static void removeTargetElementInStringPrint(String original, char target) {
        String processed = "";

        helperRemoveTargetElementInStringPrint(original, processed, target);

    }
    static void helperRemoveTargetElementInStringPrint(String original, String processed, char target) {
        if (original.isEmpty()) {
            System.out.println(processed);
            return;
        }
        if (original.charAt(0) != target) {
            processed += original.charAt(0);
        }
        helperRemoveTargetElementInStringPrint(original.substring(1), processed, target);
    }

    // Selection sort
    static void selectionSort(int[] nums) {
        //helperSelectionSort(nums, nums.length -1);
        helperSelectionSortIntegrated(nums, 1, nums.length -1, 0);
    }
    static void helperSelectionSortIntegrated(int[] nums, int n, int end, int largest) {
        // Base condition for outer
        if (end <= 0) {
            return;
        }

        // Base condition for inner
        if (n > end) {
            // Swap
            helperSwap(nums, largest, end);
            helperSelectionSortIntegrated(nums, 1, --end, 0);
            return;
        }

        if (nums[n] > nums[largest]) {
            largest = n;
            helperSelectionSortIntegrated(nums, ++n, end, largest);
        } else {
            helperSelectionSortIntegrated(nums, ++n, end, largest);
        }
    }
    static void helperSelectionSort(int[] nums, int end) {
        // Base condition
        if (end <= 0) {
            return;
        }

        // 1 -> find the largest element
        int largestElement = helperFindLargest(nums,1, end, 0);
        // 2 -> sort it in right place
        helperSwap(nums, largestElement, end);

        // Iteration
        helperSelectionSort(nums, --end);
    }
    static int helperFindLargest(int[] nums, int n, int end, int largest) {
        if (n > end) {
            return largest;
        }

        if (nums[n] > nums[largest]) {
            largest = n;
            return helperFindLargest(nums, ++n, end, largest);
        } else {
            return helperFindLargest(nums, ++n, end, largest);
        }
    }


    // Bubble sort
    static void bubbleSort(int[] nums) {
        helperBubbleSort(nums, 0, 0, nums.length - 1);
    }
    static void helperBubbleSort(int[] nums, int n, int swaps, int end) {
        // Base condition for one row
        if (n >= end) {
            if (swaps > 1) {
                helperBubbleSort(nums, 0, 0, --end);
                return;
            } else {
                return;
            }
        }

        // Swap if smaller, if not smaller, move on to the next num
        if (nums[n] > nums[n+1]) {
            helperSwap(nums, n, n+1);
            helperBubbleSort(nums, ++n, ++swaps, end);
        } else {
            helperBubbleSort(nums, ++n, swaps, end);
        }
    }
    static  void helperSwap(int[] nums, int indexA, int indexB) {
        int temp = nums[indexA];
        nums[indexA] = nums[indexB];
        nums[indexB] = temp;
    }


    // Pattern problems
    static void printStars(int n) {
        helperPrintStarsReversePure(n, 1);
    }
    static void helperPrintStars(int row, int column) {
        if (row == 0) {
            return;
        }
        if (column == row) {
            System.out.println();
            helperPrintStars(--row, 1);
        } else {
            helperPrintStars(row, ++column);
        }
    }
    static void helperPrintStarsReversePure(int row, int column) {
        if ( row == 0) {
            return;
        }
        if (column <= row) {
            helperPrintStarsReversePure(row, ++column);
            System.out.print("*");
        } else {
            helperPrintStarsReversePure(--row, 1);
            System.out.println();
        }
    }
    static void helperPrintStarsReverse(int row, int column, int n) {
        if (row == n+1) {
            return;
        }
        if (row < column) {
            System.out.println();
            helperPrintStarsReverse(++row, 1, n);
        } else {
            System.out.print("*");
            helperPrintStarsReverse(row, ++column, n);

        }

    }
    static void printStarsPartial(int n) {
        if (n == 0) {
            return;
        }
        printStars(n-1);
        for (int i = 1; i <= n; i++) {
            System.out.print("*");
        }
        System.out.println();
    }


    // Array problems
    static int FindInRotatedArrayWithoutPivot(int[] nums, int target) {
        int end = nums.length -1 ;
        // Check if no pivot -> normal binary search
        if (nums[0] < nums[end/2] && nums[end/2] < nums[end]) {
            return binarySearchViaRecursion(nums,0,end,target);
        }

        // If there is a pivot, then this
        return helperFindInRotatedArrayWithoutPivot(nums, target, 0, nums.length-1);

    }
    static int helperFindInRotatedArrayWithoutPivot(int[] nums, int target, int start, int end) {
        // base condition
        if (start > end) {
            return -1;
        }
        int m = (start + end) /2 ;

        // check if middle is the target
        if (nums[m] == target) {
            return m;
        }

        // Left side is sorted
        if (nums[start] < nums[m]) {
            // Target is within m till end
            if (target < nums[m] && target < nums[start]) {
                return  helperFindInRotatedArrayWithoutPivot(nums, target, m+1, end);
            }

            // Target is within m till end
            if (target < nums[m] && target > nums[start]) {
                return helperFindInRotatedArrayWithoutPivot(nums, target, start, m-1);
            }

            // If target is the start
            return helperFindInRotatedArrayWithoutPivot(nums, target, start, m-1);
        }

        // Check if the start is larger -> this means
        // everything to the right is sorted
        // the pivot is to the left
        if (nums[start] > nums[m]) {

            // If the target is greater than middle and the start,
            // it's in the left
            if (target > nums[m] && target > nums[start]) {
                return  helperFindInRotatedArrayWithoutPivot(nums, target, start, m-1);
            }

            // If the target is larger than the middle but smaller than start
            // It's in the right
            if (target > nums[m] && target < nums[start]) {
                return  helperFindInRotatedArrayWithoutPivot(nums, target, m+1, end);
            }

            // If the target is larger than start and larger than middle
            // It's in the left
            if (target > nums[m] && target > nums[start]) {
                return  helperFindInRotatedArrayWithoutPivot(nums, target, start, m-1);

            }
            // Element is smaller than middle and start
            if (target < nums[start] && target < nums[m]) {
                return  helperFindInRotatedArrayWithoutPivot(nums, target, start, m-1);

            }
        }



        // Pivot in in the right


        // Check if pivot is in the right

        // Check if target lies in the left of middle
        if (nums[0] >= nums[m] && target > nums[0]) {
            return helperFindInRotatedArrayWithoutPivot(nums, target, start, m-1);
        }
        if (nums[0] <= nums[m] && target < nums[m]) {
            return  helperFindInRotatedArrayWithoutPivot(nums, target, m+1, end);
        }

        // Check if target lies in the right of middle
        if (nums[0] <= nums[m] && target > nums[m]) {
            return  helperFindInRotatedArrayWithoutPivot(nums, target, m+1, end);
        }

        return -1;



    }
    static int findInRotatedArray(int[] nums, int target) {
        // 1) find pivot
        int pivot = findPivotInRotateArray(nums);

        // 2) Check if pivot is the target
        if (nums[pivot] == target) {
            return pivot;
        }

        // 3) If non-rotated array
        if (pivot == nums.length - 1) {
            return binarySearchViaRecursion(nums, 0 , pivot, target);
        }

        // 4) If a target out of bounds is given
        if (target > nums[pivot] || target < nums[pivot+1]) {
            return -1;
        }

        // 5) If rotated array
        return target < nums[0]
                ?binarySearchViaRecursion(nums, pivot+1, nums.length-1, target)
                :binarySearchViaRecursion(nums, 0, pivot -1, target);
    }
    static int findPivotInRotateArray(int[] nums) {
        int end = nums.length-1;
        // If no pivot
        if (nums[0] < nums[end/2] && nums[end/2] < nums[end]) {
            return end;
        }

        return helperFindPivotInRotateArray(nums, 0, end);

    }
    static int helperFindPivotInRotateArray(int[] nums, int start, int end) {

        // Base condition
        if (start >= end) {
            return start;
        }

        int m = (start + end) / 2;

        // If pivot is found
        if (nums[m] > nums[m+1]) {
            return m;
        } else if (nums[m-1] > nums[m]) {
            return  m-1;
        }

        // If no pivot
        if (nums[m] < nums[start]) {
            return helperFindPivotInRotateArray(nums, start, m-2);
        } else {
            return helperFindPivotInRotateArray(nums, m+1, end);
        }
    }
    static List<Integer> linearSearchMultipleIndex(int[] nums, int num) {
        List<Integer> list = new ArrayList<>(1);
        helperLinearSearchMultipleIndex(nums, num, 0, list);
        return list;
    }
    static void helperLinearSearchMultipleIndex(int[] nums, int num, int n, List<Integer> list) {
        if (nums[n] == num) list.add(n);

        if (n == nums.length - 1) {
            return;
        }
        helperLinearSearchMultipleIndex(nums, num, n+1, list);
    }
    static boolean linearSearchBool(int[] nums, int num) {
        return helperLinearSearchBool(nums, num, 0);
    }
    static boolean helperLinearSearchBool(int[] nums, int num, int n) {
        if (n == nums.length -1 ) {
            return (nums[n] == num);
        }

        // Either this num must be num or the next should be n
        return nums[n] == num || helperLinearSearchBool(nums, num, n+1);

    }
    static int linearSearchIndex(int[] nums, int num) {
        return helperLinearSearchIndex(nums, num, 0);
    }
    static int helperLinearSearchIndex(int[] nums, int num, int n) {
        if (n == nums.length -1 ) {
            return (nums[n] == num)?n:-1;
        }

        // If it is the num, return the index, if not, return the next result
        return (nums[n] == num)?n:helperLinearSearchIndex(nums, num, n+1);
    }
    static boolean findIfArrayIsSorted(int[] n) {
        return helperFindIfArrayIsSortedLinear(n, 0);
    }
    static  boolean helperFindIfArrayIsSortedLinear(int[] arr, int n) {
        // Base condition
        if (n == arr.length - 1) {
            return true;
        }
        if (arr[n] <= arr[n+1]) {
            return helperFindIfArrayIsSortedLinear(arr, n+1);
        }
        return false;
    }
    // Flopped problem
    static boolean helperFindIfArrayIsSortedBinary(int[] n, int start, int end) {
        int m = (start + end) / 2;
        // Base Condition
        if (start >= end) {
            return true;
        }

        if (n[start] <= n[m] && n[m] <= n[end] ) {
            return helperFindIfArrayIsSortedBinary(n,start+1, end-1 );
        }
        else {
            return false;
        }
    }


    // Basic easy questions
    static int numberOfStepsToReduceTo0(int n) {
        // Base condition
        if (n == 0) {
            return 0;
        }
        if (n % 2 == 0) {
            return 1 + numberOfStepsToReduceTo0(n/2);
        }
        else return 1+ numberOfStepsToReduceTo0(n-1);
    }
    static int numberOfZeros(int number) {
        // Base condition
        if (number < 10) {
            return number == 0?1:0;
        }

        if (number % 10 == 0) {
            return 1 + numberOfZeros(number/10);
        }
        return numberOfZeros(number/10);
    }
    static boolean palindromeNumber(int number) {
        return  (number == reverseANumber(number));
    }
    static int reverseANumber(int number) {
        // Base condition
        if (number < 10) {
            return number;
        }

        // Finding the position of the number
        int digits =  (int)Math.log10(number);
        int position = (int) Math.pow(10, digits);

        // Obtaining the last number
        int lastDigit = number % 10;
        int newNumber = lastDigit * position;

        // Returning
        return (newNumber + reverseANumber(number/10));
    }
    static int productOfDigitsOfNum(int n) {
        if (n / 10 < 1) {
            return n;
        }
        return (n % 10) * productOfDigitsOfNum(n/10);
    }
    static int sumOfDigitsOfNum(int n) {
        if (n / 10 < 1) {
            return n;
        }
        return (n % 10) + sumOfDigitsOfNum(n/10);
    }
    static int factorialMan(int n) {
        if (n <= 2) {
            return n;
        }
        return n * factorialMan(n-1);
    }
    static int binarySearchViaRecursion(int[] nums, int start, int end, int target) {
        // Base condition -> target is not in the array
        if (start > end) {
            return -1;
        }

        int n = ( start + end ) / 2;
        int currentValue = nums[n];

        if (currentValue == target) {
            // Base condition -> target is found
            return n;
        }
        else if (currentValue > target) {
            return binarySearchViaRecursion(nums, start, n-1, target);
        }
        else  {
            return binarySearchViaRecursion(nums, n+1, end, target);
        }
    }
    static int fibonacciNumber(int n) {
        if (n == 1) {
            return 1;
        }

        if (n <= 0) {
            return n;
        }
        return (fibonacciNumber(n-1) + fibonacciNumber(n-2));
    }
    static void printNumbers(int n) {
        if (n== 0) {
            return;
        }
        printNumbers(n-1);
        System.out.println(n);
    }
    static  void printHelloWorld(int n) {
        if (n == 1) {
            System.out.println("Hello world");
            return;
        }
        System.out.println("Hello world");
        printHelloWorld(n-1);
    }
}