package GraphModule.Test;

import GraphModule.WeightedGraph;

public class TestShortedpath {

    public static void main(String[] args) {
        int[][] edges = new int[][]{
                {0, 1, 2}, {0, 3, 8},
                {1, 0, 2}, {1, 2, 7}, {1, 3, 3},
                {2, 1, 7}, {2, 3, 4}, {2, 4, 5},
                {3, 0, 8}, {3, 1, 3}, {3, 2, 4}, {3, 4, 6},
                {4, 2, 5}, {4, 3, 6}
        };
        Integer[] vertices = {0, 1, 2, 3, 4};
        WeightedGraph<Integer> graph = new WeightedGraph<Integer>(vertices, edges);
        WeightedGraph.ST tree = graph.getShortedPath(3);
        tree.printAllPaths();
    }
}
