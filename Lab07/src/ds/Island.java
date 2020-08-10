package ds;

import com.sun.jdi.IntegerValue;

import java.util.Arrays;

public class Island {
    private ParPtrTree ppt;
    private Integer[][] map;
    private int map_size;
    private static Integer[] roots;
    private static int count;

    public Island(Integer[][] m, int ms) {
        this.map = m;
        this.map_size = ms;
        ppt = new ParPtrTree(map_size * map_size);
    }

    /**
     * Segment map using union and find function in ppt (point pointer tree)
     * Hint: you can represents 2-d array as 1-d array by flattening.
     * For row r and col c -> 1-d index = row * (row_size) + col
     */
    public void segmentMap() {
        for (int i = 0; i < map_size; i++) {
            for (int j = 0; j < map_size; j++) {
                if (map[i][j] == 1) {
                    if (j != map_size - 1) {
                        if (map[i][j + 1] == 1) {
                            ppt.union(i * map_size + j, i * map_size + j + 1);
                        }
                    }
                    if (i != map_size - 1) {
                        if (map[i + 1][j] == 1) {
                            ppt.union(i * map_size + j, (i + 1) * map_size + j);
                        }
                    }
                }
            }
        }
    }

    /**
     * get the number of the islands using array of ppt
     */
    public int getIslandNum() {
        count = 0;
        Integer tempRoot = null;
        roots = new Integer[map_size * map_size];

        for (int i = 0; i < map_size * map_size; i++) {
            if (map[i / map_size][i % map_size] != 1) continue;

            tempRoot = ppt.find(i);

            if (count == 0) {
                roots[0] = tempRoot;
                count++;
            }

            for (int j = 0; j < count; j++) {
                if (roots[j].equals(tempRoot)) {
                    break;
                } else if (j == count - 1) {
                    roots[count] = tempRoot;
                    count++;
                }
            }
        }
        return count;
    }

    public int[] getIslandSize() {
        int num = count;
        Integer[] size = ppt.getSize();
        int[] ans = new int[num];

        for (int i = 0; i < num; i++) {
            ans[i] = size[roots[i]].intValue();
        }
        Arrays.sort(ans);
        return ans;
    }
}
