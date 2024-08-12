package ObjectOrientedProgramming;

import java.util.Arrays;

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
        if (size > 0) {
         return data[--size];
        }
        return -1;
    }

    void add(int value) {
        data[size] = value;
        size++;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
