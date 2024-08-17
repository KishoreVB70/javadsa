package Structures;

public class BinSearchTree {
    Node root;
    BinSearchTree(int value) {
        this.root = new Node(value);
    }

    void insert(int value) {
        insert(root, value);
    }
    private void insert(Node node, int value) {
        if (node == null) {
            node.value = value;
            return;
        }

        if (node.value > value) {
            insert(node.right, value);
        }
        else {
            insert(node.left, value);
        }
    }

    boolean remove(int value) {
        Node target = get(value);
        if (target == null) {
            return false;
        }
        target = null;
        return true;
    }

    Node get (int value) {
        return get(root, value);
    }
    private Node get(Node node, int value) {
        // Negative base condition
        if (node == null) {
            return null;
        }
        // Positive base condition
        if (node.value == value) {
            return node;
        }

        if (value > node.value) {
            return get(node.right, value);
        } else {
            return get(node.right, value);
        }
    }



    class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }
}
