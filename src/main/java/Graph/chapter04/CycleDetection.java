package Graph.chapter04;

import Graph.chapter02.Graph;

/**
 * @description: 无向图的环检测
 * @author: wtx
 * @createDate: 2020/6/3
 */
public class CycleDetection {
    private Graph G;
    private boolean[] visited;
    private boolean hasCycle = false;

    public CycleDetection(Graph G){
        this.G = G;
        this.visited = new boolean[G.V()];

        //不管是否为连通图都可以遍历
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if (dfs(v, v)) {
                    this.hasCycle = true;
                    break;
                }
            }
        }
    }

    // 从顶点v开始, 判断图中是否有环
    // 返回值的设计可以使遍历的时候可以提前终止
    private boolean dfs(int v, int parent) {
        visited[v] = true;

        for (int w:G.adj(v))
            if (!visited[w]){
                if(dfs(w,v)) // 从v出发，有环直接返回
                    return true;
            }
            else if ( w!= parent)   //找到一个环
                return true;
        return false;
    }

    public boolean isHasCycle(){
        return this.hasCycle;
    }


    public static void main(String[] args) {

        Graph g = new Graph("g.txt");
        CycleDetection graphDFS = new CycleDetection(g);
    }
}
