package Graph.chapter04;

import Graph.chapter02.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 找到路径就返回, 不一定要遍历所有的顶点
 * @author: wtx
 * @createDate: 2020/6/3
 */
public class Path {
    private Graph graph;
    private int s;  //源点
    private int t;  //终点

    private boolean[] visited;
    private int[] prev;

    public Path(Graph graph, int s, int t) {
        graph.validateVertex(s);
        graph.validateVertex(t);
        this.graph = graph;
        this.s = s;
        this.t = t;

        visited = new boolean[graph.V()];
        prev = new int[graph.V()];
        for (int i = 0; i < prev.length; i++)
            prev[i] = -1;

        dfs(s,s);
    }

    private boolean dfs(int v, int parent){
        visited[v] = true;
        prev[v] = parent;

        if (v==t) return true;

        for (int w : graph.adj(v))
            if (!visited[w])
                if (dfs(w,v))
                    return true;

        return false;
    }

    //s、t是否可达, 检测到可达时及时终止递归, 避免时间消耗
    public boolean isConnected(){
        return visited[t];
    }

    public Iterable<Integer> path(){
        List<Integer> list = new ArrayList<>();
        if (!isConnected())
            return list;

        int cur = this.t;
        while (cur!=this.s){
            list.add(cur);
            cur = prev[cur];
        }
        list.add(this.s);
        Collections.reverse(list);
        return list;
    }
}
