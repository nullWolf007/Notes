package GraphModule;

import java.util.List;

/**
 * 接口类
 *
 * @param <V>
 */
public interface Graph<V> {
    //获取图的顶点数
    int getSize();

    //获取所有的顶点
    List<V> getVertices();

    //获取指定index的顶点
    V getVertex(int index);

    //获取指定顶点的index
    int getIndex(V v);

    //获取指定index的顶点的所有邻居的index
    List<Integer> getNeighnors(int index);

    //获取指定index的度
    int getDegree(int index);

    //打印边
    void printEdges();

    //清除图
    void clear();

    //添加顶点到图中，如果已经存在返回false；如果添加成功返回true
    boolean addVertex(V vertex);

    //添加从u到v的边；如果u或者v是无效的 抛出IllegalArgumentException异常；如果添加成功返回true；如果该边已经存在返回false
    boolean addEdge(int u, int v);

    //得到从index开始的一个深度优先搜索树
    AbstractGraph<V>.Tree dfs(int index);

    //得到从index开始的一个宽度优先搜索树
    AbstractGraph<V>.Tree bfs(int index);
}
