package Structures;

import java.util.Arrays;

public class GraphProblems {
    public static void main(String[] args) {
        CustGraph graph = new CustGraph(5);
        graph.connect(0,3);
        graph.connect(3,2);
        System.out.println(Arrays.toString(graph.getEdges(3)));
    }



}
