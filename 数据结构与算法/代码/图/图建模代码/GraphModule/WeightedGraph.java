package GraphModule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeightedGraph<V> extends AbstractGraph<V> {
    public WeightedGraph() {
    }

    public WeightedGraph(V[] vertices, int[][] edges) {
        createWeightedGraph(Arrays.asList(vertices), edges);
    }

    public WeightedGraph(List<WeightedEdge> edges, int numberOfVertices) {
        List<V> vertices = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            vertices.add((V) new Integer(i));
        }

        createWeightedGraph(vertices, edges);
    }

    public WeightedGraph(List<V> vertices, List<WeightedEdge> edges) {
        createWeightedGraph(vertices, edges);
    }

    private void createWeightedGraph(List<V> vertices, int[][] edges) {
        this.vertices = vertices;

        for (int i = 0; i < vertices.size(); i++) {
            neighbors.add(new ArrayList<Edge>());
        }

        for (int i = 0; i < edges.length; i++) {
            neighbors.get(edges[i][0]).add(new WeightedEdge(edges[i][0], edges[i][1], edges[i][2]));
        }
    }

    private void createWeightedGraph(List<V> vertices, List<WeightedEdge> edges) {
        this.vertices = vertices;

        for (int i = 0; i < vertices.size(); i++) {
            neighbors.add(new ArrayList<Edge>());
        }

        for (WeightedEdge edge : edges) {
            neighbors.get(edge.u).add(edge);
        }
    }

    //返回指定边的权重
    public double getWeighte(int u, int v) throws Exception {
        for (Edge edge : neighbors.get(u)) {
            if (edge.v == v) {
                return ((WeightedEdge) edge).weight;
            }
        }
        throw new Exception("没有找到这个边");
    }

    //打印所有的边
    public void printWeightedEdges() {
        for (int i = 0; i < neighbors.size(); i++) {
            List<Edge> edgeList = neighbors.get(i);
            for (int j = 0; j < edgeList.size(); j++) {
                System.out.print("(" + edgeList.get(j).u + "," + edgeList.get(j).v + "," + ((WeightedEdge) edgeList.get(j)).weight + ")");
            }
            System.out.println();
        }
    }

    //添加一个边
    public boolean addEdge(int u, int v, double weight) {
        return addEdge(new WeightedEdge(u, v, weight));
    }
}
