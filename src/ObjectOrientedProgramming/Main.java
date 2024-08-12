package ObjectOrientedProgramming;

public class Main {
    public static void main(String[] args) {
        MyArrayList arr = new MyArrayList();
        System.out.println(arr.length());
        arr.add(21);
        System.out.println(arr.get(0));
        System.out.println(arr.length());
    }
}
