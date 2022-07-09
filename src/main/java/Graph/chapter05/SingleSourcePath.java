package Graph.chapter05;

import Graph.chapter02.Graph;

import java.util.*;

/**
 * @description: 使用BFS解决单源路径问题
 * @author: wtx
 * @createDate: 2020/6/4
 */
public class SingleSourcePath {
    private Graph graph;
    private int source;
    private boolean[] visited;
    private int[] prev;

    public SingleSourcePath(Graph graph, int source) {
        this.graph = graph;
        this.source = source;
        this.visited = new boolean[graph.V()];
        this.prev = new int[graph.V()];

        for (int i = 0; i < prev.length; i++)
            prev[i] = -1;

        bfs(source);
    }

    private void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        prev[s] = s;
        visited[s] = true;

        while (!queue.isEmpty()){
            Integer v = queue.remove();

            for(int w: graph.adj(v))
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    prev[w] = v;  // 表明可以从v到w，v是w的前置节点
                }
        }
    }

    // s、t是否可达
    public boolean isConnected( int t){
        this.graph.validateVertex(t);
        return visited[t];
    }

    // 获取从源节点s 到 终点t 的单源路径
    public List<Integer> getPathTo(int t){
        List<Integer> path = new ArrayList<>();
        if (!isConnected(t))
            return path;

        int cur = t;
        while (cur!=source){
            path.add(cur);
            cur = prev[cur];
        }
        path.add(cur);
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g.txt");
        SingleSourcePath singleSourcePath = new SingleSourcePath(graph,0);
        System.out.println(singleSourcePath.getPathTo(6));
    }
}
