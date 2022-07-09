package Graph.chapter12;

import Graph.chapter11.WeightedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @description: BellmanFord 算法，求解单源最短路径
 * @author: wtx
 * @createDate: 2020/6/7
 */
public class BellmanFord {
    private WeightedGraph graph;
    private Integer source;
    private int[] dis;
    private boolean hasNegativeCycle = false;
    private int[] pre;


    public BellmanFord(WeightedGraph graph, Integer source) {
        this.graph = graph;

        graph.validateVertex(source);
        this.source = source;

        dis = new int[graph.V()];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[source] = 0;
        pre = new int[graph.V()];
        Arrays.fill(pre,-1);
        pre[source] = source;

        // BellmanFord
        //v-1轮循环 ：对所有边进行松弛操作
        for (int pass = 1; pass < graph.V(); pass++) {
            for (int v = 0; v < graph.V(); v++){
                for (int w : graph.adj(v))
                    if (dis[v] != Integer.MAX_VALUE && dis[v] + graph.getWeight(w, v) < dis[w]) {
                        dis[w] = dis[v] + graph.getWeight(v, w);
                        pre[w] = v;
                    }
            }
        }

        // 再进行一轮，判断是否包含负权环
        for (int v = 0; v < graph.V(); v++)
            for (int w: graph.adj(v))
                if (dis[v]!=Integer.MAX_VALUE && dis[v]+graph.getWeight(w,v) < dis[w])
                    this.hasNegativeCycle = true;
    }

    public boolean isHasNegativeCycle(){
        return this.hasNegativeCycle;
    }

    public boolean isConnectedTo(int v){
        graph.validateVertex(v);
        return dis[v] != Integer.MAX_VALUE;
    }

    public int disTo(int v){
        graph.validateVertex(v);
        if (hasNegativeCycle)
            throw new RuntimeException("hasNegativeCycle");

        return dis[v];
    }

    public Iterable<Integer> path(int t){
        List<Integer> res = new ArrayList<>();
        if (!isConnectedTo(t))
            return res;

        int cur = t;
        while (cur!=source){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(source);
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph("g.txt");
        BellmanFord bellmanFord = new BellmanFord(graph,0);
        System.out.println(bellmanFord.hasNegativeCycle);
        for (int di : bellmanFord.dis) {
            System.out.println(di);
        }
    }
}
