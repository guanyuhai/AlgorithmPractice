package Graph.chapter11;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @description: 实现Prim算法
 * @author: wtx
 * @createDate: 2020/6/6
 */
public class Prim {
    private WeightedGraph graph;
    private List<WeightedEdge> mst; //最小生成树

    public Prim(WeightedGraph graph) {
        this.graph = graph;
        mst = new ArrayList<>();

        CC cc = new CC(graph);
        if (cc.getCcCount() > 1)
            return;

        // Prim
        boolean[] visited = new boolean[graph.V()];
        visited[0] = true;  //初始切分

        //使用优先队列优化, 避免无谓地扫描所有边，每次取出最小堆的最小权值
        Queue<WeightedEdge> pq = new PriorityQueue<>();
        // 初始的时候，所有和0相邻的邻边都是横切边
        for (int w: graph.adj(0))
            pq.add(new WeightedEdge(0,w,graph.getWeight(0,w)));

        while (!pq.isEmpty()){
            WeightedEdge minEdge = pq.remove();
            //未必所有边都是合法的横切边
            if (visited[minEdge.getV()] && visited[minEdge.getW()])
                continue;
            mst.add(minEdge); // 因为是从优先队列中取出来的，所以一定是最小横切边

            //扩展切分
            //新加入切分的顶点
            int newV = visited[minEdge.getV()] ? minEdge.getW():minEdge.getV();
            for (int w: graph.adj(newV))
                if (!visited[w]) // 如果w为false，则表明newV 到 w 的边为横切边
                    pq.add(new WeightedEdge(newV,w,graph.getWeight(newV,w)));
        }

        //效率太低，优化!
        /*for (int i = 1; i < graph.V(); i++) {
            WeightedEdge minEdge = new WeightedEdge(-1,-1,Integer.MAX_VALUE); //记录最短的横切边
            for (int v = 0; v < graph.V(); v++)
                if (visited[v])
                    for (int w: graph.adj(v))   // v、w为横切边
                        if (!visited[w] && graph.getWeight(w,v)<minEdge.getWeight())
                            minEdge = new WeightedEdge(v,w,graph.getWeight(v,w));

            mst.add(minEdge);
            //扩充切分, 懒得判断, 直接赋值为true
            visited[minEdge.getW()] = true;
            visited[minEdge.getV()] = true;
        }*/
    }

    public List<WeightedEdge> result(){
        return this.mst;
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph("src\\main\\java\\Graph\\chapter11\\g.txt");
        Prim prim = new Prim(graph);
        System.out.println(prim.result());
    }
}
