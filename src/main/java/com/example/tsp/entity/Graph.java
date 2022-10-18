package com.example.tsp.entity;

public class Graph {
    private static final String INF = "INF";
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private String view;
    private int[][] graph;


    public Graph(int size) {
        graph = new int[size][size];
    }

    public String getView() {
        if (view == null) {
            view = createView();
        }
        return view;
    }

    public int[][] getGraph() {
        return graph;
    }

    public void setGraph(int[][] graph) {
        this.graph = graph;
    }

    private String createView() {
        StringBuilder sb = new StringBuilder();
        for (int[] vector: graph) {
            for (int distance : vector) {
                if (distance != Integer.MAX_VALUE) {
                    sb.append(distance).append(SPACE);
                } else {
                    sb.append(INF).append(SPACE);
                }
            }
            sb.append(NEW_LINE);
        }
        return sb.toString();
    }
}
