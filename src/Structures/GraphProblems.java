package Structures;

import java.lang.reflect.Array;
import java.util.*;

public class GraphProblems {
    public static void main(String[] args) {
        int[][] grid = {{1,0},{0,1}};
        System.out.println(largestIsland(grid));

    }
    static class Pair {
        int r;
        int c;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static class Tpair {
        int r;
        int c;
        int d;

        Tpair(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

    }
    static class Fpair{
        int r;
        int d;

        Fpair(int r, int d) {
            this.r =r;
            this.d = d;
        }
    }
    static class Cpair {
        int r;
        long d;

        Cpair(int r, long d) {
            this.r =r;
            this.d = d;
        }
    }
    static class Ppair{
        int n;
        int p;
        int w;

        Ppair(int n, int p, int w) {
            this.n = n;
            this.p = p;
            this.w = w;
        }
    }

    // 20) Largest island
    static class DisjointSet2D {
        int[][] rank;
        List<List<Pair>> parent;
        DisjointSet2D(int n) {
            // Rank would be 0 initially
            rank = new int[n][n];
            parent = new ArrayList<>();

            // Parent of itself
            for(int i = 0; i < n; i++) {
                List<Pair> lt = new ArrayList<>();
                for(int j = 0; j < n; j++) {
                    lt.add(new Pair(i, j));
                }
                parent.add(lt);
            }
        }

        public Pair root(int r, int c) {
            Pair par = parent.get(r).get(c);
            if(par.r == r && par.c == c) {
                return par;
            }

            par = root(par.r, par.c);
            parent.get(r).set(c, par);
            return par;
        }

        public void union(int r1, int r2, int c1, int c2) {
            Pair oneRoot = root(r1, c1);
            Pair twoRoot = root(r2, c2);

            // Same root
            if(oneRoot.r == twoRoot.r && twoRoot.c == oneRoot.c) {
                return;
            }

            if(rank[oneRoot.r][oneRoot.c] < rank[twoRoot.r][twoRoot.c]) {
                // 1) change parent
                parent.get(oneRoot.r).set(oneRoot.c, twoRoot);
                // 2 Change rank
                rank[twoRoot.r][twoRoot.c] += rank[oneRoot.r][oneRoot.c];

            } else {
                // 1) change parent
                parent.get(twoRoot.r).set(twoRoot.c, oneRoot);
                // 2 Change rank
                rank[oneRoot.r][oneRoot.c] += rank[twoRoot.r][twoRoot.c];
            }


        }
    }
    public static int largestIsland(int[][] grid) {
        // 1) Disjoint set
        int n = grid.length;
        DisjointSet2D dj = new DisjointSet2D(n);

        Queue<Pair> q = new LinkedList<>();

        // 1) Union of 1's
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 1) {
                    Pair root = dj.root(i, j);
                    if(root.r == i && root.c == j) {
                        dj.rank[i][j] = 1;
                        q.offer(root);
                    }

                    int pr = 0;
                    int pc = 0;
                    while(!q.isEmpty()) {
                        Pair pair = q.poll();
                        int r = pair.r;
                        int c = pair.c;

                        if(r < 0 || r >= n || c < 0 || c >= n || grid[r][c] == 0 ) {
                            continue;
                        }

                        // This is the first in the BFS
                        if(pr == 0 && pc  == 0) {
                            pr = r;
                            pc = c;
                        } else {
                            dj.rank[r][c] = 1;
                            dj.union(pr, pc, r, c);
                        }

                        int[] row = {1,0,-1,0};
                        int[] col = {0,1,0,-1};
                        for(int k = 0; k < 4; k++) {
                            q.offer(new Pair(r+ row[k], c+ col[k]));
                        }
                    }
                }
            }
        }

