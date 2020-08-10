package com.sort;

public class HybridSorter<K extends Comparable<? super K>> {
    InsertionSorter<K> insertionSort = new InsertionSorter<K>();
    private final int RUN = 32;

    /**
     * Sorts the elements in given array from `left` to `right in lexicographic order
     * using the hybrid sorting algorithm.
     */

    public Pair<K, ?> search(Pair<K, ?>[] array, int k, String sortType) {
        return array[k];
    }

    public void sort(Pair<K, ?>[] array, int left, int right, String sortType) {
        if (array.length <= RUN) {
            insertionSort.sort(array, left, right, sortType);
            return;
        }

        int pivotIndex = (left + right) / 2;
        Pair<K, ?> pivot = array[pivotIndex];

        Pair<K, ?> tmp1 = pivot;
        array[pivotIndex] = array[right];
        array[right] = tmp1;

        int k = partition(array, left - 1, right, pivot, sortType);

        if (k == -1) {
            System.out.println("Not valid Sort Type");
            return;
        }

        Pair<K, ?> tmp2 = array[k];
        array[k] = array[right];
        array[right] = tmp2;

        if ((k - left) > 1) {
            sort(array, left, k - 1, sortType);
        }
        if ((right - k) > 1) {
            sort(array, k + 1, right, sortType);
        }

        return;
    }

    public int partition(Pair<K, ?>[] array, int left, int right, Pair<K, ?> pivot, String sortType) {
        if (sortType.equals("keys")) {
            int l = left;
            int r = right;

            do {
                while (array[++l].getKey().compareTo(pivot.getKey()) < 0) ;
                while ((r != 0) && (array[--r].getKey().compareTo(pivot.getKey()) > 0)) ;

                Pair<K, ?> tmp = array[l];
                array[l] = array[r];
                array[r] = tmp;
            } while (l < r);

            Pair<K, ?> tmp = array[l];
            array[l] = array[r];
            array[r] = tmp;

            return l;
        } else if (sortType.equals("values")) {
            int l = left;
            int r = right;

            do {
                while (array[++l].getValue() < pivot.getValue()) ;
                while ((r != 0) && (array[--r].getValue() > pivot.getValue())) ;

                Pair<K, ?> tmp = array[l];
                array[l] = array[r];
                array[r] = tmp;
            } while (l < r);

            Pair<K, ?> tmp = array[l];
            array[l] = array[r];
            array[r] = tmp;

            return l;
        }

        return -1;
    }
}
