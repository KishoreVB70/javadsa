package Others;

import java.util.Random;

public class Misc {
    public static void main(String[] args) {
        System.out.println(createRandomString(30));
    }

    static String createRandomString(int num) {
        StringBuffer st = new StringBuffer();
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
//            char c = (char) (97 + ( (int)  (26 * rand.nextFloat())  ));
            char c = (char) (97 + rand.nextInt(26));
            st.append(c);
        }
        return st.toString();
    }
}
