package ObjectOrientedProgramming;

import java.util.Arrays;

public class GenArrayList<T> {
    private Object[] data;
    private int size;
    final static int INITIAL_SIZE = 10;

    int length() {
        return size;
    }

    GenArrayList() {
        data = new Object[INITIAL_SIZE];
    }

    T get(int index) {
        return (T)data[index];
    }

    T pop() {
        if (size > 0) {
            return (T)data[--size];
        }
        return (T)data[size];
    }

    void add(T value) {
        data[size] = value;
        size++;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
