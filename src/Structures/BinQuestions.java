package Structures;

import java.util.*;

public class BinQuestions {
    public static void main(String[] args) {
        BinSearchTree tree = new BinSearchTree(7);
        for (int i = 0; i < 6; i++) {
            tree.insert(i*2);
        }
        tree.prettyDisplay();
        bfsPrint(tree.root);
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

    // 2) Returning every level as a separate list in an Array list
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
}
