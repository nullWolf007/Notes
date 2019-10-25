package Graph.NineTail;

import Graph.AbstractGraph;
import Graph.Edge;
import Graph.UnweightGraph;

import java.util.ArrayList;
import java.util.List;

public class NineTailModel {
    //H表示正面朝上 T表示反面朝上
    public final static int NUMBER_OF_NODES = 512;

    protected AbstractGraph<Integer>.Tree tree;

    public NineTailModel() {
        List<Edge> edges = getEdges();

        UnweightGraph<Integer> graph = new UnweightGraph<Integer>(edges, NUMBER_OF_NODES);

        tree = graph.bfs(511);
    }

    private List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();

        for (int u = 0; u < NUMBER_OF_NODES; u++) {
            for (int k = 0; k < 9; k++) {
                char[] node = getNode(u);
                if (node[k] == 'H') {//只有正面朝上才可翻
                    int v = getFlippedNode(node, k);
                    edges.add(new Edge(v, u));
                }
            }
        }
        return edges;
    }

    /**
     * 翻转某个正面硬币 及 周围硬币
     */
    public static int getFlippedNode(char[] node, int position) {
        int row = position / 3;
        int column = position % 3;

        flipCell(node, row, column);
        flipCell(node, row - 1, column);
        flipCell(node, row + 1, column);
        flipCell(node, row, column - 1);
        flipCell(node, row, column + 1);

        return getIndex(node);
    }

    /**
     * 反转单个硬币
     */
    public static void flipCell(char[] node, int row, int column) {
        if (row >= 0 && row <= 2 && column >= 0 && column <= 2) {
            if (node[row * 3 + column] == 'H') {
                node[row * 3 + column] = 'T';
            } else {
                node[row * 3 + column] = 'H';
            }
        }
    }

    /**
     * 根据节点获取index
     */
    public static int getIndex(char[] node) {
        int result = 0;
        for (int i = 0; i < 9; i++) {
            if (node[i] == 'T') {
                result = result * 2 + 1;
            } else {
                result = result * 2;
            }
        }
        return result;
    }

    /**
     * 根据index生成节点
     */
    public static char[] getNode(int index) {
        char[] result = new char[9];

        for (int i = 0; i < 9; i++) {
            int digit = index % 2;
            if (digit == 0) {
                result[8 - i] = 'H';
            } else {
                result[8 - i] = 'T';
            }
            index = index / 2;
        }
        return result;
    }

    public List<Integer> getShortesPath(int nodeIndex) {
        return tree.getPath(nodeIndex);
    }

    public static void printNode(char[] node) {
        for (int i = 0; i < 9; i++) {
            System.out.print(node[i]);
            if (i % 3 == 2) {
                System.out.println();
            }
        }
    }
}
