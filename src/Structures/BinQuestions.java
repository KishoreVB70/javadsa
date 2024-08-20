package Structures;

import java.util.*;

public class BinQuestions {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(rightSideView(root));
    }
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    // 8) Right side view
    // Medium https://leetcode.com/problems/binary-tree-right-side-view/
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> lt = new ArrayList<>();
        if (root == null) {
            return lt;
        }

        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if(node.left != null){
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
                if (i == size - 1) {
                    lt.add(node.val);
                }
            }
        }
        return lt;
    }

    //9) Find cousin or not
    // Easy https://leetcode.com/problems/cousins-in-binary-tree/
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }

        boolean found = false;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    if (node.left.val == x || node.left.val == y) {
                        if (found) {
                            return true;
                        }
                        if (i == size -1) {
                            return false;
                        }
                        found = true;
                        continue;
                    }
                }
                if (node.right != null) {
                    if (node.right.val == x || node.right.val == y) {
                        if (found) {
                            return true;
                        }
                        if (i == size -1) {
                            return false;
                        }
                        found = true;
                        continue;
                    }
                }

                if(node.left != null){
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
                if (i == size -1) {
                    if (found) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    //7 Next right pointer
    // Medium https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
    public static Node connectToNext1(Node root) {
        if (root == null){
            return root;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Node currentNode = q.poll();
                if (currentNode.left != null) {
                    q.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    q.offer(currentNode.right);
                }
                if (i < size - 1) {
                    currentNode.next = q.peek();
                }
            }

        }
        return  root;
    }
    public static Node connectToNext2(Node root) {
        if (root == null){
            return root;
        }
        helperConnectToNext2(root);
        return  root;
    }
    public static Node helperConnectToNext2(Node root) {
        if (root == null){
            return root;
        }
        // Base condition
        if (root.left == null) {
            return root;
        }
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        helperConnectToNext2(root.left);
        helperConnectToNext2(root.right);
        return root;
    }

    // 4 -> Level order successor -> BFS
    // Type 1 -> If there is nothing to the right, you want the next one to the far left
    static TreeNode findLevelOrderSuccessor1 (TreeNode root, TreeNode target) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode currentNode = q.poll();
            if (currentNode.left != null) {
                q.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                q.offer(currentNode.right);
            }
            if (currentNode == target) {
                if (!q.isEmpty()) {
                    return q.poll();
                }
                return null;
            }
        }

        return null;
    }
    // Type 2 -> if there is nothing to the right, return null
    static TreeNode findLevelOrderSuccessor2(TreeNode root, TreeNode target) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = q.poll();
                if (currentNode.left != null) {
                    q.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    q.offer(currentNode.right);
                }

                if (currentNode == target) {
                    if (i+1 == size) {
                        return null;
                    }
                    return q.poll();
                }
            }
        }

        return null;
    }

    // 5 -> Binary Tree Zigzag Level Order Traversal
    // Medium https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
    // Legit => Don't reverse
    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        List<List<Integer>> returnList = new ArrayList<>();

        if (root == null) {
            return returnList;
        }

        ArrayDeque<TreeNode> q = new ArrayDeque<>();

        q.offer(root);
        boolean rev = false;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> currentList = new ArrayList<>();
            if (!rev) {
                for (int i = 0; i < size ; i++) {
                    TreeNode node = q.removeFirst();
                    currentList.add(node.val);
                    if (node.left != null) {
                        q.addLast(node.left);
                    }
                    if (node.right != null) {
                        q.addLast(node.right);
                    }
                }
            } else {
                for (int i = 0; i < size ; i++) {
                    TreeNode node = q.removeLast();
                    currentList.add(node.val);
                    if (node.right != null) {
                        q.addFirst(node.right);
                    }

                    if (node.left != null) {
                        q.addFirst(node.left);
                    }
                }
            }

            returnList.add(currentList);
            rev = !rev;
        }
        return  returnList;
    }
    // Reversing
    public static List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> returnList = new ArrayList<>();

        if (root == null) {
            return returnList;
        }

        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);
        int n = 1;
        while (!q.isEmpty()) {
            List<Integer> currentList = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
                currentList.add(node.val);
            }
            if (n % 2 == 0) {
                currentList = currentList.reversed();
            }
            returnList.add(currentList);
            n++;
        }
        return  returnList;
    }

    // 6 -> Level order traversal, but from last
    // Medium -> https://leetcode.com/problems/binary-tree-level-order-traversal-ii/
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> returnList = new ArrayList<>();
        if (root == null) {
            return returnList;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> currentList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                currentList.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            returnList.addFirst(currentList);
        }
        return  returnList;
    }


    // 1 -> Breath first search
    static void bfsPrint(BinSearchTree.Node root) {
        if (root == null) {
            return;
        }
        ArrayList<BinSearchTree.Node> nodes = new ArrayList<>();
        nodes.add(root);
        bfsPrint(nodes);
    }
    static void bfsPrint(ArrayList<BinSearchTree.Node> nodes) {
        // Base condition
        if (nodes.isEmpty()) {
            return;
        }

        BinSearchTree.Node node = nodes.getFirst();

        System.out.println(node.value);
        if (node.left != null) {
            nodes.add(node.left);
        }
        if (node.right != null) {
            nodes.add(node.right);
        }
        nodes.removeFirst();
        bfsPrint(nodes);


    }

    // 2) Returning every level as a separate list as a list
    // https://leetcode.com/problems/binary-tree-level-order-traversal/
    public List<List<Integer>> levelOrderTraversalArrayList(TreeNode root) {
        List<List<Integer>> returnList = new ArrayList<>();

        if (root == null) {
            return returnList;
        }

        List<TreeNode> qu = new ArrayList<>();
        qu.add(root);

        levelOrderTraversalArrayList(returnList, new ArrayList<>(), qu, null ) ;
        return returnList;
    }
    static void levelOrderTraversalArrayList(List<List<Integer>> returnList, List<Integer> currentList, List<TreeNode> currentQ, List<TreeNode> nextQ) {
        if (currentQ.isEmpty()) {
            return;
        }

        if (nextQ == null) {
            nextQ = new ArrayList<>();
        }

        TreeNode node = currentQ.removeFirst();
        currentList.add(node.val);

        if (node.left != null) {
            nextQ.add(node.left);
        }

        if (node.right != null) {
            nextQ.add(node.right);
        }

        // If current level is over
        if (currentQ.isEmpty()) {
            returnList.add(currentList);
            levelOrderTraversalArrayList(returnList, new ArrayList<>(),  nextQ,  null);
            return;
        }

        // If current level is not over
        levelOrderTraversalArrayList(returnList, currentList,  currentQ,  nextQ);

    }

    // 3) Easy -> can't believe this is a google question -> Average of all levels as a list
    //https://leetcode.com/problems/average-of-levels-in-binary-tree/
    public static List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Double> returnList = new ArrayList<>();

        q.offer(root);

        while (!q.isEmpty()) {
            int currentLevelEnd = q.size();
            // 1) Find the double while also adding children to next level
            long total = 0;
            int n = 0;

            while (n < currentLevelEnd) {
                TreeNode node = q.poll();
                if (node.right != null) {
                    q.offer(node.right);
                }
                if (node.left != null) {
                    q.offer(node.left);
                }

                total += node.val;
                n++;
            }

            returnList.add((double) total/n);
        }
        return  returnList;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
