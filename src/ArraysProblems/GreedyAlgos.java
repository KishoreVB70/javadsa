package ArraysProblems;

import java.util.Arrays;

public class GreedyAlgos {
    public static void main(String[] args) {
//        System.out.println(matchPlayersAndTrainers());
    }


    // 1) Match players and trainers
    // Medium https://leetcode.com/problems/maximum-matching-of-players-with-trainers/
    public static int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);

        int satisfied = 0;
        int i = 0;
        int j = 0;

        while(i < players.length && j < trainers.length) {
            // Sastisfaction
            if(trainers[j] >= players[i]) {
                satisfied++;
                i++;
            }
            // Not satisfied -> only j moves ahead
            j++;
        }

        return satisfied;
    }
}
