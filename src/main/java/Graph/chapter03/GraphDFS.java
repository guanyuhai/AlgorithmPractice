package Graph.chapter03;

import Graph.chapter02.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 图的深度优先遍历 O(V+E) 
 * @author: wtx
 * @createDate: 2020/6/3
 */
public class GraphDFS {
    private Graph G;
    private boolean[] visited;
    // 存储遍历结果
    private List<Integer> order = new ArrayList<>();

    public GraphDFS(Graph G){
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
        order.add(v);

        for (int w:G.adj(v))
            if (!visited[w])
                dfs(w);
        // 放在后面，则为后序遍历
    }


    public List<Integer> getOrder() {
        return order;
    }

    public static void main(String[] args) {
        Graph g = new Graph("g2.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        graphDFS.getOrder().stream().forEach(i -> System.out.print(i+" "));
    }
}
