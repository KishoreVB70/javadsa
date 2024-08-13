package ObjectOrientedProgramming;

public class Main {
    public static void main(String[] args) {
        GenArrayList<Integer> arr  = new GenArrayList<>();

        for (int i = 0; i < 15; i++) {
            arr.add(i*2);
        }

        System.out.println(arr);

    }
}
