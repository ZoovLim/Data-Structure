package ds.hash;

public class HashTable {
    private int[] table;
    private int c1, c2, c3;
    private int delMarker = -1;
    private int tableSize;

    public HashTable(int n) {
        tableSize = n;
        table = new int[tableSize];
    }

    public void create(int c1, int c2, int c3) {
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
    }

    public void insert(int key) {
        int i = 0;
        int m = quadProb(key, i);

        while (table[m] != 0) {
            m = quadProb(key, ++i);
        }

        table[m] = key;

        System.out.println("INSERT: " + key + " / INDEX: " + m);
    }

    public void delete(int key) {
        int i = 0;
        int m = quadProb(key, i);

        while (i != 577) {
            if (table[m] == 0) {
                System.out.println("Failed to find " + key);
                return;
            }
            if (table[m] == key) {
                table[m] = delMarker;
                System.out.println("DELETE: " + key + " / INDEX: " + m);
                return;
            }
            m = quadProb(key, ++i);
        }

        System.out.println("Failed to find " + key);
    }

    public void search(int key) {
        int i = 0;
        int m = quadProb(key, i);

        while (i != 577) {
            if (table[m] == 0) {
                System.out.println("Failed to find " + key);
                return;
            }
            if (table[m] == key) {
                System.out.println("SEARCH: " + key + " / INDEX: " + m);
                return;
            }
            m = quadProb(key, ++i);
        }

        System.out.println("Failed to find " + key);
    }

    public void printAll() {
        boolean start = false;

        for (int i = 0; i < 577; i++) {
            if (!start) {
                if (table[i] != 0) {
                    System.out.print(table[i] + "(" + i + ")");
                    start = true;
                }
            } else {
                if (table[i] != 0) {
                    System.out.print(", " + table[i] + "(" + i + ")");
                }
            }
        }

        System.out.println();
    }

    private int quadProb(int k, int i) {
        return ((k % tableSize) + (c1 * (int) Math.pow(i, 2) + (c2 * (int) Math.pow(i, 1)) + c3)) % 577;
    }
}
