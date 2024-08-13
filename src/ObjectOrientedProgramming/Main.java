package ObjectOrientedProgramming;

public class Main {
    public static void main(String[] args) {
        MyArrayList arr = new MyArrayList();
        GenArrayList<String> arr1  = new GenArrayList<>();
        arr1.add("Foilor");
        System.out.println(arr1);
        System.out.println(arr1.length());
    }
}
