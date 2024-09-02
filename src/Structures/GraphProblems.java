package Structures;

import java.util.*;

public class GraphProblems {
    public static void main(String[] args) {
        CustGraph graph = new CustGraph(5);
        graph.connect(1,3);
        graph.connect(3,5);
        breathFirstSearch(graph);
    }

    public static void breathFirstSearch(CustGraph graph) {
        Set<Integer> visited = new HashSet<>(graph.size());
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < graph.size(); i++) {
            visitNode(i, visited, queue, graph);

            while (!queue.isEmpty()) {
                int queueSize = queue.size();

                for (int j = 0; j < queueSize ; j++) {
                    int res = queue.poll();
                    visitNode(res, visited, queue, graph);
                }
            }
        }
    }

    public static void visitNode(int node, Set<Integer> visited, Queue<Integer> queue, CustGraph graph) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);

        System.out.println(node);


        for(int n: graph.getEdges(node)) {
            queue.add(n);
        }
    }



}
