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

        // If not balance, balance and return it
        return doRotation(node, value);


    }

    private Node doRotation(Node parent, int value) {
        // Obtain all the information required
        boolean firstLeft;
        boolean secondLeft;


        // Find the child direction
        Node child;
        if (value < parent.value) {
            child = parent.left;
            firstLeft = true;
        } else {
            child = parent.right;
            firstLeft = false;
        }

        // Find the grandchild direction
        if (value < child.value) {
            secondLeft = true;
        } else {
            secondLeft = false;
        }

        // Do the rotation for child if necessary
        if (firstLeft && !secondLeft) {
            child = leftRotate(child);
            parent.left = child;
        } else if (!firstLeft && secondLeft) {
            child = rightRotate(child);
            parent.left = child;
        }


        // Straight forward rotation -> Edges
        if (firstLeft) {
            parent = rightRotate(parent);
        } else  {
            parent = leftRotate(parent);
        }

        return parent;
    }
//    private Node doRotation(Node parent) {}

    private Node leftRotate(Node parent) {
        // Obtain the dominant child
        Node rightChild = parent.right;

        // Fix the left part -> Obtain non-dominant grand child -> right left grand child
        Node rightLeft = rightChild.left;
        rightChild.left = parent;
        parent.right = rightLeft;

        // Fix the right -> nothing needs to be fixed bro, all fine there
        return rightChild;

    }
    private Node rightRotate(Node parent) {
        // Obtain the dominant child
        Node leftChild = parent.left;

        // Fix the right part -> Obtain non-dominant grand child -> right left grand child
        Node leftRight = leftChild.right;
        leftChild.right = parent;
        parent.left = leftRight;

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
