package Structures;

public class BinSearchTree {
    Node root;
    BinSearchTree(int value) {
        this.root = new Node(value);
    }

    void insert(int value) {
        this.root = insert(root, value);
    }
    private Node insert(Node node, int value) {
        if (node == null) {
            Node newNode = new Node(value);
            return newNode;
        }
        if (value < node.value) {
            node.left = insert(node.left, value);
        }

        if (value > node.value) {
            node.right = insert(node.right, value);
        }

        node.height = Integer.max(height(node.right), height(node.left)) + 1;

        return node;
    }

    void remove(int value) {
        this.root = remove(root, value);
    }
    private Node remove(Node node, int value) {
        if (node == null) {
            return null;
        }

        if (node.value == value) {
            return null;
        }

        if (value < node.value) {
            node.left = remove(node.left, value);
        }

        if (value > node.value){
            node.right = remove(node.right, value);
        }

        node.height = Integer.max(height(node.right), height(node.left)) + 1;

        return node;
    }

    boolean isBalanced() {
        return isBalanced(root);
    }
    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int abs  = Math.abs(height(node.left) - height(node.right));
        return  ( abs<=1 && isBalanced(node.left) && isBalanced(node.right));
    }

    Node get (int value) {return get(root, value);}
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
        }

        return get(node.left, value);
    }
    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }
    void display() {display(root, "Root is: ");}
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
        int height;

        Node(int value) {
            this.value = value;
        }
    }
}
