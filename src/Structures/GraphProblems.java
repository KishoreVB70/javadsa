package Structures;

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
                {2,2},
                {1,1},
                {0,0},
                {2,0}
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

//        System.out.println(numProvinces( list, 5));

        System.out.println(orangesRotting(array));

    }

    // Concept -> Finding cycle in graph

    // 4) Rotten oranges
    // Medium https://leetcode.com/problems/rotting-oranges/description/
    static class Pair {
        int r;
        int c;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static int orangesRotting(int[][] grid) {
        int steps = 0;
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new Pair(i, j));
                }
            }
        }

        boolean second = false;

        while(!q.isEmpty()) {
            if (second) steps++;
            second = true;

            int size = q.size();
            for(int i = 0; i < size; i++) {
                Pair pair = q.poll();
                int r = pair.r;
                int c = pair.c;


                // Up
                if (r - 1 >= 0 && grid[r-1][c] == 1) {
                    grid[r-1][c] = 2;
                    q.offer(new Pair(r-1,c));
                }
                // Down

                if (r + 1 < grid.length && grid[r+1][c] == 1) {
                    grid[r+1][c] = 2;
                    q.offer(new Pair(r+1,c));
                }


                // Right
                if (c + 1 < grid[0].length && grid[r][c+1] == 1) {
                    grid[r][c+1] = 2;
                    q.offer(new Pair(r,c+1));
                }

                // Left
                if (c - 1 >= 0 && grid[r][c-1] == 1) {
                    grid[r][c-1] = 2;
                    q.offer(new Pair(r,c-1));
                }
            }
        }

        // Looking for rotten oranges
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return -1;
                }
            }
        }

        return steps;
    }

    // 3) Flood fill
    // Easy https://leetcode.com/problems/flood-fill/
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        helperFloodFill(image, sr, sc, color, image[sr][sc]);
        return image;
    }
    public void helperFloodFill(int[][] image, int i, int j, int color, int tar) {
        if (i < 0 || j < 0 || i >= image.length || j >= image[0].length || image[i][j] != tar || image[i][j] == color) {
            return;
        }

        image[i][j] = color;

        // Up
        helperFloodFill(image, i+1, j, color, tar);
        helperFloodFill(image, i-1, j, color, tar);
        helperFloodFill(image, i, j+1, color, tar);
        helperFloodFill(image, i, j-1, color, tar);
    }

    // 2) Number of islands
    // Medium https://leetcode.com/problems/number-of-islands/
    public int numIslands(char[][] grid) {
        int islands = 0;

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    visitIslands(grid, i, j);
                }
            }
        }

        return islands;
    }
    private void visitIslands(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || j >= grid[0].length || i >= grid.length || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';

        // Up
        visitIslands(grid, i-1, j);

        // Down
        visitIslands(grid, i+1, j);

        // Right
        visitIslands(grid, i, j+1);

        // Left
        visitIslands(grid, i, j-1);
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
