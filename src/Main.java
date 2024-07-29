import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter int a");
        int num1= input.nextInt();

        System.out.print("Enter int b");
        int num2= input.nextInt();

        System.out.print("Input 1 for addition and any other num for subtraction");
        int operation= input.nextInt();

        int result;
        if( operation == 1) {
            result = num1 + num2;
        } else {
            if (num1 > num2) {
                result = num1 - num2;
            } else {
                result = num2 - num1;
            }
        }

        System.out.println("Result of your operation is: " + result);
    }
}