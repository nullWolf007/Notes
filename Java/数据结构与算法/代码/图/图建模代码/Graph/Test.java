package Graph;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> vertexs = new ArrayList<>();
        vertexs.add("Peter");
        vertexs.add("Jane");
        vertexs.add("Mark");
        vertexs.add("Cindy");
        vertexs.add("Wendy");

        List<Edge> line = new ArrayList<Edge>();
        line.add(new Edge(0, 1));
        line.add(new Edge(0, 2));

        line.add(new Edge(1, 2));

        line.add(new Edge(2, 4));

        line.add(new Edge(3, 4));

        Graph<String> unweightGraph = new UnweightGraph<String>(vertexs, line);
        unweightGraph.printEdges();


    }
}
