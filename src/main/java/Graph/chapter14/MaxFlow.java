package Graph.chapter14;

import java.util.*;

public class MaxFlow {
    private WeightedGraph network;
    private int s, t; // 源点和汇点

    private WeightedGraph rG; // 残量图
    private int maxFlow = 0;

    public MaxFlow(WeightedGraph network, int s, int t){
        if(!network.isDirected()) // 最大流问题必须是有向图
            throw new IllegalArgumentException("MaxFlow only works in directed graph.");
        if(network.V() < 2) // 网络流点数必须大于等于2
            throw new IllegalArgumentException("The network should hs at least 2 vertices.");

        network.validateVertex(s);
        network.validateVertex(t);
        if(s == t) // 源点和汇点必须是不同的两个点
            throw new IllegalArgumentException("s and t should be different.");

        this.network = network;
        this.s = s;
        this.t = t;

        // 创建残量图
        this.rG = new WeightedGraph(network.V(), true);
        for(int v = 0; v < network.V(); v ++) {
            for (int w : network.adj(v)) {
                int c = network.getWeight(v, w);
                rG.addEdge(v, w, c);  // 容量c
                rG.addEdge(w, v, 0);  // 权值，默认为0
            }
        }

        while(true){
            // 找到一条增广路径
            ArrayList<Integer> augPath = getAugmentingPath();
            if(augPath.size() == 0) break;

            // 计算增广路径上的最小值
            int f = Integer.MAX_VALUE;
            for(int i = 1; i < augPath.size(); i ++) {
                int v = augPath.get(i - 1);
                int w = augPath.get(i);
                f = Math.min(f, rG.getWeight(v, w));
            }
            maxFlow += f;

            // 根据增广路径更新rG
            for(int i = 1; i < augPath.size(); i ++){
                int v = augPath.get(i - 1);
                int w = augPath.get(i);

                rG.setWeight(v, w, rG.getWeight(v, w) - f);
                rG.setWeight(w, v, rG.getWeight(w, v) + f);
            }
        }
    }

    // 在残量图中找到增广路径，BFS的方式
    private ArrayList<Integer> getAugmentingPath(){
        Queue<Integer> q = new LinkedList<>();
        int[] pre = new int[network.V()]; // 存在对应顶点的前一个顶点
        Arrays.fill(pre, -1);

        q.add(s);
        pre[s] = s;
        while(!q.isEmpty()){
            int cur = q.remove();
            if(cur == t) break;
            for(int next: rG.adj(cur)) {
                if (pre[next] == -1 && rG.getWeight(cur, next) > 0) {
                    pre[next] = cur;
                    q.add(next);
                }
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        if(pre[t] == -1) return res;

        int cur = t;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public int result(){
        return maxFlow;
    }

    public int flow(int v, int w){
        if(!network.hasEdge(v, w))
            throw new IllegalArgumentException(String.format("No edge %d-%d", v, w));

        return rG.getWeight(w, v);
    }
}
