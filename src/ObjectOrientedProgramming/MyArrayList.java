package ObjectOrientedProgramming;

public class MyArrayList {
    private int[] data;
    private int size;
    final static int INITIAL_SIZE = 10;

    int length() {
        return size;
    }

    MyArrayList () {
        data = new int[INITIAL_SIZE];
    }

    int get(int index) {
        return data[index];
    }

    int pop() {
         return data[--size];
    }

    void add(int value) {
        data[size] = value;
        size++;
    }
}
