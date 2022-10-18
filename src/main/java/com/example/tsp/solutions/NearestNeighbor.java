package com.example.tsp.solutions;

import com.example.tsp.entity.Graph;
import com.example.tsp.entity.Path;

import java.util.ArrayList;
import java.util.List;

public class NearestNeighbor implements TSP {

    private long leadTimeMillis;
    private Path minimalPath;
    private final int[][] graph;
    private List<Integer> tempPathList = new ArrayList<>();
    private boolean[] visited;
    private int minPath = 0;
    private boolean calculated = false;

    public NearestNeighbor(Graph graph) {
        this.graph = graph.getGraph();
        minimalPath = new Path();
        visited = new boolean[this.graph.length];
    }

    @Override
    public long getTime() {
        return calculated ? leadTimeMillis : null;
    }

    @Override
    public Path getMinimalPath() {
        long initialTimeMillis = System.currentTimeMillis();
        calculate();
        leadTimeMillis = System.currentTimeMillis() - initialTimeMillis;
        calculated = true;
        return minimalPath;
    }

    private void calculate() {
        Path tempPath;
        for (int i = 0; i < graph.length; i++) {
            tempPath = calculateOnePath(i);
            if (tempPath.getLength() < minimalPath.getLength()) {
                minimalPath = tempPath;
            }
            visited = new boolean[graph.length];
            minPath = 0;
            tempPathList = new ArrayList<>();
        }
    }

    private Path calculateOnePath(int i) {
        Path path = new Path();
        tempPathList.add(i + 1);
        int minLocalPath = Integer.MAX_VALUE;
        int minIndex = 0;
        visited[i] = true;
        for (int j = 0; j < graph[i].length; j++) {
            if (graph[i][j] < minLocalPath && !visited[j]) {
                minLocalPath = graph[i][j];
                minIndex = j;
            }
        }
        if (!visited[minIndex]) {
            minPath = minPath + graph[i][minIndex];
            calculateOnePath(minIndex);
        }
        path.setPath(tempPathList);
        path.setLength(minPath);
        return path;
    }
}
