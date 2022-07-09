package Graph.chapter11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 使用 Kruskal 算法获取带权图的最小生成树
 * @author: wtx
 * @createDate: 2020/6/6
 */
public class Kruskal {
    private WeightedGraph graph;
    private List<WeightedEdge> mst;    //最小生成树

    public Kruskal(WeightedGraph graph) {
        this.graph = graph;
        this.mst = new ArrayList<>();

        CC cc = new CC(graph);
        //连通分量的个数大于1
        if (cc.getCcCount() > 1)
            return;

        // kruskal
        List<WeightedEdge> edges = new ArrayList<>(); //存放图的所有边
        for (int v = 0; v < graph.V(); v++)
            for (int w: graph.adj(v))
                if (v < w)  //避免每条边被存两遍， 比如0 2，2 0，只存0 2这条边，因为是无向图
                    edges.add(new WeightedEdge(v,w,graph.getWeight(v,w)));

        Collections.sort(edges);

        // 把边由小到大取出, 判断是否成环
        UF uf = new UF(graph.V());   //union find 并查集
        for (WeightedEdge edge : edges) {
            int v = edge.getV();
            int w = edge.getW();

            // 判断v和w是否联通，在并查集中，判断两个顶点是否在同一个集合中
            if (!uf.isConnected(v,w)){
                mst.add(edge);
                uf.unionElements(v,w);
            }
        }
    }

    public List<WeightedEdge> result(){
        return mst;
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph("src\\main\\java\\Graph\\chapter11\\g.txt");
        Kruskal kruskal = new Kruskal(graph);
        System.out.println(kruskal.result());
    }
}
