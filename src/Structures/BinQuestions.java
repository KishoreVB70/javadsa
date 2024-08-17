package Structures;

public class BinQuestions {
    public static void main(String[] args) {
        Avlt tree = new Avlt(42);
        tree.insert(41);
        tree.insert(43);
        tree.insert(40);
        tree.insert(44);
        tree.insert(45);
        tree.insert(47);
        tree.insert(46);
        tree.display();
    }
}
