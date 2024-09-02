package Structures;

import java.util.ArrayList;
import java.util.List;

public class CustGraph {
    private ArrayList<ArrayList<Integer>> graphs;

    CustGraph(int n) {
        graphs = new ArrayList<>(n+1);

        // Populating
        for (int i = 0; i < n+1; i++) {
            graphs.add(new ArrayList<>());
        }
    }

    public void connect(int p, int q) {
        graphs.get(p).add(q);
        graphs.get(q).add(p);
    }

    public int[] getEdges(int p) {
        // Assuming graphs is an ArrayList<ArrayList<Integer>>
        Integer[] tempArray = new Integer[graphs.get(p).size()];
        tempArray = graphs.get(p).toArray(tempArray);

        // Convert Integer[] to int[]
        int[] returnArray = new int[tempArray.length];
        for (int i = 0; i < tempArray.length; i++) {
            returnArray[i] = tempArray[i];
        }

        return returnArray;
    }

    public int size() {
        return graphs.size();
    }

}
