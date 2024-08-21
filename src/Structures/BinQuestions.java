package Structures;

import javax.swing.tree.TreeNode;
import java.util.*;

public class BinQuestions {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);

        root.left.right = new TreeNode(4);
        root.left.left = new TreeNode(3);
//        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(6);
//        root.left.right.right = new TreeNode(6);
//        root.right.left.right = new TreeNode(7);

        int[] bot = {-10,-3,0,5,9};

        flatten(root);
//        System.out.println();
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
    //--------------------- DFS questions -----------------------

    // 18) Kth smallest element in Binary search tree
    public int kthSmallest(TreeNode root, int k) {
        int ck = 0;
        // 1 -> Find the smallest
        while (root != null) {
            root = root.left;

            if (root == null) {
                ck = 1;
            }
            if (k == 1) {
                return root.val;
            }
        }
        return ck;
    }
    public int kthSmallestRec(TreeNode root, int k) {
        ArrayList<Integer> val = new ArrayList<>(1);
        kthSmallest(root, 0, k, val );
        return val.getFirst();
    }
    public int kthSmallest(TreeNode node, int k, int target, ArrayList<Integer> val) {
        // Base condition
        if (node == null) {
            return k;
        }

        // Search the left side
        k = kthSmallest(node.left, k, target, val) + 1;

        // If k already found, don't go into right
        if (k < 0) {
            return  -10;
        }

        // If kth is the target,
        if (k == target) {
            val.add(node.val);
            return -10;
        }

        // If k is not found in the left, then go into the right
        return kthSmallest(node.right, k, target, val);
    }



    // 17) Lowest common ancestor
    // Medium -> EASSYY confidence booseter https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return root;
        }

        if (root == p || root == q) {
            // First check if it is within
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);

            if (left != null || right != null) {
                return root;
            }

            // If both are null, it must be on the parent's side
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        if (left != null) {
            return left;
        }
        return right;

    }

    // 17 B -> of a binary search tree
    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/

    // 16) Validate binary search tree
    // Medium https://leetcode.com/problems/validate-binary-search-tree/description/
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isValidBSTRec(root.left, Long.MIN_VALUE, root.val);
        boolean right = isValidBSTRec(root.right, root.val, Long.MAX_VALUE);

        return left && right;
    }
    public boolean isValidBSTRec(TreeNode root, long l, long h) {
        // Base condition
        if (root == null) {
            return true;
        }

        // Negative base condition
        if (root.val <= l || root.val >= h) {
            return false;
        }

        boolean left = isValidBSTRec(root.left, l, root.val);
        boolean right = isValidBSTRec(root.right, root.val, h);

        return left && right;
    }

    // 15) Flatten binary tree into linked list
    // Medium https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
    public static void flatten(TreeNode root) {
        Stack<TreeNode> temp = new Stack<>();
        flatten(root, temp);
    }
    public static TreeNode flatten(TreeNode node, Stack<TreeNode> stack) {
        if (node == null) {
            return null;
        }

        // Go check the left side
        if (node.right != null) {
            stack.push(node.right);
        }

        node.right = flatten(node.left, stack);

        // Go do whatever with the next one in the stack -> it might be the right or the previous right or whatever
        if (node.right == null) {
            if (!stack.isEmpty()) {
                node.right = flatten(stack.pop(), stack);
            }
        }

        node.left = null;
        return node;

    }
    public static void flattenConstantSpaceComplexity(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }

        if (root.left != null && root.right != null) {
            TreeNode emptyMan = findRightMostEmptyNode(root.left);
            emptyMan.right = root.right;
            root.right  = root.left;
            root.left = null;
        }

        if (root.left != null) {
            root.right  = root.left;
            root.left = null;
        }

        flattenConstantSpaceComplexity(root.right);
    }
    public static TreeNode findRightMostEmptyNode(TreeNode node) {
        if (node.right == null) {
            return node;
        }

        return findRightMostEmptyNode(node.right);
    }

    // 14) Convert sorted array into binary search tree
    // Very easy https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
    public static TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length-1);
    }
    public static TreeNode sortedArrayToBST(int[] nums, int s, int e) {
        int m = (s + e) / 2;

        TreeNode node = new TreeNode(nums[m]);
        if (s == e) {
            return node;
        }

        if (s > e) {
            return  null;
        }
        node.left = sortedArrayToBST(nums,s, m-1);
        node.right = sortedArrayToBST(nums, m+1, e);

        return node;
    }
    // 13) Height of the tree
    // Very very easy https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public int maxDepth(TreeNode root) {
        return heightOfTheTree(root) - 1;
    }
    public int heightOfTheTree(TreeNode root) {
        // Base condition
        if (root == null) {
            return 0;
        }
        int l = height(root.left);
        int r = height(root.right);
        return  Integer.max(l,r) + 1;
    }

    // 11) Diameter of binary tree
    // Easy https://leetcode.com/problems/diameter-of-binary-tree/
    static public int diameter = 0;
    static public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter;
    }
    static  public int height(TreeNode node) {
        // Base condition
        if (node == null) {
            return 0;
        }
        int l = height(node.left);
        int r = height(node.right);
        int dia = l + r;
        diameter = Integer.max(l+r, diameter);
        return  Integer.max(l,r) + 1;
    }

    // 12) Invert binary tree
    // Easy https://leetcode.com/problems/invert-binary-tree/
    static public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return  null;
        }
        invert(root);
        return root;
    }
    static public void invert(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invert(root.left);
        invert(root.right);
    }





    // -----------------BFS questions -----------
    // 10)  Symmetric tree or not
    // Easy but hard https://leetcode.com/problems/symmetric-tree/
    public static boolean isSymmetric(TreeNode root) {
            if (root.left == null && root.right == null) {
                return true;
            }

            if (root.left == null || root.right == null) {
                return false;
            }

            if (root.left.val != root.right.val) {
                return false;
            }
            Deque<TreeNode> q1 = new ArrayDeque<>();
            Queue<TreeNode> q2 = new LinkedList<>();

            q1.offer(root.left);
            q2.offer(root.right);

            while (!q1.isEmpty()) {
                int q1S = q1.size();
                for (int i = 0; i < q1S; i++) {
                    TreeNode left = q1.poll();
                    TreeNode right = q2.poll();

                    // Checking null
                    if (
                            (left.left == null && right.right != null) ||
                                    (left.left != null && right.right == null) ||
                                    (left.right == null && right.left != null) ||
                                    (left.right != null && right.left == null)
                    ) {
                        return false;
                    }

                    if (left.left != null && right.right != null) {
                        if (left.left.val != right.right.val) {
                            return false;
                        }else {
                            q1.offer(left.left);
                            q2.offer(right.right);
                        }
                    }


                    if (left.right != null && right.left != null) {
                        if (left.right.val != right.left.val) {
                            return false;
                        } else {
                            q1.offer(left.right);
                            q2.offer(right.left);
                        }
                    }

                }
            }

            return true;
        }

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
    // Easy but hard https://leetcode.com/problems/cousins-in-binary-tree/
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
