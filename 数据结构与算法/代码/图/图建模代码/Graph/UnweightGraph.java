package Graph;

import java.util.List;

public class UnweightGraph<V> extends AbstractGraph<V> {
    public UnweightGraph() {
    }

    public UnweightGraph(List<V> vertices, List<Edge> edges) {
        super(vertices, edges);
    }

    public UnweightGraph(V[] vertices, int[][] edges) {
        super(vertices, edges);
    }
}
