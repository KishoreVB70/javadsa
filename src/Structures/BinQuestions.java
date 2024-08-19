package Structures;

import java.util.ArrayList;

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
}
