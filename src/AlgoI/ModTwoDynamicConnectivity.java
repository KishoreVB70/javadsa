package AlgoI;

public class ModTwoDynamicConnectivity {

    public class QuickFind{
        private int[] ids;
        QuickFind() {

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

    }
    public class WeightedUnion {

    }


}
