package Graph.chapter03;

import Graph.chapter02.AdjMatrix;

import java.util.ArrayList;

/**
 * 基于邻接矩阵表示的图的深度优先遍历
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/13 18:41
 */
public class AdjMatrixDFS {
    private AdjMatrix G;
    private boolean[] visited;

    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();

    // 参数类型更改为AdjMatrix即可
    public AdjMatrixDFS(AdjMatrix G){
        this.G = G;
        this.visited = new boolean[G.V()];

        //不管是否为连通图都可以遍历，因为每个节点都验证是否访问过
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(v);
    }

    private void dfs(int v) {
        visited[v] = true;
        // 放在前面，为先序遍历
        pre.add(v);

        for (int w:G.adj(v))
            if (!visited[w])
                dfs(w);
        // 放在后面，则为后序遍历
        post.add(v);
    }
}
