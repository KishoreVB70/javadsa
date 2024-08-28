package Structures;

import java.util.*;

public class BinQuestions {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(9);
        root.left.left.left.right = new TreeNode(14);
        root.left.left.left.right.right = new TreeNode(26);

        root.left.left.right = new TreeNode(4);
        root.left.left.right.left = new TreeNode(8);
        root.left.left.right.left.right = new TreeNode(17);


        System.out.println(Solution.verticalTraversal(root));

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
    public static class Dnode{
        Dnode left;
        Dnode right;
        int val;

        Dnode(int val) {
            this.val = val;
        }
        Dnode() {
        }
    }

        public int[] twoSum(int[] numbers, int target) {

            int s = 0;
            int e = numbers.length - 1;
            int[] result = new int[2];

            while(s < e) {
                if (numbers[s] + numbers[e] == target) {
                    result[0] = s;
                    result[1] = e;
                }
                else if (numbers[s] + numbers[e] > target) {
                    e--;
                } else {
                    s++;
                }
            }
            return result;
        }

    //---------------------- Advanced tree questions ----------------------

    // 5) Binary search tree has 2 nodes swapped
    static TreeNode prev;
    static TreeNode first;
    static TreeNode second;
    static TreeNode swapBST(TreeNode head) {
        helperSwapBST(head);
        return head;
    }
    static void helperSwapBST(TreeNode current) {
        if (current == null) {
            return;
        }

        helperSwapBST(current.left);

        if (prev != null && prev.val > current.val) {
            if (first == null) {
                first = prev;
            }
            second = current;
        }
        prev = current;

        helperSwapBST(current.right);
    }

    // 4) Convert binary search tree into sorted doubly linked list
    // Medium
    // Locked problem https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/

    static Dnode headd;
    static Dnode currentt;
    static Dnode previouss;
    static Dnode bstIntoDLinkedList(TreeNode root) {
        //In order traversal -> while in it, create a doubly linked list as you go
        helperBstIntoDLinkedList(root);

        return headd;

        // 2 -> for each element in the heap, create a Dnode
    }
    static void helperBstIntoDLinkedList(TreeNode root) {
        if (root == null) {
            return;
        }

        helperBstIntoDLinkedList(root.left);

        currentt = new Dnode(root.val);
        currentt.left = previouss;

        // Condition for first element
        if (previouss != null) {
            previouss.right = currentt;
        } else {
            headd = currentt;
        }

        previouss = currentt;

        helperBstIntoDLinkedList(root.right);

    }


    // 4A) Convert binary tree into sorted doubly linked list
    static Dnode binTreeIntoDLinkedList(TreeNode root) {
        // 1 -> create a min heap for sorted order of the binary tree
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        binTreeIntoDLinkedList(root, heap);

        // 2 -> for each element in the heap, create a Dnode
        Dnode head = new Dnode();
        Dnode previous = null;
        Dnode current;

        while (!heap.isEmpty()) {
            // Link current to previous
            current = new Dnode(heap.poll());
            current.left = previous;

            // Cases for head
            if (previous != null) {
                previous.right = current;
            } else {
                head = current;
            }

            previous = current;
        }

        return head;


    }
    static void binTreeIntoDLinkedList(TreeNode root,PriorityQueue<Integer> heap) {
        if (root == null) {
            return;
        }

        heap.offer(root.val);
        binTreeIntoDLinkedList(root.left, heap);
        binTreeIntoDLinkedList(root.right, heap);


    }


    // 3) two sum IV
    // Leet Easy
    // https://leetcode.com/problems/two-sum-iv-input-is-a-bst/description/
    public boolean findTarget(TreeNode root, int k) {
        // There is a possibility of only one root node
        if (root.left == null && root.right == null) {
            return false;
        }

        // Hash set implementation
        Set<Integer> set = new HashSet<>();


        return findTarget(root, set, k);
    }
    public boolean findTarget(TreeNode node, Set<Integer> set, int target) {
        // Base condition
        if (node == null) {
            return false;
        }

        // In order traversal
        boolean left = findTarget(node.left, set, target);

        int sub = target - node.val;
        if (set.contains(sub)) {
            return true;
        }
        set.add(node.val);

        boolean right = findTarget(node.right, set, target);


        return left || right ;
    }


    // 2) Word ladder
    // Google Hard question
    // https://leetcode.com/problems/word-ladder/description/
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 1 -> check if the end word is in the wordList
        if (!wordList.contains(endWord)) {
            return 0;
        }

        // Visited set
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        Set<String> set = new HashSet<>(wordList);
        set.remove(beginWord);
        int result = 0;


