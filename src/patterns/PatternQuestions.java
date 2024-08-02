package patterns;

public class PatternQuestions {
    public static void main(String[] args) {
        for(int i = 0; i < 5; i++){
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void q1() {
        for(int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    static void q2() {
        for(int i = 0; i < 5; i++){
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

    }
}
