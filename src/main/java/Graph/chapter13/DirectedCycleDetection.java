package Graph.chapter13;

/**
 * @description: 有向图的环检测
 * @author: wtx
 * @createDate: 2020/6/7
 */
public class DirectedCycleDetection {

    private Graph G;
    private boolean[] visited;
    private boolean[] onPath;   //是否在当前路径!
    private boolean hasCycle = false;

    public DirectedCycleDetection(Graph G){
        // 判断是否为无向图
        if(!G.isDirected())
            throw new IllegalArgumentException("CycleDetection only works in directed graph.");

        this.G = G;
        visited = new boolean[G.V()];
        onPath = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                if(dfs(v, v)){
                    hasCycle = true;
                    break;
                }
    }

    // 从顶点 v 开始，判断图中是否有环
    private boolean dfs(int v, int parent){
        visited[v] = true;
        onPath[v] = true;
        for(int w: G.adj(v))
            if(!visited[w]){
                if(dfs(w, v)) return true;
            } // 从0到1，从1到0，是一个环，所以不判断 w!=parent
            else if(onPath[w])
                return true;

        onPath[v] = false;
        return false;
    }

    public boolean hasCycle(){
        return hasCycle;
    }
}
