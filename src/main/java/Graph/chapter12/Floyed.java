package Graph.chapter12;

import Graph.chapter11.WeightedGraph;

import java.util.Arrays;

/**
 * @description:  Floyed 算法
 * @author: wtx
 * @createDate: 2020/6/7
 */
public class Floyed {
    private WeightedGraph graph;
    private int[][] dis;
    private boolean hasNegativeCycle = false;

    public Floyed(WeightedGraph graph) {
        this.graph = graph;

        dis = new int[graph.V()][graph.V()];
        for (int i = 0; i < graph.V(); i++)
            Arrays.fill(dis[i],Integer.MAX_VALUE);

        for (int v = 0; v < graph.V(); v++) {
            dis[v][v] = 0;
            for (int w: graph.adj(v))
                dis[v][w] = graph.getWeight(v,w);
        }

        for (int t = 0; t < graph.V(); t++)
            for (int v = 0; v < graph.V(); v++)
                for (int w = 0; w < graph.V(); w++)
                    if (dis[v][t]!=Integer.MAX_VALUE && dis[t][w]!=Integer.MAX_VALUE
                            && dis[v][t]+dis[t][w] < dis[v][w] ) //可以在这里判断一下溢出的情况
                        dis[v][w] = dis[v][t] + dis[t][w];

        for (int v = 0; v < graph.V(); v++)
            if (dis[v][v]<0) // 判断是否有负权环
                this.hasNegativeCycle = true;
    }

    // 查询是否有负权环
    public boolean isHasNegativeCycle(){
        return this.hasNegativeCycle;
    }

    // 顶点v与w是否相连
    public boolean isConneted(int v, int w){
        graph.validateVertex(v);
        graph.validateVertex(w);
        return dis[v][w] != Integer.MAX_VALUE;
    }

    // 顶点v与w之间最短路径
    public int distTo(int v, int w){
        graph.validateVertex(v);
        graph.validateVertex(w);
        return dis[v][w];
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph("g.txt");
        Floyed floyed = new Floyed(graph);
        if (!floyed.hasNegativeCycle) {
            for (int i = 0; i < graph.V(); i++) {
                for (int j = 0; j < graph.V(); j++) {
                    System.out.print(floyed.dis[i][j] + " ");
                    System.out.println();
                }
            }
        }
    }
}
