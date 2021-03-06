package Graph.chapter15;

// 二分图匹配问题
public class BipartiteMatching {
    private Graph G;
    private int maxMatching;

    public BipartiteMatching(Graph G){
        BipartitionDetection bd = new BipartitionDetection(G);
        if(!bd.isBipartite())
            throw new IllegalArgumentException("BipartiteMatching only works for bipartite graph.");

        this.G = G;
        // 通过colors 得知顶点属于哪个二分图部分
        int[] colors = bd.colors();

        // 源点为 V, 汇点为 V + 1
        WeightedGraph network = new WeightedGraph(G.V() + 2, true);
        // 构造残量图，增加了源点和汇点，且将一个无向图转为有向图
        for(int v = 0; v < G.V(); v ++) {
            if(colors[v] == 0) network.addEdge(G.V(), v, 1); // 添加从源点到V的边
            else network.addEdge(v, G.V() + 1, 1); // 从v到汇点的边

            for (int w : G.adj(v)) {
                if (v < w) {
                    if (colors[v] == 0) network.addEdge(v, w, 1);
                    else network.addEdge(w, v, 1);
                }
            }
        }

        MaxFlow maxFlow = new MaxFlow(network, G.V(), G.V() + 1);
        maxMatching = maxFlow.result();
    }

    public int maxMatching(){
        return maxMatching;
    }

    // 最大匹配结果是不是完全匹配，即所有的点都被匹配了
    public boolean isPerfectMatching(){
        return maxMatching * 2 == G.V();
    }

    public static void main(String[] args){
        Graph g = new Graph("g.txt");
        BipartiteMatching bm = new BipartiteMatching(g);
        System.out.println(bm.maxMatching());

        Graph g2 = new Graph("g2.txt");
        BipartiteMatching bm2 = new BipartiteMatching(g2);
        System.out.println(bm2.maxMatching());
    }
}
