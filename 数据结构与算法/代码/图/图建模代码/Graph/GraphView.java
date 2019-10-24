package Graph;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class GraphView extends Pane {
    private Graph<? extends Displayable> graph;

    public GraphView(Graph<? extends Displayable> graph) {
        this.graph = graph;

        List<? extends Displayable> vertices = graph.getVertices();

        //画点 和 名字
        for (int i = 0; i < graph.getSize(); i++) {
            int x = vertices.get(i).getX();
            int y = vertices.get(i).getY();
            String name = vertices.get(i).getName();

            getChildren().add(new Circle(x, y, 4));
            getChildren().add(new Text(x - 8, y - 18, name));
        }

        //画线
        for (int i = 0; i < graph.getSize(); i++) {
            List<Integer> neighbors = graph.getNeighnors(i);
            int x1 = graph.getVertex(i).getX();
            int y1 = graph.getVertex(i).getY();
            for (int v : neighbors) {
                int x2 = graph.getVertex(v).getX();
                int y2 = graph.getVertex(v).getY();

                //画线
                getChildren().add(new Line(x1, y1, x2, y2));
            }
        }
    }
}
