package Structures;

public class BinQuestions {
    public static void main(String[] args) {
        BinSearchTree tree = new BinSearchTree(21);
        tree.insert(20);
        tree.insert(22);
        tree.insert(19);
        tree.insert(18);
        tree.remove(22);
        tree.display();
    }
}
