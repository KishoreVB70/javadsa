package Structures;

public class HeapStructure {

    private int[] arr;
    private int lastIndex;
    HeapStructure() {
        arr = new int[10];
        lastIndex = 0;
    }

    public void add(int a) {
        arr[lastIndex] = a;
        int currentIndex = lastIndex;
        int parentIndex = findParent(currentIndex);
        while (currentIndex > 0 &&  arr[parentIndex] > arr[currentIndex]) {
            swap(parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = findParent(currentIndex);
        }
        lastIndex++;
    }

    public int remove() {
        int removed = arr[0];
        swap(0, lastIndex);
        lastIndex--;

        int currentIndex = 0;
        int child1 = 1;
        int child2 = 2;
        while (child1 < lastIndex) {
            // If child2 is out of bound
            if (! (child2 < lastIndex) ) {
                swap(currentIndex, child1);
                break;
            }

            // Both are in bound
            if (arr[child1] < arr[child2]) {
                swap(currentIndex, child1);
                currentIndex = child1;
            } else {
                swap(currentIndex, child2);
                currentIndex = child2;
            }
            child1 = currentIndex *2 + 1;
            child2 = currentIndex *2 + 2;
        }

        return removed;
    }
    public void printHeap() {
        for (int i = 0; i < lastIndex; i++) {
            System.out.println(arr[i]);
        }
    }

    private int findParent(int a) {
        if (a % 2 == 0) {
            return (a/2) - 1;
        }
        return a/2;
    }
    private void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
