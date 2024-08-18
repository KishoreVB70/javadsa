package Structures;

public class BinQuestions {
    public static void main(String[] args) {
        int[] bot = {3,8,6,7,-2,-8,4,9};
        SegmentTre tree = new SegmentTre(bot);
        tree.display();
        System.out.println(tree.findValueInRange(1, 3));
    }
}
