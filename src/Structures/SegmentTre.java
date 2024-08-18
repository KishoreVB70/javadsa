package Structures;

public class SegmentTre {

    private class Node {
        int value;
        int start;
        int end;
        Node left;
        Node right;

        Node(int value, int start, int end) {
            this.value = value;
            this.start = start;
            this.end = end;
        }

    }

    private static final int DEFAULT_VALUE = 0;
    SegmentTre(){}

    Node root;
    public int findValue(int s, int e) {
        return findValue(root,s, e );
    }

    private int findValue(Node node, int s, int e) {
        // Negative base condition
        if (node.start > e || node.end < s) {
            return DEFAULT_VALUE;
        }

        // Positive base condition
        if (node.start >= s && node.end <= e) {
            return node.value;
        }


        // Partially inside
        int left = findValue(node.left, s, e);
        int right = findValue(node.right, s, e);

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

















}
