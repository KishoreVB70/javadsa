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
        if (isFull()) {
            System.out.println("Queue is full");
        }

        if (isEmpty()) {
            start = 0;
            end = -1;
        }

        data[++end] = i;
    }

    public int remove() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        }
        return data[start++];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        for (int i = start; i <= end ; i++) {
            System.out.print(data[i] + ",");
        }
        System.out.println();
    }

    private boolean isFull () {
        return end == data.length - 1;
    }

    private boolean isEmpty () {
        return start == end + 1;
    }















}
