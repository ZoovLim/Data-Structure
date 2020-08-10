package ds.graph;

import ds.queue.DistanceQueue;

public class Dijkstra {
    private DistanceQueue distQueue;
    private int[] prev;
    private double[] D;
    private Graph G;

    public Dijkstra(Graph G) {
        D = new double[G.n()];
        distQueue = new DistanceQueue(G.n());
        prev = new int[G.n()];
        this.G = G;
    }

    public void calculateShortestPath(Graph G, int start) {
        for (int i = 0; i < G.n(); i++) {
            D[i] = Double.MAX_VALUE;
            prev[i] = -1;
        }
        D[start] = 0;

        for (int i = 0; i < G.n(); i++) {
            distQueue.insert(i, D[i]);
        }

        for (int i = 0; i < G.n(); i++) {
            int v = minVertex(G);

            if (D[v] == Double.MAX_VALUE) {
                return;
            }

            for (int w = G.first(v); w < G.n(); w = G.next(v, w)) {
                if (distQueue.contains(w) && D[w] > D[v] + G.weight(v, w)) {
                    D[w] = D[v] + G.weight(v, w);
                    distQueue.decreaseDistance(w, D[w]);
                    prev[w] = v;
                }
            }
        }
    }

    private void printPathToEnd(Graph G, int end) {
        int v = end;
        StringBuffer ss = new StringBuffer();

        while (v != -1) {
            ss.append(v);
            ss.append(" ");
            v = prev[v];
        }

        System.out.println(ss.reverse().toString());
    }

    public void printAllPath(Graph G, int src) {
        for (int i = 0; i < G.n(); i++) {
            System.out.print("PATH " + src + " " + i + ": " + D[i]);
            printPathToEnd(G, i);
        }
    }

    public int minVertex(Graph G) {
        return distQueue.removeMin();
    }
}
