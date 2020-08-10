package com.sort;

public class InsertionSorter<K extends Comparable<? super K>> {

    /**
     * Sorts the elements in given array from `left` to `right` in lexicographic order using insertion sort algorithm.
     */

    public void sort(Pair<K, ?>[] array, int left, int right, String sortType) {
        if (sortType.equals("keys")) {
            for (int i = left + 1; i <= right; i++) {
                for (int j = i; (j > 0) && (array[j].getKey().compareTo(array[j - 1].getKey()) < 0); j--) {
                    Pair<K, ?> tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }

            return;
        } else if (sortType.equals("values")) {
            for (int i = left + 1; i <= right; i++) {
                for (int j = i; (j > 0) && (array[j].getValue() < array[j - 1].getValue()); j--) {
                    Pair<K, ?> tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }

            return;
        }

        return;
    }
}