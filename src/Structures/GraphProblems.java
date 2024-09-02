package Structures;

import java.util.*;

public class GraphProblems {
    public static void main(String[] args) {
        CustGraph graph = new CustGraph(5);
        graph.connect(1,5);
        graph.connect(5,2);
        graph.connect(5,3);
        graph.connect(3,4);
        depthFirstSearch(graph);
    }

    public static void breathFirstSearch(CustGraph graph) {
        Set<Integer> visited = new HashSet<>(graph.size());
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < graph.size(); i++) {
            visitNodeBFS(i, visited, queue, graph);

            while (!queue.isEmpty()) {
                int queueSize = queue.size();

                for (int j = 0; j < queueSize ; j++) {
                    int res = queue.poll();
                    visitNodeBFS(res, visited, queue, graph);
                }
            }
        }
    }

    public static void visitNodeBFS(int node, Set<Integer> visited, Queue<Integer> queue, CustGraph graph) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);

        System.out.println(node);


        for(int n: graph.getEdges(node)) {
            queue.add(n);
        }
    }

     public static void depthFirstSearch(CustGraph graph) {
        boolean[] visited = new boolean[graph.size()];

        // Outer loop for different connected components
        for (int i = 1; i < graph.size(); i++) {
            if (!visited[i]) {
                visitNodeDFS(i, graph, visited);
            }
        }
    }

    public static void visitNodeDFS(int node, CustGraph graph, boolean[] visited) {
        visited[node] = true;

        System.out.println(node);

        for(int n: graph.getEdges(node)) {
            // Base condition -> Don't visit
            if (!visited[n]) {
                visitNodeDFS(n, graph, visited);
            }
        }
    }



}
