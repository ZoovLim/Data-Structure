package ds;

import java.util.Arrays;

/**
 * General Tree class implementation for UNION/FIND
 */
public class ParPtrTree {
    private Integer[] array; // Node array
    private Integer[] size; // groupsize array. Consider only values of root nodes. We don't need to care others.
    private int maxSize;

    public ParPtrTree(int maxsize) {
        this.maxSize = maxsize;
        array = new Integer[this.maxSize]; // Create node array
        size = new Integer[this.maxSize];
        for (int i = 0; i < this.maxSize; i++) {
            array[i] = null;
            size[i] = 1;
        }
    }

    public void clear() {
        array = new Integer[this.maxSize]; // Create node array
        size = new Integer[this.maxSize];
        for (int i = 0; i < this.maxSize; i++) {
            array[i] = null;
            size[i] = 1;
        }
    }

    /**
     * Determine if nodes are in different trees
     */
    public boolean differ(Integer a, Integer b) {
        return find(a) != find(b);
    }

    /**
     * Merge two subtrees using weighted union rule
     */
    public void union(Integer a, Integer b) {
        Integer r1 = find(a);
        Integer r2 = find(b);

        if (!r1.equals(r2)) {
            if (size[r1] >= size[r2]) {
                array[r2] = r1;
                size[r1] += size[r2];
            } else {
                array[r1] = r2;
                size[r2] += size[r1];
            }
        }
    }

    /**
     * Find the root of the node using path compression
     */
    public Integer find(Integer curr) {
        if (array[curr] == null) return curr;
        array[curr] = find(array[curr]);
        return array[curr];
    }

    /**
     * Return the size of the graph that the node belongs to
     */
    public Integer groupSize(Integer curr) {
        return this.size[find(curr)];
    }

    public Integer[] getSize() {
        return this.size;
    }

    public Integer[] getArray() {
        return this.array;
    }

    public void print() {
        System.out.println(Arrays.toString(array).replace("null", "N"));
    }
}
