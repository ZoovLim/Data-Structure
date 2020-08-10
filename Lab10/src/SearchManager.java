import java.math.BigInteger;
import java.net.PasswordAuthentication;
import java.util.concurrent.TimeUnit;

public class SearchManager {
    BigInteger[] items;
    int N = -1;

    public SearchManager() {
    }

    /**
     * Set items for the 'this.itmes' array using arithmetic or geometric sequence.
     * Print the elements in the array using 'print' function at the end of the code.
     * Hint. You have to use BigInteger1.add(BigInteger2) or BigInteger1.multiply(BigInteger2)
     * when you add or multiply BigIntegers.
     * Hint. You have to use 'new BigInterger("STRING TYPE VALUE")' to generate new BigInteger Instance.
     * e.g. a = new BigInteger("1234567890123456789")
     *
     * @param type 'A' for arithmetic, 'G" for geometric sequence.
     * @param n    the number of elements.
     * @param a0   the initial value.
     * @param d    commom difference/ ratio of the sequence.
     */
    public void setItems(char type, int n, int a0, int d) {
        this.N = n;
        this.items = new BigInteger[n];

        if (type == 'A') {
            items[0] = new BigInteger(Integer.toString(a0));
            BigInteger diff = new BigInteger(Integer.toString(d));

            for (int i = 1; i < n; i++) {
                items[i] = new BigInteger("0");
                items[i] = items[i - 1].add(diff);
            }
        } else if (type == 'G') {
            items[0] = new BigInteger(Integer.toString(a0));
            BigInteger ratio = new BigInteger(Integer.toString(d));

            for (int i = 1; i < n; i++) {
                items[i] = new BigInteger("1");
                items[i] = items[i - 1].multiply(ratio);
            }
        }
        print();
    }

    /**
     * Find the index of the target in "this.items" using jump search and count the number of comparisons.
     * Print "[J] Index: (index), count: (count)" if you find the target.
     * Print "[J] Not found, count: (count)" if there is no target in the "this.items".
     * Hint. Use BigInteger1.compareTo(BigInteger2) when you compare two BigInteger instances.
     *
     * @param target BigInteger type of target value
     * @return index of the target value
     */
    public int doJumpSearch(BigInteger target) {
        if (this.N == -1) {
            System.out.println("[J] The array is empty.");
            return -1;
        }

        int k = (int) Math.sqrt(N);
        int comp = 0;
        int curr = k - 1;

        while (curr < N) {
            if (target.compareTo(items[curr]) > 0) {
                comp++;
                curr += k;
            } else if (target.compareTo(items[curr]) == 0) {
                comp++;
                System.out.println("[J] Index: " + curr + ", count: " + comp);
                return curr;
            } else {
                comp++;
                curr -= k - 1;
                break;
            }
        }

        for (int i = 0; i < k - 1; i++) {
            if (curr + i >= N) {
                break;
            } else if (target.compareTo(items[curr + i]) > 0) {
                comp++;
            } else if (target.compareTo(items[curr + i]) == 0) {
                comp++;
                System.out.println("[J] Index: " + (curr + i) + ", count: " + comp);
                return curr + i;
            } else {
                comp++;
                System.out.println("[J] Not found, count: " + comp);
                return -1;
            }
        }
        System.out.println("[J] Not found, count: " + comp);
        return -1;
    }

    /**
     * Find the index of the target in "this.items" using binary search and count the number of comparisons.
     * Print "[B] Index: (index), count: (count)" if you find the target.
     * Print "[B] Not found, count: (count)" if there is no target in the "this.items".
     * Hint. Use BigInteger1.compareTo(BigInteger2) when you compare two BigInteger instances.
     *
     * @param target BigInteger type of target value
     * @return index of the target value
     */
    public int doBinarySearch(BigInteger target) {
        if (this.N == -1) {
            System.out.println("[B] The array is empty.");
            return -1;
        }

        int l = 0;
        int r = N - 1;
        int m;
        int comp = 0;

        while (l <= r) {
            m = (r - l) / 2 + l;

            if (target.compareTo(items[m]) == 0) {
                comp++;
                System.out.println("[B] Index: " + m + ", count: " + comp);
                return m;
            } else if (target.compareTo(items[m]) > 0) {
                comp++;
                l = m + 1;
            } else {
                comp++;
                r = m - 1;
            }
        }
        System.out.println("[B] Not found, count: " + comp);
        return -1;
    }

    /**
     * Print the list of elements in the 'this.items'.
     */
    public void print() {
        if (this.N == -1) {
            System.out.println("There are no items to print");
        }
        String str = "";
        for (int i = 0; i < N; i++) str += this.items[i] + " ";
        System.out.println(str);
    }

    /*----------------------------------- Optional Problem ----------------------------------------------*/

    /**
     * Find the index of the target in "this.items" using interpolation search and count the number of comparisons.
     * Print "[I] Index: (index), count: (count)" if you find the target.
     * Print "[I] Not found, count: (count)" if there is no target in the "this.items".
     *
     * @param target BigInteger type of target value
     * @return index of the target value
     */
    public int doInterpolationSearch(BigInteger target) {
        if (this.N == -1) {
            System.out.println("[I] The array is empty.");
            return -1;
        }
        int l = 0;
        int r = N - 1;
        int m;
        int comp = 0;

        while (l < r) {
            double start = items[l].doubleValue();
            double end = items[r].doubleValue();
            double k = target.doubleValue();
            double p = (k - start) / (end - start);

            if (p < 0 || p > 1) {
                System.out.println("[I] Not found, count: " + comp);
                return -1;
            }

            m = l + (int) ((r - l + 1) * p);

            if (items[m].equals(target)) {
                comp++;
                System.out.println("[I] Index: " + m + ", count: " + comp);
                return m;
            } else if (items[m].compareTo(target) < 0) {
                comp++;
                l = m + 1;
            } else {
                comp++;
                r = m - 1;
            }
        }

        if (l == r && items[l].equals(target)) {
            comp++;
            System.out.println("[I] Index: " + l + ", count: " + comp);
            return l;
        }

        System.out.println("[I] Not found, count: " + comp);
        return -1;
    }
}