package Graph.NineTail;

import java.util.List;

public class NineTail {
    public static void main(String[] args) {
        char[] initialNode = "HHHTTTHHH".toCharArray();

        NineTailModel model = new NineTailModel();
        List<Integer> path = model.getShortesPath(NineTailModel.getIndex(initialNode));

        for (int i = 0; i < path.size(); i++) {
            NineTailModel.printNode(NineTailModel.getNode(path.get(i)));
            System.out.println("----------");
        }
    }
}
