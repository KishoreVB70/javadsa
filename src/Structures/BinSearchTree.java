package Structures;

public class BinSearchTree {
    Node root;
    BinSearchTree(int value) {
        this.root = new Node(value);
    }

    void insert(int value) {
        insert(root, value);
    }
    private Node insert(Node node, int value) {
        if (node == null) {
            Node newNode = new Node(value);
            return newNode;
        }

        if (value > node.value) {
            Node returner = insert(node.right, value);
            if (returner != null) {
                node.right = returner;
            }
        }
        else {
            Node returner = insert(node.left, value);
            if (returner != null) {
                node.left = returner;
            }
        }
        return null;
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

    void display() {
        display(root, "Root is: ");
    }
    private void display(Node node, String text) {
        if (node == null) {
            return;
        }
        System.out.println(text + node.value);
        display(node.left, "Left of " + node.value + " is: ");
        display(node.right, "Right of " + node.value + " is: ");
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
