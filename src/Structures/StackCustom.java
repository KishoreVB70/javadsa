package Structures;

public class StackCustom {
    private int[] data;
    private  static final int DEFAULT_SIZE = 10;
    private int ptr;

    public StackCustom() {
        this(DEFAULT_SIZE);
    }

    public StackCustom(int size) {
        this.data = new int[size];
        ptr= -1;
    }

    //Peek
    public int peek() {
        return data[ptr];
    }

    // Push
    public void push(int i) {
        if (isFull()) {
            extendArray();
        }
        ptr++;
        data[ptr] = i;
    }

    // Pop
    public int pop(){
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return data[ptr--];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        for (int i = 0; i <= ptr; i++) {
            System.out.println(data[i]);
        }
    }

    private void extendArray() {
        int[] newData = new int[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public boolean isEmpty() {
        return ptr==-1;
    }

    public boolean isFull() {
        return ptr==data.length-1;
    }



}
