package LeetCode.Graph;

/**
 * 判断二分图
 * leetcode 785 https://leetcode-cn.com/problems/is-graph-bipartite/
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/14 15:46
 */
public class LeetCode785 {
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
