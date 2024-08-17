package Structures;

import java.util.Scanner;

public class BinTree {
    Node root;

    BinTree() {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the root node: ");
        int val = in.nextInt();
        root = new Node(val);
        initializer(root, new Scanner(System.in));
    }

    BinTree(boolean hi) {
        if (!hi) {
            return;
        }
        root = new Node(15);
        root.left = new Node(6);
        root.left.right = new Node(14);
        root.left.left = new Node(8);
        root.left.left.right = new Node(10);

        root.right = new Node(9);
        root.right.right = new Node(29);
        root.right.left = new Node(49);


    }

    private void initializer(Node node, Scanner in) {
        System.out.print("Do you want to go left of " + node.value + ": ");
        boolean l = in.nextBoolean();

        if (l) {
            System.out.print("Value: ");
            int val = in.nextInt();
            node.left = new Node(val);
            initializer(node.left, in );
        }

        System.out.print("Do you want to go right of " + node.value + ": ");
        boolean r = in.nextBoolean();

        if (r) {
            System.out.print("Value: ");
            int val = in.nextInt();
            node.right = new Node(val);
            initializer(node.right, in );
        }
    }

    void display() {
        display(root, 0);
    }
    private void display(Node node, int level) {
        if (node == null) {
            return;
        }

        display(node.right, level+1);

        if (level != 0) {
            for (int i = 0; i < level-1; i++) {
                System.out.print("|\t\t");
            }
            System.out.println("|------>" + node.value);
        }
        // root node -> no indentation or spacing or whatever
        else {
            System.out.println(node.value);
        }
        display(node.left, level+1);
    }



    private class Node {
        int value;
        Node left;
        Node right;

        Node(int val) {
            this.value = val;
        }
    }
}
