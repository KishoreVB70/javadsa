package Structures;

import java.util.*;

public class BinQuestions {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2147483647);
        root.left = new TreeNode(2147483647);
        root.right = new TreeNode(2147483647);
        System.out.println(averageOfLevels(root));
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
            if (currentNode == target) {
                if (!q.isEmpty()) {
                    return q.poll();
                }
                return null;
            }

            if (currentNode.left != null) {
                q.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                q.offer(currentNode.right);
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
                if (currentNode == target) {
                    if (i+1 == size) {
                        return null;
                    }
                    return q.poll();
                }
                if (currentNode.left != null) {
                    q.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    q.offer(currentNode.right);
                }
            }



        }

        return null;
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


    static class Node {
        int value;
        BinSearchTree.Node left;
        BinSearchTree.Node right;
        int height;

        Node(int value) {
            this.value = value;
        }
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
