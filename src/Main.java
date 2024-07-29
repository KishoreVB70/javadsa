import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        greatestOfThree();
        upperOrLower();

    }

    private static void upperOrLower() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a character: ");
        char c = in.next().trim().charAt(0);
        if (c > 90) {
            System.out.println("Character is lower case");
        } else {
            System.out.println("character is upper case");
        }
    }

    private static void greatestOfThree() {
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