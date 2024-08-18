package Structures;

public class SegmentTre {

    private class Node {
        int value;
        int start;
        int end;
        Node left;
        Node right;

        Node( int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

    private static final int DEFAULT_VALUE = 0;

    SegmentTre(int[] arr){
        root = populate(new Node(0, arr.length-1), arr) ;
    }

    private Node populate(Node node, int[] arr) {
        // Base condition
        if (node.start == node.end) {
            node.value = arr[node.start];
            return node;
        }

        int m = (node.start + node.end) / 2;

        node.left = populate(new Node(node.start,m),arr);
        node.right = populate(new Node(m+1,node.end),arr);

        node.value = node.right.value + node.left.value;

        return node;
    }

    Node root;
    public int findValueInRange(int s, int e) {
        return findValueInRange(root,s, e );
    }

    private int findValueInRange(Node node, int s, int e) {
        // Negative base condition
        if (node.start > e || node.end < s) {
            return DEFAULT_VALUE;
        }

        // Positive base condition
        if (node.start >= s && node.end <= e) {
            return node.value;
        }


        // Partially inside
        int left = findValueInRange(node.left, s, e);
        int right = findValueInRange(node.right, s, e);

        return left + right;
    }

    public int updateValue(int target, int val) {return updateValue(root, target, val);}

    private int updateValue(Node node, int target, int val) {
        // Negative base condition
        if (node.start > target || node.end < target) {
            return node.value;
        }

        // Positive base condition
        if (node.start == target && node.end == target) {
            node.value = val;
            return node.value;
        }

        // Continue condition
        int left = updateValue(node.left, target, val);
        int right = updateValue(node.right, target, val);

        return left + right;
    }

    void prettyDisplay() {prettyDisplay(root, 0);}
    private void prettyDisplay(Node node, int level) {
        if (node == null) {
            return;
        }

        prettyDisplay(node.right, level+1);

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
        prettyDisplay(node.left, level+1);
    }
    void display() {display(root, "Root is: ");}
    private void display(Node node, String text) {
        if (node == null) {
            return;
        }
        System.out.println(text + node.value + " S: " + node.start + " E: " + node.end);
        display(node.left, "Left of " + node.value + " is: " );
        display(node.right, "Right of " + node.value + " is: ");
    }

















}
