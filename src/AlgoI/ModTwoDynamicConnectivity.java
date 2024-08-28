package AlgoI;

public class ModTwoDynamicConnectivity {

    public class QuickFind{
        private int[] ids;


        public QuickFind(int n) {
            ids = new int[n];
            for (int i = 0; i < n; i++) {
                ids[i] = i;
            }
        }

        public boolean connected(int p, int q) {
            return ids[p] == ids[q];
        }

        public void union(int p, int q) {
            int oldP = ids[p];
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] == oldP)  ids[i] = ids[q];
            }
        }
    }

    public class QuickUnion{
        private int[] ids;

        public QuickUnion(int n) {
            ids = new int[n];
            for (int i = 0; i < n; i++) {
                ids[i] = i;
            }
        }

        private int root(int p) {
            while (ids[p] != p) p = ids[p];
            return p;
        }

        public void union(int p, int q) {
            int pRoot = root(p);
            int qRoot = root(q);
            ids[pRoot] = qRoot;
        }

        public boolean connected(int p, int q) {
            return root(p) == root(q);
        }

    }
    public class WeightedUnion {
        private int[] ids;
        private int[] size;

        public WeightedUnion(int n) {
            ids = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                ids[i] = i;
            }
        }

        private int root(int p) {
            while (ids[p] != p) p = ids[p];
            return p;
        }

        public void union(int p, int q) {
            int pRoot = root(p);
            int qRoot = root(q);
            if (pRoot == qRoot) return;


            int pSize = size[pRoot];
            int qSize = size[qRoot];

            if (pSize > qSize) {
                ids[qRoot] = pRoot;
                size[pRoot] += qSize;
            } else {
                ids[pRoot] = qRoot;
                size[qRoot] += pSize;
            }
        }

        public boolean connected(int p, int q) {
            return root(p) == root(q);
        }
    }


}
