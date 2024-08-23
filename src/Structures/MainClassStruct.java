package Structures;

public class MainClassStruct {
    public static void main(String[] args) {
        HeapStructure heap = new HeapStructure();
        heap.add(5);
        heap.printHeap();
        heap.add(3);
        heap.printHeap();
        heap.add(1);
        heap.printHeap();
    }
}
