package Structures;

public class QueueCircularCust {
    private int start;
    private int end;
    private int[] data;
    private  static final int DEFAULT_SIZE = 10;

    public QueueCircularCust() {
        this(DEFAULT_SIZE);
    }

    public QueueCircularCust(int size) {
        this.data = new int[size];
        start = 0;
        end = -1;
    }

    public void add(int i) {
        // Check if stack is full
        if (isFull()) {
            resize();
        }

        // Next end spot = data
        end++;
        data[end % data.length] = i;
    }

    public int remove() {
        // If no elements
        if (isEmpty()) {
            System.out.println("Stack is empty, can't remove");
        }


        // If it is the last element -> set to pre historic conditions
        if (start % data.length == end % data.length) {
            int removed = data[start];
            start = 0;
            end = -1;
            return removed;
        }

        // If normal, return value, and move ahead
        return data[start++];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        for (int i = start; i <= end ; i++) {
            System.out.print(data[i % data.length] + ",");
        }
        System.out.println();
    }

    private void resize() {
        int[] newArray = new int[data.length * 2];
        int j = 0;
        for (int i = start; i <= end; i++) {
            newArray[j] = data[i % data.length];
            j++;
        }
        data = newArray;
        start = 0;
        end = j;
    }

    private boolean isFull () {
        if (end == -1) {
            return false;
        }
        return ((end+1) % data.length == start);
    }

    private boolean isEmpty () {
        return end == -1;
    }















}
