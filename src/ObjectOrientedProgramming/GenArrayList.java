package ObjectOrientedProgramming;

import java.util.Arrays;

public class GenArrayList<T> {
    private Object[] data;
    private int size;
    final private static int INITIAL_SIZE = 10;

    int length() {
        return size;
    }

    GenArrayList() {
        data = new Object[INITIAL_SIZE];
    }

    T get(int index) {
        return (T) (data[index]);
    }

    T pop() {
        if (size > 0) {
            return (T)data[--size];
        }
        return (T)data[size];
    }

    void add(T value) {
        if (size == data.length) {
            resize();
        }
        data[size++] = value;
    }

    void set(T value, int index) {
        data[index] = value;
    }

    private void  resize() {
        Object[] temp = new Object[size * 2];
        // Copy over the data
        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}
