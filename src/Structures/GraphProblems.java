package Structures;

import java.lang.reflect.Array;
import java.util.*;

public class GraphProblems {
    public static void main(String[] args) {
        CustGraph graph = new CustGraph(5);
        graph.connect(1,5);
        graph.connect(5,2);
        graph.connect(5,3);
        graph.connect(3,4);

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        int[][] array =  {
                {1 ,0, 0, 0 ,0},
                {0, 1, 0, 0 ,0},
                {0 ,0 ,1 ,0 ,1},
                {0 ,0, 0, 1 ,0},
                {0, 0, 1, 0 ,1}
        };

        // Iterate through each row of the 2D int array
        for (int i = 0; i < array.length; i++) {
            ArrayList<Integer> rowList = new ArrayList<>();

            // Iterate through each element in the row
            for (int j = 0; j < array[i].length; j++) {
                rowList.add(array[i][j]);
            }
            list.add(rowList);
        }

        // Add the row ArrayList to the main ArrayList

        System.out.println(numProvinces( list, 5));


    }

    // 1) Number of provinces
    // Medium
    // https://leetcode.com/problems/number-of-provinces/

    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {
        int provinces = 0;
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                visited[i] = true;
                provinces++;
                dfsOfGraph(i, visited, adj);
            }
        }
        return provinces;
    }


    // Depth First Search
    public static void dfsOfGraph(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        int size = adj.getFirst().size();
        for(int i = 0; i < size; i++) {
            if (adj.get(node).get(i) == 1 && !visited[i]) {
                visited[i] = true;
                dfsOfGraph(i, visited, adj);
            }
        }
    }


    // Breath First Search
    public static ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> result = new ArrayList<>(adj.size());
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;


        while(!queue.isEmpty()) {
            int node = queue.poll();

            // Add it to the result
            result.add(node);

            // Add its children to the queue
            for(int n: adj.get(node)) {
                if (!visited[n]) {
                    visited[node] = true;
                    queue.offer(n);
                }
            }
        }

        return result;
    }









}
