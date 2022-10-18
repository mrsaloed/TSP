package com.example.tsp.entity;

import java.util.Arrays;
import java.util.List;

public class Path {
    private static final String ARROW = " → ";
    private static final String LEFT_BRACKET = "(";
    private static final String RIGHT_BRACKET = ")";
    private int length = Integer.MAX_VALUE;
    private int[] path;
    private String view;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getView() {
        if (view == null) {
            view = createView();
        }
        return view;
    }

    public int[] getPath() {
        return path;
    }

    public void setPath(int[] path) {
        this.path = path;
    }

    public void setPath(List<Integer> path) {
        this.path = new int[path.size()];
        for (int i = 0; i < path.size(); i++) {
            this.path[i] = path.get(i);
        }
    }

    private String createView() {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(path).forEach(x -> sb.append(x).append(ARROW));
        sb.append(LEFT_BRACKET).append(length).append(RIGHT_BRACKET);
        return sb.toString();
    }
}
