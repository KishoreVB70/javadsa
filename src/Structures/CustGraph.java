package Structures;

import java.util.ArrayList;

public class CustGraph {
    ArrayList<ArrayList<Integer>> list;
    CustGraph(int n) {
        list = new ArrayList<>(n+1);
    }

    public void connect(int p, int q) {
        list.get(p).add(q);
        list.get(q).add(p);
    }

    public Object[] getEdges(int p) {
        return list.get(p).toArray();
    }
}
