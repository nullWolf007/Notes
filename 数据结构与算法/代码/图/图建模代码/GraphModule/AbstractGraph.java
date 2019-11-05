package GraphModule;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGraph<V> implements Graph<V> {
    protected List<V> vertices = new ArrayList<>();//顶点集合
    protected List<List<Edge>> neighbors = new ArrayList<>();//边的集合

    protected AbstractGraph() {
    }

    protected AbstractGraph(List<V> vertices, List<Edge> edges) {
        for (int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
        }
        for (Edge edge : edges) {
            addEdge(edge.u, edge.v);
        }
    }

    protected AbstractGraph(V[] vertices, int[][] edges) {
        for (int i = 0; i < vertices.length; i++) {
            addVertex(vertices[i]);
        }
        for (int i = 0; i < edges.length; i++) {
            addEdge(edges[i][0], edges[i][1]);
        }
    }

    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            addVertex((V) new Integer(i));
        }
        for (Edge edge : edges) {
            addEdge(edge.u, edge.v);
        }
    }

    //获取图的顶点数
    @Override
    public int getSize() {
        return vertices.size();
    }

    //获取所有的顶点
    @Override
    public List<V> getVertices() {
        return vertices;
    }

    //获取指定index的顶点
    @Override
    public V getVertex(int index) {
        if (index > vertices.size() - 1) {
            return null;
        }
        return vertices.get(index);
    }

    //获取指定顶点的index
    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    //获取指定index的顶点的所有邻居的index
    @Override
    public List<Integer> getNeighnors(int index) {
        if (index > neighbors.size() - 1) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        for (Edge e : neighbors.get(index)) {
            result.add(e.v);
        }
        return result;
    }

    //获取指定index的度
    @Override
    public int getDegree(int index) {
        if (index > neighbors.size() - 1) {
            return -1;
        }
        return neighbors.get(index).size();
    }

    //打印边
    @Override
    public void printEdges() {
        for (int i = 0; i < neighbors.size(); i++) {
            List<Edge> edgeList = neighbors.get(i);
            for (int j = 0; j < edgeList.size(); j++) {
                System.out.print("(" + edgeList.get(j).u + "," + edgeList.get(j).v + ")");
            }
            System.out.println();
        }
    }

    //清除图
    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    //添加顶点到图中，如果已经存在返回false；如果添加成功返回true
    @Override
    public boolean addVertex(V vertex) {
        if (vertices.contains(vertex)) {//已经存在
            return false;
        } else {
            vertices.add(vertex);
            neighbors.add(new ArrayList<Edge>());
            return true;
        }
    }

    //添加从u到v的边；如果u或者v是无效的 抛出IllegalArgumentException异常；如果添加成功返回true；如果该边已经存在返回false
    @Override
    public boolean addEdge(int u, int v) {
        if (null == getVertex(u) || null == getVertex(v)) {
            throw new IllegalArgumentException("无效");
        }
        List<Edge> edgeList = neighbors.get(u);
        Edge edge = new Edge(u, v);
        if (edgeList.contains(edge)) {
            return false;
        } else {
            neighbors.get(u).add(edge);
            return true;
        }
    }

    //添加从u到v的边；如果u或者v是无效的 抛出IllegalArgumentException异常；如果添加成功返回true；如果该边已经存在返回false
    public boolean addEdge(Edge edge) {
        List<Edge> edgeList = neighbors.get(edge.u);
        if (edgeList.contains(edge)) {
            return false;
        } else {
            neighbors.get(edge.u).add(edge);
            return true;
        }
    }


    //得到从index开始的一个深度优先搜索树
    @Override
    public AbstractGraph<V>.Tree dfs(int index) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];

        //初始化 全部为-1
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }

        //记录是否访问过
        boolean[] isVisited = new boolean[vertices.size()];

        dfs(index, parent, searchOrder, isVisited);

        return new Tree(index, parent, searchOrder);
    }

    private void dfs(int u, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
        searchOrder.add(u);
        isVisited[u] = true;

        for (Edge e : neighbors.get(u)) {
            if (!isVisited[e.v]) {
                parent[e.v] = u;
                dfs(e.v, parent, searchOrder, isVisited);
            }
        }
    }

    //得到从index开始的一个宽度优先搜索树
    @Override
    public AbstractGraph<V>.Tree bfs(int index) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];

        //初始化
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        boolean[] isVisited = new boolean[vertices.size()];
        linkedList.offer(index);
        isVisited[index] = true;
        while (!linkedList.isEmpty()) {
            int u = linkedList.poll();
            searchOrder.add(u);
            for (Edge e : neighbors.get(u)) {
                if (!isVisited[e.v]) {
                    linkedList.offer(e.v);
                    parent[e.v] = u;
                    isVisited[e.v] = true;
                }
            }
        }
        return new Tree(index, parent, searchOrder);
    }


    public class Tree {
        private int root;//根结点
        private int[] parent;//结点的父结点 如parent[0]=3;则说明0的父结点是2结点
        private List<Integer> searchOrder;//遍历结点的顺序

        public Tree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        //获取根节点的index
        public int getRoot() {
            return root;
        }

        //获取父结点的index
        public int getParent(int v) {
            return parent[v];
        }

        //获取被搜索的顶点的顺序
        public List<Integer> getSearchOrder() {
            return searchOrder;
        }

        //返回搜索到顶点的个数
        public int getNumberOfVeriticesFound() {
            return searchOrder.size();
        }

        //返回一个从指定下标的顶点 到 根节点 的顶点线性表
        public List<V> getPath(int index) {
            ArrayList<V> path = new ArrayList<>();
            do {
                path.add(vertices.get(index));
                index = parent[index];
            }
            while (index != -1);
            return path;
        }

        //打印从根节点到该结点的路径
        public void printPath(int index) {
            List<V> path = getPath(index);
            System.out.println("路径：从根" + vertices.get(root) + "到" + vertices.get(index));
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i) + "-->");
            }
        }

        public void printTree() {
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] != -1) {
                    System.out.print("(" + vertices.get(parent[i]) + "," + vertices.get(i) + ") ");
                }
            }
        }
    }

}

