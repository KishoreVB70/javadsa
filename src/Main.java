import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();

        if (a >= b && a >= c) {
            System.out.println("a is the largest");
        } else if (b >= a && b >= c) {
            System.out.println("b is the largest");
        } else {
            System.out.println("c is the largest");
        }
    }
}