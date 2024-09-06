package Structures;

import java.util.*;

public class GraphProblems {
    public static void main(String[] args) {
        CustGraph graph = new CustGraph(5);
        graph.connect(1,5);
        graph.connect(5,2);
        graph.connect(5,3);
        graph.connect(3,4);

        String input = "[\"O\",\"X\",\"O\",\"O\",\"O\",\"X\"],[\"O\",\"O\",\"X\",\"X\",\"X\",\"O\"],[\"X\",\"X\",\"X\",\"X\",\"X\",\"O\"],[\"O\",\"O\",\"O\",\"O\",\"X\",\"X\"],[\"X\",\"X\",\"O\",\"O\",\"X\",\"O\"],[\"O\",\"O\",\"X\",\"X\",\"X\",\"X\"]";
        String replacedString = input.replace("\"", "'")
                .replace("[", "{")
                .replace("]", "}");
        System.out.println(replacedString);
        char[][] array =  {
                {'O','X','O','O','O','X'},
                {'O','O','X','X','X','O'},
                {'X','X','X','X','X','O'},
                {'O','O','O','O','X','X'},
                {'X','X','O','O','X','O'},
                {'O','O','X','X','X','X'}
        };


        // Add the row ArrayList to the main ArrayList

//        System.out.println(numProvinces( list, 5));
        solve(array);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }

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

    // 6) Convert O into X
    public static void solve(char[][] board) {
        boolean[][] global = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !global[i][j]) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    int val = dfs(i, j, board, visited, global);
                    if (val == -1) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }

    public static int dfs(int i, int j, char[][] board, boolean[][] visited, boolean[][] globe) {
        if (i >= board.length || j >= board[0].length || i<0 || j<0 || globe[i][j]) {
            return 1;
        }
        if (board[i][j] == 'X' || visited[i][j]) {
            return -1;
        }


        visited[i][j] = true;
         int res = dfs(i-1, j, board, visited, globe);
        if (res == 1) {
            globe[i][j] = true;
            return 1;
        }

        res = dfs(i+1, j, board, visited, globe);
        if (res == 1) {
            globe[i][j] = true;
            return 1;
        }

        res = dfs(i, j-1, board, visited, globe);
        if (res == 1) {
            globe[i][j]  = true;
            return 1;
        }

        res = dfs(i, j+1, board, visited, globe);
        if (res == 1) {
            globe[i][j] = true;
            return 1;
        }

        board[i][j] = 'X';
        return -1;
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
