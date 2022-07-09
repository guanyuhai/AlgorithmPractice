package Graph.chapter06;

/**
 * @description: leetcode 785 https://leetcode-cn.com/problems/is-graph-bipartite/
 * graph[i]表示图中与节点i相连的所有节点
 * @author: wtx
 * @createDate: 2020/6/4
 */
public class Solution785 {
    private boolean[] visited;
    private int[] colors;   // 0为红色、1为白色
    private int[][] graph;

    public boolean isBipartite(int[][] graph) {
        this.graph = graph;
        int V = graph.length;
        visited = new boolean[V];
        colors = new int[V];  // 里面的值默认为0

        for (int v = 0; v < V; v++)
            if (!visited[v])
                if (!dfs(v,0))
                    return false;
        return true;
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;
        for(int w: graph[v]) {
            if (!visited[w]) {
                if (!dfs(w, 1 - color)) return false;
            } else if (colors[w] == colors[v]) return false;
        }
        return true;
    }
}