        // 2) Checking 0's
        int largest = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 0) {
                    // Check the four sides
                    int[] row = {1,0,-1,0};
                    int[] col = {0,1,0,-1};
                    Set<Pair> set = new HashSet<>();
                    for(int k = 0; k < 4; k++) {
                        if(i+ row[k] < 0 || i+ row[k] >= n || j+ col[k] < 0 || j+ col[k] >= n || grid[i+ row[k]][j+col[k]] == 0) {
                            continue;
                        }
                        set.add(dj.root(i+row[k], j+col[k]));
                    }

                    int rk = 1;
                    for(Pair p: set) {
                        rk += dj.rank[p.r][p.c];
                    }
                    largest = Integer.max(largest, rk);
                }
            }
        }

        return largest==0?dj.rank[0][0]:largest;
    }

    // 19) Accounts merge
    // Medium https://leetcode.com/problems/accounts-merge/
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 1) Hash map
        HashMap<String, Integer> map = new HashMap<>();

        // 2) Disjoint set
        DisjointSet dj = new DisjointSet(accounts.size());


        // 3) Hash map filling
        for(int i = 0; i < accounts.size(); i++) {
            for(int j = 1; j < accounts.get(i).size(); j++) {
                String st = accounts.get(i).get(j);

                if(map.containsKey(st)) {
                    int root = dj.findRoot(map.get(st));
                    dj.union(root, i);
                    continue;
                }
                // Not in the map
                map.put(st, i);

            }
        }

        // 4) Hash array creation
        HashMap<Integer, List<String>> hlist = new HashMap<>();

        for (String key : map.keySet()) {
            String st = key;
            int i = map.get(key);

            int root = dj.findRoot(i);

            List<String> ltt;
            if(hlist.containsKey(root)) {
                ltt = hlist.get(root);
            } else {
                ltt = new ArrayList<>();
            }

            ltt.add(st);
            hlist.put(root, ltt);
        }

        // 5) Sorting the array
        for(List<String> lt : hlist.values() ) {
            Collections.sort(lt);
        }

        // 6) Creating the return list
        List<List<String>> result = new ArrayList<>();
        for(Integer i: hlist.keySet()) {
            List<String> lt = hlist.get(i);
            lt.addFirst(accounts.get(i).getFirst());
            result.add(lt);
        }

        return result;
    }
    //18) Number of additional connections required in the network
    // Medium https://leetcode.com/problems/number-of-operations-to-make-network-connected/
    public int makeConnected(int n, int[][] connections) {
        // Base condition
        // Less edges to refactor
        if(connections.length < n -1) {
            return -1;
        }

        // Modified Kruskals algo for no edge weights
        // 1) Queue
        DisjointSet dj = new DisjointSet(n);

        // 2) Operation
        for(int i  = 0; i < connections.length; i++) {
            int cn = connections[i][0];
            int nn = connections[i][1];

            int cr = dj.findRoot(cn);
            int nr = dj.findRoot(nn);

            if(cr == nr) {
                continue;
            }

            dj.union(cn, nn);
        }

        int result = -1;
        for(int i = 0; i < n; i++) {
            if(dj.findRoot(i) == i) {
                result++;
            }
        }

        return result;
    }

    // Concept -> Kruskal's Algorithm
    public static List<Pair> kruskalAlgo(int[][] edges, int n) {
        // 1) Dis joint set
        DisjointSet dj = new DisjointSet(n);

        // 2) Heap
        PriorityQueue<Ppair> q = new PriorityQueue<Ppair>((f,s) -> f.w - s.w);
        for (int i = 0; i < n; i++) {
            q.offer(new Ppair(edges[i][0], edges[i][1], edges[i][2]));
        }

        // 3) Minimum spanning Tree
        List<Pair> result = new ArrayList<>();

        // 3) Operation
        while (!q.isEmpty()) {
            Ppair pair = q.poll();
            int cn = dj.findRoot(pair.n);
            int nn = dj.findRoot(pair.p);

            // Already connected

            if(cn == nn) {
                continue;
            }

            dj.union(cn, nn);
            result.add( new Pair(pair.n, pair.p));
        }

        return result;
    }

    // Concept -> Disjoint set
    static class DisjointSet{
        List<Integer> parent;
        List<Integer> rank = new ArrayList<>();
        DisjointSet(int n) {
            parent = new ArrayList<>(n+1);
            rank = new ArrayList<>(n+1);
            for (int i = 0; i < n+1; i++) {
                parent.add(i);
                rank.add(0);
            }
        }

        public void union(int u, int v) {
            // 1) Find out the root of each nodes
            u = findRoot(u);
            v = findRoot(v);

            // Already joint
            if(u == v) {
                return;
            }

            // 2) Rank both the parent
            int uRank = rank.get(u);
            int vRank = rank.get(v);

            // 3) Union operation
            if(uRank > vRank) {
                parent.set(v, u);
            }
            else if(uRank < vRank) {
                parent.set(u, v);
            }
            // Equal
            else {
                parent.set(v, u);
                rank.set(u, uRank +1);
            }
        }

        public boolean find(int u, int v) {
            return findRoot(u) == findRoot(v);
        }

        private int findRoot(int u) {
            if(u == parent.get(u)) {
                return u;
            }

            int root = findRoot(parent.get(u));
            parent.set(u, root);
            return root;
        }
    }

    // Concept -> Prim's algorithm
    public ArrayList<Pair> primsAlgo(int[][] edges, int n) {
        // 1) Priority queue
        PriorityQueue<Ppair> q = new PriorityQueue<Ppair>((f,s) -> f.w - s.w);
        q.offer(new Ppair(0,-1,0));

        // 2) Visited
        boolean[] visited = new boolean[n];

        // 3) Results config
        int sum = 0;
        ArrayList<Pair> result = new ArrayList<>();

        // Operation
        while (!q.isEmpty()) {
            Ppair pair = q.poll();
            if(visited[pair.n]) continue;
            int cn = pair.n;

            // Visited is marked only after the node has been removed from the priority queue
            visited[pair.n] = true;
            sum+= pair.w;

            // Don't add it to the result if it is the first node
            if(pair.p > -1) {
                result.add(new Pair(pair.p, pair.n));
            }

            // Adding adjacent nodes
            for (int i = 0; i < edges.length; i++) {
                if(edges[i][0] == cn || edges[i][1] == cn  ) {
                    int nn = edges[i][0];
                    if(edges[i][0] == cn) {
                        nn = edges[i][1];
                    }

                    // Add only if not visited
                    if(visited[nn]) continue;
                    q.offer(new Ppair(nn, cn, edges[i][2]));
                }
            }

        }
        return result;
    }

    // 17) Smallest number of neighbors threshold
    // Medium https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // 1) Matrix
        int[][] cost = new int[n][n];
        // Bi directional edges
        for(int i =0; i< edges.length; i++) {
            cost[edges[i][0]][edges[i][1]] = edges[i][2];
            cost[edges[i][1]][edges[i][0]] = edges[i][2];
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(cost[i][j] > 0 || i == j) {
                    continue;
                }
                cost[i][j] = Integer.MAX_VALUE;
            }
        }

        // 2) Iteration
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                if(cost[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for(int j = 0; j < n; j++) {
                    if(i == j || cost[k][j] == Integer.MAX_VALUE || i ==k || j == k) {
                        continue;
                    }

                    if(cost[i][j] > cost[i][k] + cost[k][j]) {
                        cost[i][j] = cost[i][k] + cost[k][j];
                    }
                }
            }
        }

        // 3) Distance threshold
        int[] dist = new int[n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(cost[i][j] > distanceThreshold) {
                    dist[i]++;
                }
            }
        }

        // 4) Finding the most
        int largest = dist[0];
        int result = 0;
        for(int i = 0; i < n; i++) {
            if(dist[i] >= largest) {
                largest = dist[i];
                result = i;
            }
        }

        return result;
    }

    // Concept -> Floyd Warshall algorithm
    public int[][] floydWarshall(int[][] edges, int n) {
        // 1) Create the matrix
        int[][] mat = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    edges[i][j] = 0;
                }
                edges[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < edges.length; i++) {
            mat[edges[i][0]][edges[i][1]] = edges[i][2];
        }

        // 2) Iteratively make the matrix great
        for (int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                // Row check
                if(mat[i][k] == Integer.MAX_VALUE) {
                    continue;
                }
                for(int j = 0; j < n; j++) {
                    if(i == k || j == k || mat[k][j] == Integer.MAX_VALUE) {
                        continue;
                    }
                    // Go via
                    if(mat[i][j] > mat[i][k] + mat[k][j]) {
                        mat[i][j] = mat[i][k] + mat[k][j];
                    }
                }
            }
        }

        // 3) Negative cycle
        for(int i = 0; i < n; i++) {
            if(mat[i][i] < 0) {
                return new int[][]{ {-1} };
            }
        }

        return mat;
    }

    // Concept -> Bell Man Ford algorithm
    public int[] bellmanFord(int[][] edges, int n, int s) {
        // 1) Distance array
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        // 2) Relaxation
        for(int j = 0; j < n-1; j++) {
            for(int i = 0; i < n; i++) {
                int cr = edges[i][0];
                int nr = edges[i][1];
                int cd = dist[cr];

                // If it has not been assigned - continue
                if(cd == Integer.MAX_VALUE) {
                    continue;
                }

                int nd = edges[i][2] + cd;
                if(dist[nr] > nd) {
                    dist[nr] = nd;
                }
            }
        }

        // 3) Negative cycle check
        for(int i = 0; i < n; i++) {
            int cr = edges[i][0];
            int nr = edges[i][1];
            int cd = dist[cr];
            int nd = edges[i][2] + cd;
            if(dist[nr] > nd) {
                return new int[]{-1};
            }
        }
        return dist;
    }

    // 16) Number of ways to arrive at the destination
    // Medium https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
    public int countPaths(int n, int[][] roads) {
        // 1) Distance array
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        // Always starts from 0
        dist[0] = 0;


        // 2) Priority Queue
        PriorityQueue<Cpair> q = new PriorityQueue<Cpair>((f,s) -> Long.compare(f.d, s.d));
        q.offer(new Cpair(0, 0));

        // Memoization array
        long[] memo = new long[n];
        memo[0] = 1;
        final int mod = (int) (1e9 + 7);

        while(!q.isEmpty()) {
            Cpair pair = q.poll();
            int cr = pair.r;
            long cd = pair.d;

            for(int i = 0; i < roads.length; i++) {
                if(! (roads[i][0] == cr || roads[i][1] == cr)) {
                    continue;
                }

                int nr = roads[i][1];
                if(roads[i][1] == cr) {
                    nr = roads[i][0];
                }

                long nd = cd + roads[i][2];

                // If it is the result node
                if(nr == n-1) {
                    // 1) It is some value, but bigger
                    if(dist[nr] > nd) {
                        dist[nr] = nd;
                        memo[nr] = memo[cr];
                    }
                    // 2) It is the same
                    else if(dist[nr] == nd) {
                        memo[nr]= (memo[nr] + memo[cr]) % mod;
                    }
                    // 3) New distance is bigger
                    // Ignore it
                }

                // If it's a normal node, it must be smaller than the current node distance and also            the target node total distance
                else if(nd < dist[n-1]) {
                    if (nd < dist[nr]) {
                        dist[nr] = nd;
                        q.offer(new Cpair(nr, nd));
                        memo[nr] = memo[cr];
                    } else if (nd == dist[nr]) {
                        memo[nr] = (memo[nr] + memo[cr] ) % mod;
                    }

                }
            }
        }

        // Computing the result
        return (int) ((memo[n-1] % mod));
    }

    // 15) Cheapest flight with K stops
    // https://leetcode.com/problems/cheapest-flights-within-k-stops/
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 2) Distance array
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;


        // 1) Min heap
        Queue<Fpair> q = new LinkedList<Fpair>();
        for(int j = 0; j < flights.length; j++) {
            if( flights[j][0] == src && (dist[flights[j][1]] > flights[j][2] ) ) {
                dist[flights[j][1]] = flights[j][2];
                q.offer(new Fpair(flights[j][1], flights[j][2]));
            }
        }


        // 3) Operation
        int steps = -1;
        while(!q.isEmpty()) {
            if(++steps == k) {
                break;
            }
            int size = q.size();
            for(int i = 0; i < size; i++) {
                Fpair pair = q.poll();
                int cr = pair.r;
                int cd = pair.d;

                // Find the next thing
                for(int j = 0; j < flights.length; j++) {
                    if( !(flights[j][0] == cr)) {
                        continue;
                    }
                    int nr = flights[j][1];
                    int nd = cd + flights[j][2];

                    if (dist[nr] > nd) {
                        dist[nr] = nd;
                        if(!(nr == dst)) {
                            q.offer(new Fpair(nr, nd));
                        }
                    }
                }

            }
        }

        return dist[dst] == Integer.MAX_VALUE?-1:dist[dst];
    }

    // 14) Minimum effort Path
    // https://leetcode.com/problems/path-with-minimum-effort/
    public int minimumEffortPath(int[][] heights) {
        // 1) Min heap
        PriorityQueue<Tpair> q = new PriorityQueue<Tpair>((f,s) -> f.d - s.d);
        q.offer(new Tpair(0,0,0));

        int rn = heights.length;
        int cn = heights[0].length;

        // 2) Distance matrix
        int[][] dist = new int[rn][cn];
        int val = Integer.MAX_VALUE;
        for(int i = 0; i < rn; i++) {
            for(int j = 0; j < cn; j++) {
                dist[i][j] = val;
            }
        }
        dist[0][0] = 0;

        // 3) Queue
        while(!q.isEmpty()) {
            Tpair pair = q.poll();
            int cr = pair.r;
            int cc = pair.c;
            int cd = pair.d;


            // Only four directions
            int[] rArr = {-1, 0, 1, 0};
            int[] cArr = {0, 1, 0, -1};

            for(int i = 0; i < 4; i++) {
                int nr = cr + rArr[i];
                int nc = cc + cArr[i];

                if (nr >= 0 && nr < rn && nc >= 0 && nc < cn) {
                    int nd = Math.max(Math.abs(heights[cr][cc] - heights[nr][nc]), cd);
                    if (dist[nr][nc] > nd) {
                        dist[nr][nc] = nd;
                        if ( !(nr == rn-1 && nc == cn-1)) {
                            q.offer(new Tpair(nr, nc,nd));
                        }
                    }
                }
            }
        }

        return dist[rn-1][cn-1];
    }

    // 13) Shortest distance in a binary matrix
    // https://leetcode.com/problems/path-with-minimum-effort/
    public static int shortestPathBinaryMatrix(int[][] grid) {
        // Edge case
        if(grid[0][0] == 1) {
            return -1;
        }
        int n = grid.length;
        // 1) Priority Queue
        PriorityQueue<Tpair> q = new PriorityQueue<Tpair>((f,s) -> f.d - s.d);
        q.offer(new Tpair(0, 0, 0));

        // 2) Distance matrix
        int[][] dist = new int[n][n];
        for(int i = 0; i< n; i++) {
            for(int j = 0; j< n; j++) {
                dist[i][j] = n*n;
            }
        }
        dist[0][0] = 1;

        // Work
        while(!q.isEmpty()) {
            Tpair pair = q.poll();
            int cDist = pair.d;
            int cr = pair.r;
            int cc = pair.c;

            for(int i = cr-1; i<= cr+1; i++) {
                for(int j = cc-1; j <= cc+1; j++) {
                    if (i >= 0 && j >= 0 && i < n && j <n &&
                            grid[i][j] == 0 && dist[i][j] > cDist + 1) {

                        dist[i][j] = cDist + 1;

                        if( !(i == n -1 && j == n -1) ) {
                            q.offer(new Tpair(cDist +1, i, j));
                        }
                    }
                }
            }
        }
        if (dist[n-1][n -1] != n*n) {
            return dist[n -1][n -1];
        }
        return -1;
    }

    // GFG problem Shortest path from node 1 to n in an undirected graph
    public List<Integer> returnShortestPathUnDirected(int n, int m, int edges[][]) {
        //  Priority Queue
        PriorityQueue<Pair> q = new PriorityQueue<Pair>((f, s) -> f.r - s.r);
        q.offer(new Pair(1,0));

        // Distance array
        int[] dist = new int[m+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        // Memoization array
        int[] parent = new int[m+1];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        List<Integer> result = new ArrayList<>();
        Stack<Integer> resStack = new Stack<>();

        while(!q.isEmpty()) {
            // Get the list from the queue
            Pair pair = q.poll();
            int cNode = pair.r;
            int cDist = pair.c;

            // Traverse through the adjacency list
            for (int[] edge : edges) {
                if (edge[0] == cNode) {
                    int nNode = edge[1];
                    int nDist = edge[2];

                    if (dist[nNode] > cDist + nDist) {
                        dist[nNode] = cDist + nDist;
                        parent[nNode] = cNode;

                        // If it is the target node
                        if (nNode == n) {
                            Stack<Integer> res = new Stack<>();
                            int i = n;
                            // Only for 1, parent would be 1
                            do {
                                res.push(parent[i]);
                                i = parent[i];
                            }
                            while (parent[i] != i);
                            resStack = res;
                        }
                        // Only offer if it is not the target node
                        else {
                            q.offer(new Pair(nNode, cDist + nDist));
                        }
                    }
                }
            }
        }

        while (!resStack.isEmpty()) {
            result.add(resStack.pop());
        }

        if(result.isEmpty()) {
            result.add(-1);
        }
        return result;
    }

    // Concept Dijkstra's algorithm
    public static int[] dijkstra(List<List<Pair>> adj, int src) {
        // Priority queue
        PriorityQueue<Pair> q = new PriorityQueue<Pair>((x,y) -> x.r - y.r);
        q.offer(new Pair(src, 0));

        // Distance array
        int[] dist = new int[adj.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;


        while (!q.isEmpty()) {
            Pair pair = q.poll();
            for(Pair p: adj.get(pair.c)) {
                if (dist[p.c] > pair.r + p.r) {
                    q.offer(new Pair(pair.r,  + p.r));
                    dist[p.c] = pair.r + p.r;
                }
            }
        }

        return dist;

    }

    // 12) Word Ladder II
    // https://leetcode.com/problems/word-ladder/
    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();
        Queue<List<String>> q = new LinkedList();
        Set<String> set = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();

        // 1) Create queue
        List<String> first = new ArrayList<>();
        first.add(beginWord);
        q.offer(first);
        visited.add(beginWord);

        boolean over = false;
        while(!q.isEmpty()) {
            int size = q.size();
            if(over) break;
            Set<String> tvisit = new HashSet<>();

            for(int i = 0; i < size; i++) {
                List<String> clist = q.poll();
                char[] p = clist.getLast().toCharArray();

                for(int j = 0; j < p.length; j++) {
                    char[] c = Arrays.copyOf(p, p.length);

                    for(int k = 0; k < 26; k++) {
                        c[j] = (char) ((char) k  + 'a');
                        String st = new String(c);

                        if (set.contains(st) && !visited.contains(st)) {
                            List<String> nlist = new ArrayList<>(clist);
                            nlist.add(st);
                            q.offer(nlist);
                            tvisit.add(st);

                            if(st.equals(endWord)) {
                                over = true;
                                result.add(nlist);
                            }
                        }
                    }
                }
            }
            visited.addAll(tvisit);

        }
        return result;
    }

    // 11) Word ladder
    // https://leetcode.com/problems/word-ladder-ii/
    public static  int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<String> q = new LinkedList<>();

        q.offer(beginWord);

        set.remove(beginWord);

        int dis = 1;

        while(!q.isEmpty()) {
            dis++;
            int size = q.size();

            for(int k = 0; k < size; k++) {
                char[] p = q.poll().toCharArray();
                for(int i = 0; i < p.length; i++) {
                    char[] c = Arrays.copyOf(p, p.length);
                    for(int j = 0; j < 26; j++) {
                        c[i] = (char) ((char) j  + 'a');
                        String st = new String(c);
                        if (set.contains(st)) {
                            if(st.equals(endWord)) {
                                return dis;
                            }
                            q.offer(st);
                            set.remove(st);
                        }
                    }
                }
            }
        }
        return 0;
    }

    // Concept
    // Shortest distance from source node to all nodes in DAG-> Topo sort
    // Very important => Assumption that the top of the stack would be the source node (Only if the source points to all the elements, distance can be calculated)
    public static int[] shortestDistanceDAG(ArrayList<ArrayList<Pair>> adj, int s) {
        // 1) Create topo sort stack
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[adj.size()];
        for(int i = 0; i < adj.size(); i++) {
            if(!visited[i]) {
                shortestDFS(i, adj, visited, stack);
            }
        }

        // 2) Create the distance Array
        int[] dist = new int[adj.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        // 3) Take out node from stack and look at it's adjacency
        while (!stack.isEmpty()) {
            int node = stack.pop();

            int nodeDist= dist[node];

            if (nodeDist == Integer.MAX_VALUE) {
                nodeDist = 0;
            }

            // If the distance is lower, change the distance, if not, don't change
            for(Pair p: adj.get(node)) {
                if (dist[p.c] < nodeDist + p.r) {
                    dist[p.c] = nodeDist + p.r;
                }
            }
        }

        return dist;


    }
    public static void shortestDFS(int i, ArrayList<ArrayList<Pair>> adj, boolean[] visited, Stack<Integer> stack) {
        if(visited[i]) {
            return;
        }

        visited[i] = true;

        for(Pair p: adj.get(i)) {
            shortestDFS(p.c, adj, visited, stack);
        }

        // When exiting, add it to the stack
        stack.push(i);
    }

    // Shortest distance from source node to all nodes in undirected graph
    public static int[] shortestDistanceUnDirected(ArrayList<ArrayList<Pair>> adj, int s) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(s, 0));
        int[] dist = new int[adj.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);

        while (!q.isEmpty()) {
            Pair pair = q.poll();

            int node = pair.r;
            int d = pair.c;

            for(Pair p: adj.get(node)) {
                if(d + p.c <  dist[p.r] ) {
                    q.offer(new Pair(p.r, d + p.c));
                    dist[p.r] = d + p.c;
                }
            }
        }

        return dist;
    }

    // 11) Cheapest flight within K stops -> TLE error
    // Can be solved only by dijkstra's since it is a DCG
    // Similar problem https://leetcode.com/problems/network-delay-time/
    public static int findCheapestPrice0(int n, int[][] flights, int src, int dst, int k) {
        int sol = Integer.MAX_VALUE;

        for(int i = 0; i < flights.length; i++) {
            if (flights[i][0] == src) {
                boolean[] visited = new boolean[n+flights.length];
                int res = cheapDFS(i, flights, visited, 0, 0, k, dst);
                if ( res > -1){
                    sol = Integer.min(sol, res);
                }
            }
        }

        if (sol == Integer.MAX_VALUE) {
            return-1;
        }
        return sol;

    }
    public static int cheapDFS(int i, int[][] flights, boolean[] visited, int price, int steps, int n, int dst) {
        if(visited[flights[i][0]]) {
            return -1;
        }

        if(flights[i][1] == dst) {
            return price+flights[i][2];
        }

        if (++steps > n) {
            return -1;
        }

        visited[flights[i][0]] = true;

        int resulter = Integer.MAX_VALUE;
        for(int r = 0; r<flights.length; r++) {
            if (flights[r][0] == flights[i][1]) {
                int result = cheapDFS(r, flights, visited, price+flights[i][2], steps, n, dst);
                if (result > 0) {
                    resulter = Integer.min(resulter, result);
                }
            }
        }


        // Back tracking
        visited[flights[i][0]] = false;

        if (resulter == Integer.MAX_VALUE) {
            return -1;
        }
        return resulter;
    }

    // 10) Course Schedule II -> Literally the same as course schedule
    // Medium https://leetcode.com/problems/course-schedule-ii/
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        // Create inDeg
        int[] deg = new int[numCourses];
        for(int i = 0; i < prerequisites.length; i++) {
            deg[prerequisites[i][0]]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < deg.length; i++) {
            if (deg[i] == 0) {
                q.offer(i);
            }
        }

        int[] result = new int[numCourses];
        int index = -1;
        while(!q.isEmpty()) {
            int node = q.poll();
            result[++index] = node;
            for(int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == node) {
                    deg[prerequisites[i][0]]--;
                    if (deg[prerequisites[i][0]] <= 0) {
                        q.offer(prerequisites[i][0]);
                    }
                }
            }
        }

        if (index == numCourses-1) {
            return result;
        }
        return new int[0];

    }

    // 9) Course Schedule
    // Medium https://leetcode.com/problems/course-schedule/
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create inDeg
        int[] deg = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            deg[prerequisite[0]]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < deg.length; i++) {
            if (deg[i] == 0) {
                q.offer(i);
            }
        }

        int index = 0;
        while(!q.isEmpty()) {
            int node = q.poll();
            index ++;
            for(int i = 0; i < prerequisites.length; i++) {
                if (prerequisites[i][1] == node) {
                    deg[prerequisites[i][0]]--;
                    if (deg[prerequisites[i][0]] <= 0) {
                        q.offer(prerequisites[i][0]);
                    }
                }
            }
        }

        return index == numCourses;

    }

    // 8) Find eventual safe states
    // Medium https://leetcode.com/problems/find-eventual-safe-states/
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> result = new ArrayList<>();
        return result;
    }

    // Concept => Bipartite graph => Even number of nodes in a cycle

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

    // Concept -> Depth First Search
    public static void dfsOfGraph(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        int size = adj.getFirst().size();
        for(int i = 0; i < size; i++) {
            if (adj.get(node).get(i) == 1 && !visited[i]) {
                visited[i] = true;
                dfsOfGraph(i, visited, adj);
            }
        }
    }

    // Concept -> Breath First Search
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
