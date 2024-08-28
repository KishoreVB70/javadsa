package Others;

public class PatternQuestions {
    public static void main(String[] args) {
        q30(5);
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
    static void q3(){
        for(int i = 5; i >= 0; i--){
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

    }
    static void q4(){
        for(int i = 1; i <= 5; i++){
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }
    static void q5(){
        int i = 1;
        while (i < 5 ) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
            i++;
        }
        while (i >= 0) {
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
            i--;

        }

    }
    static void q6(){
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= (5-i); j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < i ; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    static void q7(int n){
        for (int i = 0; i < n ; i++) {
            for (int j = 1; j <= (n+i)-(n); j++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= n-i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    static void q8(){
        for (int i = 1; i <= 5; i++) {
            // Number of gaps
            int numberOfGapsPerSide = (10 - (2*i))/2;

            for (int j = 1; j <= numberOfGapsPerSide; j++) {
                System.out.print(" ");
            }

            // Number of stars
            int numberOfStars = (i + (i -1));

            for (int j = 1; j <= numberOfStars ; j++) {
                System.out.print("*");
            }

            for (int j = 1; j <= numberOfGapsPerSide; j++) {
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    static void q28(int n) {
        for (int i = 1; i < 2*n; i++) {
            if (i <= n) {
                // Print gaps
                for (int j = 1; j <= n-i; j++) {
                    System.out.print(" ");
                }

                // Print start
                for (int j = 0; j < i; j++) {
                    System.out.print("* ");
                }
            } else {
                // Print gaps
                for (int j = 1; j <= i-n; j++) {
                    System.out.print(" ");
                }

                // Print start
                for (int j = 0; j < (2*n)-i; j++) {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }

    }

    static void q30(int n) {
        for (int i = 1; i <= n; i++) {
            // Print gaps
            for (int j = 0; j < n-i ; j++) {
                System.out.print(" ");
            }

            // Print numbers
            // Descending loop
            for (int j = i; j >= 1 ; j--) {
                System.out.print(j);
            }

            //Ascending loop
            for (int j = 2; j <= i ; j++) {
                System.out.print(j);
            }
            System.out.println();

        }
    }

    static void q9(){

    }
    static void q10(){

    }
    static void q11(){

    }

}
