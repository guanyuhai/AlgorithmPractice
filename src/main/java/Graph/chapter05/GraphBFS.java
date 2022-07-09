package Graph.chapter05;

import Graph.chapter02.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description: 图的广度优先遍历 O(V+E)
 * @author: wtx
 * @createDate: 2020/6/4
 */
public class GraphBFS {
    private Graph graph;
    private boolean[] visited;
    private List<Integer> list = new ArrayList<>();

    public GraphBFS(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.V()];

        for (int v = 0; v < graph.V(); v++)
            if (!visited[v])
                bfs(v);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()){
            Integer integer = queue.remove();
            list.add(integer);

            // 每次将相邻的边添加到队列
            for(int w: graph.adj(integer)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public List<Integer> order() {
        return list;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        GraphBFS graphBFS = new GraphBFS(graph);
        System.out.println(graphBFS.order());
    }
}
