package Graph.chapter04;

import Graph.chapter02.Graph;

/*
* 任意两点之间的路径问题
* */
public class AllPairsPath {

    private Graph G;
    private SingleSourcePath[] paths;

    public AllPairsPath(Graph G){
        this.G = G;
        paths = new SingleSourcePath[G.V()];    //图的每个顶点都作为源点
        for(int v = 0; v < G.V(); v ++)
            paths[v] = new SingleSourcePath(G, v);
    }

    public boolean isConnectedTo(int s, int t){
        G.validateVertex(s);
        return paths[s].isConnectedTo(t);
    }

    // 找到任意两点之间的路径
    public Iterable<Integer> path(int s, int t){
        G.validateVertex(s);
        return paths[s].path(t);
    }
}
