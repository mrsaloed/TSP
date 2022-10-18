package com.example.tsp.entity;

public class GraphGenerator {

    private static final int INF = Integer.MAX_VALUE;

    public GraphGenerator() {

    }

    public Graph generate(int size, int minDistance, int maxDistance) {
        int[][] tempGraph = new int[size][size];
        Graph graph = new Graph(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i!=j) {
                    tempGraph[i][j] = (int) (Math.random() * (maxDistance - minDistance + 1) + maxDistance);
                } else {
                    tempGraph [i][j] = INF;
                }
            }
        }
        graph.setGraph(tempGraph);
        return graph;
    }
}
