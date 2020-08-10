package ds.graph;

public class Prim {
    private static double[] D;
    private static int[] V;
    private static Boolean[] isVisited;

    public static void findMST(Graph G) {
        D = new double[G.n()];
        V = new int[G.n()];
        isVisited = new Boolean[G.n()];
        int v, w;

        for (int i = 0; i < G.n(); i++) {
            D[i] = Double.MAX_VALUE;
            isVisited[i] = false;
        }

        D[0] = 0;

        for (int i = 0; i < G.n(); i++) {
            v = getNextVertex(G, D, isVisited);

            if (v == -1) {
                return;
            }

            isVisited[v] = true;

            for (w = G.first(v); w < G.n(); w = G.next(v, w)) {
                if (D[w] > G.weight(v, w) && !isVisited[w]) {
                    D[w] = G.weight(v, w);
                    V[w] = v;
                }
            }
        }
        print(G, V);
    }

    public static void print(Graph G, int[] parent) {
        double sum = 0;

        for (int i = 1; i < G.n(); i++) {
            System.out.println("Edge: " + V[i] + " to " + i + ", weight: " + D[i]);
            sum += D[i];
        }

        System.out.println("Total weight: " + sum);
    }

    public static int getNextVertex(Graph G, double key[], Boolean isVisited[]) {
        double m = Double.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < G.n(); i++) {
            if (!isVisited[i]) {
                if (key[i] < m) {
                    m = key[i];
                    index = i;
                }
            }
        }

        return index;
    }
}
