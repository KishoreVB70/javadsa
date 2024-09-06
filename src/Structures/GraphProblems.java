package Structures;

import java.util.*;

public class GraphProblems {
    public static void main(String[] args) {

    }

    // 8)

    // 7) Number of enclaves -> Same as O to X
    // Medium https://leetcode.com/problems/number-of-enclaves/
    public int numEnclaves(int[][] grid) {
        int result = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        // Top and bottom corner
        for(int i = 0; i < grid.length; i++) {
            int size = grid[0].length -1;
            if (grid[i][0] == 1 && !visited[i][0]) {
                dfsEnclave(i, 0, grid, visited);
            }
            if (grid[i][size] == 1 && !visited[i][size]) {
                dfsEnclave(i, size, grid, visited);
            }
        }



        // Left and Right corner
        for(int j = 0; j < grid[0].length; j++) {
            int size = grid.length - 1;
            if (grid[0][j] == 1 & !visited[0][j] ){
                dfsEnclave(0, j, grid, visited);
            }

            if (grid[size][j] == 1 && !visited[size][j] ) {
                dfsEnclave(size, j, grid, visited);
            }
        }

        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }
    public void dfsEnclave(int i, int j, int[][] board, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 0 || visited[i][j]) {
            return;
        }

        visited[i][j] = true;

        dfsEnclave(i-1, j, board, visited);
        dfsEnclave(i, j-1, board, visited);
        dfsEnclave(i+1, j, board, visited);
        dfsEnclave(i, j+1, board, visited);
    }

    // 6) Convert O into X
    // Medium https://leetcode.com/problems/surrounded-regions/
    public  void solve(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];

        // Top and bottom corner
        for(int i = 0; i < board.length; i++) {
            int size = board[0].length -1;
            if (board[i][0] == 'O' && !visited[i][0]) {
                dfsXO(i, 0, board, visited);
            }
            if (board[i][size] == 'O' && !visited[i][size]) {
                dfsXO(i, size, board, visited);
            }
        }



        // Left and Right corner
        for(int j = 0; j < board[0].length; j++) {
            int size = board.length - 1;
            if (board[0][j] == 'O' & !visited[0][j] ){
                dfsXO(0, j, board, visited);
            }

            if (board[size][j] == 'O' && !visited[size][j] ) {
                dfsXO(size, j, board, visited);
            }
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }
    public void dfsXO(int i, int j, char[][] board, boolean[][] visited) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || visited[i][j]) {
            return;
        }

        visited[i][j] = true;

        dfsXO(i-1, j, board, visited);
        dfsXO(i, j-1, board, visited);
        dfsXO(i+1, j, board, visited);
        dfsXO(i, j+1, board, visited);
    }


    // Concept -> Finding cycle in graph
    public boolean isCycleBFS(ArrayList<ArrayList<Integer>> adj) {
        Queue<Pair> q = new LinkedList<>();
        boolean[] visited = new boolean[adj.size()];
        visited[0] = true;
        q.offer(new Pair(0,-1));
        while (!q.isEmpty()) {
            Pair pair = q.poll();
            ArrayList<Integer> lt =  adj.get( pair.r);
            for (int i: lt) {
                if (i != pair.c && visited[i]) {
                    return true;
                }
                // Not yet visited and not
                else if ( i != pair.c) {
                    q.offer(new Pair(i, pair.r));
                    visited[i] = true;
                }
            }
        }
        return false;
    }

    // 5) 0 1 Matrix
    // Medium https://leetcode.com/problems/01-matrix/
    public int[][] updateMatrix(int[][] mat) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[mat.length][mat[0].length];

        for(int i = 0; i < mat.length; i++) {
            for(int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    q.offer(new Pair(i, j));
                    visited[i][j] = true;
                }
            }
        }


        int level = -1;

        while(!q.isEmpty()) {
            level++;
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Pair pair = q.poll();
                int r = pair.r;
                int c = pair.c;
                mat[r][c] = level;


                // Up
                if (r -1 >= 0 && !visited[r-1][c]) {
                    visited[r-1][c] = true;
                    q.offer(new Pair(r-1, c));
                }

                // Down
                if (r+ 1 < mat.length && !visited[r+1][c]) {
                    visited[r+1][c] = true;
                    q.offer(new Pair(r+1, c));
                }

                // Right
                if (c+ 1 < mat[0].length && !visited[r][c+1]) {
                    visited[r][c+1] = true;
                    q.offer(new Pair(r, c+1));
                }

                // Left
                if (c - 1 >=0 && !visited[r][c-1]) {
                    visited[r][c-1] = true;
                    q.offer(new Pair(r, c-1));
                }
            }
        }
        return mat;
    }

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
