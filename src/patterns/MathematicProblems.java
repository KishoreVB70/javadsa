package patterns;

public class MathematicProblems {
    public static void main(String[] args) {
        System.out.println(isOdd(21));
    }

    static boolean isOdd(int n) {
        return ((n & 1) == 1);
    }
}

