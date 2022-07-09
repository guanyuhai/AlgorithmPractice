package Graph.chapter12;

import Graph.chapter11.WeightedGraph;

import java.util.*;

/**
 * @description: Dijkstra 算法
 * @author: wtx
 * @createDate: 2020/6/7
 */
public class Dijkstra {
    private WeightedGraph graph;
    private int source; //原点
    private int[] dis;  //表示原点到i点的距离
    private boolean[] visited;  //表示是否确定原点到某点的最小距离
    private int[] pre;

    private class Node implements Comparable<Node> {
        public int v, dis;

        public Node(int v, int dis) {
            this.v = v;  // 表示顶点
            this.dis = dis; // 从源点到顶点V的距离
        }

        @Override
        public int compareTo(Node another) {
            return this.dis - another.dis;
        }
    }

    public Dijkstra(WeightedGraph graph, int source) {
        this.graph = graph;

        graph.validateVertex(source);
        this.source = source;

        this.dis = new int[graph.V()];
        Arrays.fill(dis,Integer.MAX_VALUE);
        dis[source] = 0;  // 从source到source的距离为0

        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        Arrays.fill(pre,-1);
        pre[source] = source;

        // dijkstra
        Queue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(source,0));
        while (!queue.isEmpty()){
            /* O(V^2) 需要改进：这段代码的作用只是找到当前未访问的dis值最小的节点
            int curDis = Integer.MAX_VALUE; // 当前找到的最小的dis值
            int cur = -1; // 记录当前dis值最小的顶点
            for (int v = 0; v < graph.V(); v++)
                if (!visited[v] && dis[v]<curDis){
                    curDis = dis[v]; // 更新dis值
                    cur = v;
                }
            if (cur == -1)
                break;*/
            int cur = queue.remove().v; //一句替换上面的for循环，变为O(logn)级别的时间复杂度
            if (visited[cur]) // pq中可能存在多个重复值，所以需要跳过多余的
                continue;

            visited[cur] = true; // 表示当前cur顶点已经求解出最短路径
            for (int w: graph.adj(cur)) {
                // 如果cur到w的距离比已知的到w的距离要小，就更新dis[w]的值
                if (!visited[w] && dis[cur] + graph.getWeight(cur, w) < dis[w]) {
                    dis[w] = dis[cur] + graph.getWeight(cur, w);
                    queue.add(new Node(w, dis[w]));
                    pre[w] = cur;
                }
            }
        }
    }

    // source、v是否可达
    public boolean isConnetedTo(int v){
        graph.validateVertex(v);
        return visited[v];
    }

    // source到v的距离
    public int disTo(int v){
        graph.validateVertex(v);
        return dis[v];
    }

    //source到t的路径
    public Iterable<Integer> path(int t){
        List<Integer> res = new ArrayList<>();
        if (!isConnetedTo(t))
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
        Dijkstra dijkstra = new Dijkstra(graph,0);
        for (int i = 0; i < graph.V(); i++)
            System.out.println(dijkstra.disTo(i));

        System.out.println(dijkstra.path(3));
    }
}