        // n loop
        while (!q.isEmpty()) {
            int qSize = q.size();
            result++;

            for (int k = 0; k < qSize; k++) {
                String current = q.poll();

                // m loop
                for (int i = 0; i < current.length(); i++) {
                    char[] chc = current.toCharArray();

                    // Constant loop
                    for (char c = 'a'; c <= 'z'; c++) {
                        chc[i] = c;
                        String temp = new String(chc);

                        // Base condition
                        if (temp.equals(endWord)) return result+1;

                        if (set.contains(temp)) {
                            // Add it to queue
                            q.offer(temp);
                            // Remove it from list
                            set.remove(temp);
                        }
                    }
                }
            }
        }
        return 0;
    }


    // 1) Vertical ordered traversal of tree
    // Hard
    // https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/

    static int colLow = 0;
    static int colHigh = 0;
    static int rowHigh = 0;
    static List<List<Integer>> returnList = new ArrayList<>();
    static Map<Integer,  Map<Integer, PriorityQueue<Integer>>> mapList = new HashMap<>();
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        verticalTraversal(root, 0, 0);

        // Starts from the lowest column
        // All columns from low till high will definitely be present
        for (int i = colLow; i <= colHigh; i++) {
            Map<Integer, PriorityQueue<Integer>> mp =  mapList.get(i);

            // Create list
            List<Integer> list = new ArrayList<>();

            // Starts from the first row
            for (int j = 0; j <= rowHigh ; j++) {
                // Checking contains, because it may or may not be present
                if (mp.containsKey(j)) {
                    PriorityQueue<Integer> pq =  mp.get(j);
                    for(Integer k: pq) {
                        list.add(k);
                    }
                }
            }
            returnList.add(list);
        }

        return returnList;
    }
    public static void verticalTraversal(TreeNode node, int column, int row) {
        if (node == null) {
            return;
        }
        // If already there is a mapping
        if (mapList.containsKey(column)) {
            // Get the other map
            Map<Integer, PriorityQueue<Integer>> mp = mapList.get(column);

            // Check if the row exists
            // If so, add value
            if (mp.containsKey(row)) {
                PriorityQueue<Integer> pq = mp.get(row);
                pq.offer(row);
            }
            // Row doesn't exist
            else {
                // Create priority queue
                PriorityQueue<Integer> pq = new PriorityQueue<>();
                if (row > rowHigh) {
                    rowHigh = row;
                }
                pq.offer(node.val);
                mp.put(row, pq);
            }
        }
        // Mapping doesn't exist, create everything
        else {
            Map<Integer, PriorityQueue<Integer>> mp = new HashMap<>();
            mapList.put(column, mp);
            if (column < colLow) {
                colLow = column;
            } else if (column > colHigh) {
                colHigh = column;
            }

            // Add the priority queue
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            if (row > rowHigh) {
                rowHigh = row;
            }
            pq.offer(node.val);
            mp.put(row, pq);
        }


        // Recursive calls
        verticalTraversal(node.left, column-1, row +1);
        verticalTraversal(node.right, column+1, row +1);
    }


    // Solution version
    static class Solution {
        static class ColRow{
            int val;
            int col;
            int row;
            ColRow(int val,int col,int row){
                this.val=val;
                this.col=col;
                this.row=row;
            }
        }

        static PriorityQueue<ColRow> pq;
        public static List<List<Integer>> verticalTraversal(TreeNode root) {
            List<List<Integer>> returnList=new ArrayList<>();

            // Initializing the priority queue
            // The lambda function specifies the priority order
            pq=new PriorityQueue<>((a,b)->{
                if (a.col == b.col) {
                    if (a.row == b.row) {
                        return a.val - b.val;
                    } else {
                        return a.row - b.row;
                    }
                } else {
                    return a.col - b.col;
                }
            });
            dfs(root,0,0);


            while(!pq.isEmpty()){
                List<Integer> list=new ArrayList<>();

                // Get the first value
                ColRow poll=pq.poll();
                list.add(poll.val);

                // For all the elements with the same column, add it to the same list
                while(!pq.isEmpty() &&  pq.peek().col==poll.col){
                    list.add(pq.poll().val);
                }
                returnList.add(list);
            }
            return returnList;
        }
        private static void dfs(TreeNode root,int x,int y){
            if(root==null)
                return;
            pq.offer(new ColRow(root.val,x,y));
            dfs(root.left,x-1,y+1);
            dfs(root.right,x+1,y+1);
        }
    }

    //---------------------------- DFS questions ------------------------------

    // 24) Path exists in binary Tree or not
    public static boolean startoz;
    public static int indexoz;
    static boolean anyPathExists(TreeNode root, int[] arr) {
        if (!startoz) {
            if (root.val == arr[0]) {
                startoz = true;
                indexoz += 1;
            }
            // Check if first is the rizz
            if (arr.length == 1) {
                return true;
            }

        }

        else if (startoz) {
            if (root.val == arr[indexoz]) {
                if (indexoz >= arr.length -1) {
                    return true;
                }
                indexoz += 1;
            } else {
                startoz = false;
                indexoz = 0;
            }
        }

        if (root.left == null && root.right == null) {
            return false;
        }

        boolean a = false;
        boolean b = false;


        if (root.left != null) {
            b = anyPathExists(root.left, arr);
        }

        if (root.right != null) {
            a = anyPathExists(root.right, arr);
        }


        return a || b;

    }


    // 23) Maximum path sum
    // Hard https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
    static int totalSooms;
    public static int maxPathSum(TreeNode root) {
        hMaxPathSum(root);
        return totalSooms;
    }
    public static int hMaxPathSum(TreeNode root) {
        // Base condition
        if (root == null) {
            return 0;
        }
        int left = hMaxPathSum(root.left);
        int right = hMaxPathSum(root.right);
        int pathSum1 = left + right + root.val;
        int pathSum2 = left +  root.val;
        int pathSum3 = right + root.val;

        int returnPathSum = Integer.max(root.val, Integer.max(pathSum2, pathSum3));

        int AMan = Integer.max(pathSum1, returnPathSum);

        if (AMan > totalSooms) {
            totalSooms = AMan;
        }

        return returnPathSum;
    }

    // 22) Sum root to leaf numbers
    int sumNumbss;
    public int sumNumbers(TreeNode root) {
        sumNumbers(root, 0);
        return sumNumbss;
    }
    public void sumNumbers(TreeNode root, int currentSum) {
        currentSum *= 10;
        currentSum += root.val;

        if (root.left == null && root.right == null) {
            sumNumbss += currentSum;
        }

        if (root.left != null) {
            sumNumbers(root.left, currentSum);
        }
        if (root.right != null) {
            sumNumbers(root.right, currentSum);
        }

    }

    // 21) Path sum
    // Easy https://leetcode.com/problems/path-sum/description/
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return hasPathSum(root, targetSum, 0);
    }
    public static boolean hasPathSum(TreeNode root, int targetSum, int currentSum) {
        currentSum += root.val;

        // Base condition
        if (root.right == null && root.left == null) {
            if (currentSum == targetSum) {
                return true;
            }
        }

        boolean a = false;
        boolean b = false;


        if (root.left != null) {
            a = hasPathSum(root.left, targetSum, currentSum);
        }
        if (root.right != null) {
            b = hasPathSum(root.right, targetSum, currentSum);
        }
        return a || b;
    }


    // 20) Serialize and deserialize binary tree
    // Hard
    // Things important
    // 1) How do you differentiate one number from another -> can use dots
    // 2) How to differentiate blank characters and the node values
    // I'm using in order traversal
    public static String serialize(TreeNode root) {
        StringBuffer st = new StringBuffer();
        serialize(root, st);

        return st.toString();
    }

    public static void serialize(TreeNode node, StringBuffer st) {
        if (node == null) {
            st.append('l');
            st.append(',');
            return;
        }
        st.append(node.val);
        st.append(',');
        serialize(node.left, st);
        serialize(node.right, st);
    }


    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        StringBuffer dataa = new StringBuffer(data);
        return helperDeserialize(dataa);
    }

    public static TreeNode helperDeserialize(StringBuffer data) {
        int nextSeparator = data.indexOf(",");
        String currentValue = data.substring(0, nextSeparator);
        if (currentValue.length() == 1) {
            char c = currentValue.charAt(0);
            if (c == 'l') {
                data.delete(0, nextSeparator + 1);
                return null;
            }
        }
        int value = Integer.parseInt(currentValue);

        TreeNode root = new TreeNode(value);
        root.left = helperDeserialize(data.delete(0, nextSeparator + 1));
        root.right = helperDeserialize(data);

        return root;
    }

    // 19) Construct tree from pre order and in order
    // Medium but hard https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    // This can be solved using hash maps
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return createTree(preorder, inorder);
    }
    public static TreeNode createTree(int[] preorder, int[] inorder) {
        TreeNode rootNode = new TreeNode(preorder[0]);

        if (preorder.length == 1) {
            return rootNode;
        }

        // Right branch head
        int startIndexOfRightSide = 0;
        for (int i = 0; i < preorder.length; i++) {
            if (inorder[i] == preorder[0]) {
                startIndexOfRightSide = i+1;
                break;
            }
        }
        int finalIndexOfLeftSide = startIndexOfRightSide - 1;

        if (finalIndexOfLeftSide > 0) {
            // The copy is exclusive not inclusive, so add 1 to it
            int[] leftTreePre = Arrays.copyOfRange(preorder, 1, finalIndexOfLeftSide + 1);
            int[] leftTreeIn = Arrays.copyOfRange(inorder, 0, finalIndexOfLeftSide);

            rootNode.left = createTree(leftTreePre, leftTreeIn);
        }



        if (startIndexOfRightSide < preorder.length) {
            int[] rightTreePre = Arrays.copyOfRange(preorder, startIndexOfRightSide, preorder.length);
            int[] rightTreeIn = Arrays.copyOfRange(inorder, startIndexOfRightSide, preorder.length);
            rootNode.right = createTree(rightTreePre, rightTreeIn);
        }


        return rootNode;
    }

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
