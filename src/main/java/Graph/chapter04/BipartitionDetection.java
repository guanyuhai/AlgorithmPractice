package Graph.chapter04;

import Graph.chapter02.Graph;

/**
 * @description: 二分图检测
 * @author: wtx
 * @createDate: 2020/6/3
 */
public class BipartitionDetection {

    private int[] colors;
    private boolean isBipartition = true;
    private Graph G;
    private boolean[] visited;


    public BipartitionDetection(Graph G) {
        this.G = G;
        this.visited = new boolean[G.V()];
        this.colors = new int[G.V()];
        for (int i = 0; i < colors.length; i++)
            colors[i] = -1;

        //不管是否为连通图都可以遍历
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                if (!dfs(v, 0)) {
                    this.isBipartition = false;
                    break;
                }
            }
        }
    }

    // 是否为二分图
    public boolean isBipartition(){
        return this.isBipartition;
    }

    private boolean dfs(int v, int color) {
        visited[v] = true;
        colors[v] = color;

        for (int w:G.adj(v))
            if (!visited[w]) {
                if (!dfs(w, 1 - color))
                    return false;
            }
            else if (colors[w] == colors[v])    // 这条边两个端点的颜色相同
                return false;
        return true;
    }


    public static void main(String[] args) {
        Graph g = new Graph("g.txt");
        BipartitionDetection graphDFS = new BipartitionDetection(g);
    }
}
