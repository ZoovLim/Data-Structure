package ds;

import com.sun.jdi.IntegerValue;

import java.lang.reflect.Array;

public class HashTable<Key extends Comparable<? super Key>, E> {
    private int M; // Size of the table
    private KVpair<Key, E>[] T; // Array of the elements

    @SuppressWarnings("unchecked") // Allow the generic array allocation
    public HashTable(int m) {
        M = m;
        T = (KVpair<Key, E>[]) Array.newInstance(KVpair.class, M);
        for (int i = 0; i < M; i++)
            T[i] = new KVpair<Key, E>();
    }

    /**
     * Insert record r into T
     */
    public void hashInsert(Key k, E r) {
        int key = hash(k);
        KVpair<Key, E> pair = new KVpair<>(k, r);

        if (T[key].key() == null) {
            T[key] = pair;
        } else {
            KVpair<Key, E> curr = T[key];

            while (curr.next() != null) {
                curr = curr.next();
            }
            curr.set_next(pair);
        }
    }

    /**
     * Search for the record with key k
     */
    public E hashSearch(Key k) {
        int key = hash(k);
        KVpair<Key, E> curr = T[key];

        if (curr.key() == null) {
            return null;
        }

        while (curr != null) {
            if (curr.key().equals(k)) {
                break;
            } else {
                curr = curr.next();
            }
        }
        return curr.value();
    }

    /**
     * Remove a record with key value k from the hash table
     */
    public E hashRemove(Key k) {
        int key = hash(k);
        KVpair<Key, E> curr = T[key];

        if (curr.key().equals(k)) {
            E value = curr.value();
            T[key] = null;
            return value;
        } else {
            while (curr.next() != null) {
                if (curr.next().key().equals(k)) {
                    E value = curr.next().value();
                    curr.set_next(curr.next().next());
                    return value;
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    /**
     * Change the open hashing to the closed hashing
     */
    public void changeToClosed() {
        for (int i = 0; i < M; i++) {
            KVpair<Key, E> curr = T[i];

            if (curr.key() == null) {
                continue;
            } else {
                while (curr.next() != null) {
                    Key key = curr.next().key();
                    E value = curr.next().value();
                    KVpair<Key, E> pair = new KVpair<>(key, value);

                    for (int j = 1; j < M; j++) {
                        if (T[(i + j) % M].key() == null) {
                            T[(i + j) % M] = pair;
                            break;
                        }
                    }
                    curr.set_next(curr.next().next());
                }
            }
        }
    }

    /**
     * Print the entire elements with keys and values in order
     */
    public void hashPrint() {
        for (int i = 0; i < M; i++) {
            if (T[i].key() == null) {
                continue;
            }
            System.out.println();

            KVpair<Key, E> curr = T[i];

            while (curr.key() != null) {
                System.out.print("(" + curr.key() + ", " + curr.value() + ") ");
                if (curr.next() == null) {
                    break;
                }
                curr = curr.next();
            }
        }
        System.out.println();
    }

    /**
     * If the key is an Integer, then use a simple mod function for the hash
     * function. If the key is a String, then use folding.
     */
    private int hash(Key k) {
        Object keyO = k;
        if (keyO.getClass() == Integer.class) {
            return Integer.parseInt(k.toString()) % M;
        } else {
            return k.hashCode() % M;
        }
    }
}

