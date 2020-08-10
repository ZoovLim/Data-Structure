import java.net.PasswordAuthentication;
import java.util.concurrent.TimeUnit;

public class SortManager {
    private int MAX_NUM;
    private Node<Integer, String>[] items;
    int N = -1;

    public SortManager() {
        MAX_NUM = 999;
        items = new Node[MAX_NUM];
    }

    /**
     * Input: line of items
     * Set items in the line.
     * Print list of items by calling 'print' function at the end.
     * Hint: use "String".split(" ") to split each item
     * use "String".split("_") to split key and item
     * use Integer.parseInt("String") to convert "String" into a integer.
     */
    public void setItems(int n, String itemLine) {
        this.N = n;
        String[] newItems = itemLine.split(" ");
        items = new Node[MAX_NUM];
        for (int i = 0; i < n; i++) {
            String[] itemInfo = newItems[i].split("_");
            Node<Integer, String> newItem = new Node<>(Integer.parseInt(itemInfo[0]), itemInfo[1]);
            items[i] = newItem;
        }
        print();
    }

    /**
     * Sort the items in the this.items array.
     * Print list of items by calling 'print' function at the end.
     * Hint. you have to compare Integer type values with 'Integer.compareTo(Competitor)' function.
     * Hint. you can also use 'print' function for debugging.
     * Hint. the swap operation makes selection sort unstable. Then how can we do this without the swap operation?
     */
    public void doStableSelectionSort() {
        for (int i = 0; i < N - 1; i++) {
            int minIndex = i;
            Integer minKey = items[i].getKey();
            String minValue = items[i].getItem();

            for (int j = i + 1; j < N; j++) {
                Integer key = items[j].getKey();
                String value = items[j].getItem();

                if (key.compareTo(minKey) < 0) {
                    minKey = key;
                    minValue = value;
                    minIndex = j;

                } else if ((key.compareTo(minKey) == 0) && (value.compareTo(minValue) < 0)) {
                    minKey = key;
                    minValue = value;
                    minIndex = j;
                }
            }

            Node<Integer, String> temp = null;
            temp = items[i];
            items[i] = items[minIndex];
            items[minIndex] = temp;
        }
        print();
    }

    /**
     * Print the list of nodes in the 'this.items'.
     * You should use 'Node.toString()' when you print key and item of the node.
     * e.g. System.out.println(items[i].toString())
     */
    public void print() {
        if (this.N == -1) {
            System.out.println("There is no items to print");
        }
        for (int i = 0; i < N; i++) {
            System.out.print(items[i]);
        }
        System.out.println();
    }

    /**
     * Check 'this.items' is sorted and if it was sorted with the stable sorting method.
     * Print "Unsorted" if "this.items" are not sorted
     * Print "Sorted, but not stable" if "this.items" are sorted but not stable.
     * Print "Sorted and stable." if "this.items" are both sorted and stable.
     * Assume, all items are given lexicographical order at first  and there are no duplicate items.
     * Thus, two items with same keys, but not following the lexicographical order means that sorting method wasn't stable.
     */
    public void check() {
        boolean stability = true;

        for (int i = 0; i < N - 1; i++) {
            if (items[i].getKey().compareTo(items[i + 1].getKey()) > 0) {
                System.out.println("Unsorted.");
                return;
            }

            if (items[i].getItem().equals(items[i + 1].getItem()) && items[i].getItem().compareTo(items[i + 1].getItem()) > 0) {
                System.out.println(i);
                stability = false;
            }
        }

        if (stability) {
            System.out.println("Sorted and stable.");
        } else {
            System.out.println("Sorted, but not stable.");
        }
    }

    /* Code below this line is for optional problem*/

    /**
     * Do stable selection sort with showing
     * Hint. You can use makeDelayMs(1000) function to make some delay. (for 1000ms)
     */
    public void showStableSelectionSort() {
        int n = N;
        for (int i = 1; i <= n; i++) {
            N = i;
            doStableSelectionSort();
            N = n;
            show_all(i);
            makeDelayMs(3000);
        }
    }

    /**
     * Print a line for the given node.
     * (Item)/ [ ] [ ] ...[ ] (number of boxes == Key)
     * e.g. K/ [ ] [ ] [ ] for the node with item:K, key: 3
     */
    public void show_one_node(Node<Integer, String> node, boolean star) {
        int k = node.getKey();
        String it = node.getItem();
        String l = "" + it + "/ ";
        for (int count = 0; count < k; count++) {
            if (star) {
                l += "[*] ";
            } else {
                l += "[ ] ";
            }
        }
        System.out.println(l);
    }

    /**
     * Do show_one_node for every items in the 'this.items' (except for the item index over N).
     */
    public void show_all(int fix) {
        for (int i = 0; i < this.N; i++) {
            if (i <= fix) {
                show_one_node(this.items[i], true);
            } else {
                show_one_node(this.items[i], false);
            }
        }
        System.out.println();
    }

    public void makeDelayMs(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            System.out.format("IOExpection %s%n", e);
        }
    }
}
