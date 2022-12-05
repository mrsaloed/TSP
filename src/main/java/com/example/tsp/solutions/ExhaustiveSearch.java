package com.example.tsp.solutions;

import com.example.tsp.entity.Graph;
import com.example.tsp.entity.Path;

import java.util.ArrayList;
import java.util.List;

public class ExhaustiveSearch implements TSP {

    private long leadTimeMillis;
    private final Path minimalPath;
    private final int[][] graph;
    private boolean calculated;


    public ExhaustiveSearch(Graph graph) {
        this.graph = graph.getGraph();
        minimalPath = new Path();
    }

    @Override
    public long getTime() {
        return calculated? leadTimeMillis : null;
    }

    @Override
    public Path getMinimalPath() {
        leadTimeMillis = System.currentTimeMillis();
        findPermutations(startPermutation());
        leadTimeMillis = System.currentTimeMillis() - leadTimeMillis;
        calculated = true;
        return minimalPath;
    }

    private void calculate(List<Integer> path) {
        int tempPathLength = 0;
        for (int i = 1; i < path.size(); i++) {
            tempPathLength += graph[path.get(i - 1) - 1][path.get(i) - 1];
        }
        if (tempPathLength < minimalPath.getLength()) {
            minimalPath.setLength(tempPathLength);
            minimalPath.setPath(path);
        }
    }

    private List<Integer> startPermutation() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            list.add(i + 1);
        }
        return list;
    }

    private void swap(List<Integer> chars, int i, int j) {
        int temp = chars.get(i);
        chars.set(i, chars.get(j));
        chars.set(j, temp);
    }

    private void permutations(List<Integer> chars, int currentIndex) {
        if (currentIndex == chars.size() - 1) {
            calculate(chars);
        }

        for (int i = currentIndex; i < chars.size(); i++) {
            swap(chars, currentIndex, i);
            permutations(chars, currentIndex + 1);
            swap(chars, currentIndex, i);
        }
    }

    private void findPermutations(List<Integer> str) {
        if (str == null || str.size() == 0) {
            return;
        }
        permutations(str, 0);
    }

}
