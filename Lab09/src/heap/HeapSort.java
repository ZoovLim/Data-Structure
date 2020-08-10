package heap;

import java.util.Arrays;

public class HeapSort<E extends Comparable<? super E>> {
    private MinHeap<E> minHeap;
    private int n;
    private E[] array;

    private static final int SORT_A = 2;
    private static final int SORT_D = 3;

    public HeapSort(int n) {
        array = newArray(n);
        minHeap = new MinHeap<E>(array, 0, n);
        this.n = n;
    }

    public void add(E value) {
        minHeap.insert(value);
        minHeap.printHeap();
    }

    public void remove(E value) {
        int index = minHeap.find(value);
        if (index == -1) {
            System.out.println("There is no " + value + " in the Heap");
            return;
        }
        minHeap.printHeap();
        System.out.println("(last state)");
        E val = minHeap.remove(index);
        minHeap.printHeap();
        System.out.println("(after remove " + val + ")");
    }

    public void sort(int order) {
        int curr = 0;
        int size = minHeap.heapsize();
        E[] tmp = newArray(size);

        while (curr < size) {
            tmp[curr] = minHeap.removeMin();
            curr++;
        }

        curr = 0;
        while (curr < size) {
            minHeap.insert(tmp[curr]);
            curr++;
        }

        if (order == SORT_A) {
            curr = 0;
            while (curr < size) {
                System.out.print(tmp[curr] + " ");
                curr++;
            }
        } else {
            curr = size - 1;
            while (curr >= 0) {
                System.out.print(tmp[curr] + " ");
                curr--;
            }
        }
        System.out.println();
        minHeap.printHeap();
    }

    //This function is for allocating an generic array of size n
    private static <E> E[] newArray(int length, E... array) {
        return Arrays.copyOf(array, length);
    }
}
