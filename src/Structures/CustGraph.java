package Structures;

import java.util.ArrayList;

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

    public Object[] getEdges(int p) {
        return graphs.get(p).toArray();
    }

}
