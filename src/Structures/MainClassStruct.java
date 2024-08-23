package Structures;

public class MainClassStruct {
    public static void main(String[] args) {
        HeapStructure heap = new HeapStructure();
        heap.add(5);
        heap.add(12);
        heap.add(9);
        heap.add(82);
        heap.add(3);
        heap.add(1);
        heap.printHeap();
        System.out.println("----");
        heap.remove();
        heap.remove();
        heap.remove();
        heap.printHeap();
    }
}
