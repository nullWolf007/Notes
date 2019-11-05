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

    //获取最小生成树
    public MST getMinimumSpanningTree() {
        return getMinimumSpanningTree(0);
    }

    //获取最小生成树
    public MST getMinimumSpanningTree(int startVertex) {
        double[] cost = new double[getSize()];

        //初始化 起始节点为0 其他节点为无穷大
        for (int i = 0; i < cost.length; i++) {
            cost[i] = Double.POSITIVE_INFINITY;
        }
        cost[startVertex] = 0;
        //初始化 父结点和路径长度
        int[] parent = new int[getSize()];
        parent[startVertex] = -1;
        double totalWight = 0;
        //记录经过的节点
        List<Integer> T = new ArrayList<>();
        while (T.size() < getSize()) {
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;

            for (int i = 0; i < getSize(); i++) {
                //如果没有包含i结点 并且节点的花费小于最小花费
                if (!T.contains(i) && cost[i] < currentMinCost) {
                    currentMinCost = cost[i];
                    u = i;
                }
            }
            //该节点添加到T种
            T.add(u);
            //记录路径长度
            totalWight += cost[u];

            for (Edge e : neighbors.get(u)) {
                if (!T.contains(e.v) && ((WeightedEdge) e).weight < cost[e.v]) {
                    cost[e.v] = ((WeightedEdge) e).weight;
                    parent[e.v] = u;
                }
            }
        }

        return new MST(startVertex, parent, T, totalWight);
    }

    public class MST extends Tree {
        private double totalWeight;

        public MST(int root, int[] parent, List<Integer> searchOrder, double totalWeight) {
            super(root, parent, searchOrder);
            this.totalWeight = totalWeight;
        }

        public double getTotalWeight() {
            return totalWeight;
        }
    }

    //获取单源最短路径
    public ST getShortedPath(int startVertex) {
        //初始化
        double[] cost = new double[getSize()];
        for (int i = 0; i < getSize(); i++) {
            cost[i] = Double.POSITIVE_INFINITY;
        }
        cost[startVertex] = 0;

        int[] parent = new int[getSize()];
        parent[startVertex] = -1;
        List<Integer> T = new ArrayList<>();

        while (T.size() < getSize()) {
            int u = -1;
            double currentMin = Double.POSITIVE_INFINITY;
            for (int i = 0; i < getSize(); i++) {
                if (!T.contains(i) && cost[i] < currentMin) {
                    currentMin = cost[i];
                    u = i;
                }
            }

            T.add(u);

            for (Edge e : neighbors.get(u)) {
                if (!T.contains(e.v) && cost[u] + ((WeightedEdge) e).weight < cost[e.v]) {
                    cost[e.v] = cost[u] + ((WeightedEdge) e).weight;
                    parent[e.v] = u;
                }
            }
        }
        return new ST(startVertex, parent, T, cost);
    }

    public class ST extends Tree {
        private double[] cost;

        public ST(int root, int[] parent, List<Integer> searchOrder, double[] cost) {
            super(root, parent, searchOrder);
            this.cost = cost;
        }

        public double[] getCost() {
            return cost;
        }

        public void printAllPaths() {
            for (int i = 0; i < cost.length; i++) {
                printPath(i);
                System.out.println("(cost：" + cost[i] + ") ");
            }
        }
    }
}
