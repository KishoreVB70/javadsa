package Structures;

public class Avlt {
    Node root;
    Avlt(int value) {
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

        if (isBalanced(node)) {
            return node;
        }

        // Do the rotation Thing

        // 1 -> Find the type of rotation that you want to do
        // Obtain all the people required
        boolean firstLeft;
        boolean secondLeft;

        // 1) This node is the parent
        Node parent = node;

        // 2) Child is the one which is adequate for the value
        Node child;
        if (value < node.value) {
            child = node.left;
            firstLeft = true;
        } else {
            child = node.right;
            firstLeft = false;
        }

        // 3) Get the grand child
        Node grandChild;
        if (value < child.value) {
            grandChild = child.left;
            secondLeft = true;
        } else {
            grandChild = child.right;
            secondLeft = false;
        }

        // Do the rotation

        // Straight forward rotation -> Edges
        if (firstLeft && secondLeft) {
            parent = rightRotate(parent);
        } else if (!firstLeft && !secondLeft) {
            parent = leftRotate(parent);
        }

        // Not straight forward
        if (firstLeft && !secondLeft) {
            child = leftRotate(child);
            parent.left = child;
            parent = rightRotate(parent);
        } else {
            child = rightRotate(child);
            parent.left = child;
            parent = leftRotate(parent);
        }

        return parent;
    }

    private Node leftRotate(Node node) {
        // Obtain the dominant child
        Node rightChild = node.right;

        // Fix the left part -> Obtain non-dominant grand child -> right left grand child
        Node rightLeft = rightChild.left;
        rightChild.left = node;
        node.right = rightLeft;

        // Fix the right -> nothing needs to be fixed bro, all fine there
        return rightChild;

    }
    private Node rightRotate(Node node) {
        // Obtain the dominant child
        Node leftChild = node.left;

        // Fix the right part -> Obtain non-dominant grand child -> right left grand child
        Node leftRight = leftChild.right;
        leftChild.right = node;
        node.left = leftRight;

        // Fix the left -> nothing needs to be fixed bro, all fine there
        return leftChild;
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
