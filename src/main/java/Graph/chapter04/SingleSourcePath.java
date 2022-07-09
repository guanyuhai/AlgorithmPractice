package Graph.chapter04;

import Graph.chapter02.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description: 单源路径问题
 * @author: wtx
 * @createDate: 2020/6/3
 */
public class SingleSourcePath {

    private Graph G;
    private int s;      //源
    private boolean[] visited;
    private int[] prev;     //每个顶点的前驱

    public SingleSourcePath(Graph G, int s){
        G.validateVertex(s);
        this.G = G;
        this.s = s;

        this.visited = new boolean[G.V()];
        prev = new int[G.V()];
        for(int i=0; i< prev.length; i++)
            prev[i] = -1;

        dfs(s, s); //只需对s进行深度优先遍历
    }

    //源s与t是否可达
    public boolean isConnectedTo(int t){
        G.validateVertex(t);
        return visited[t];
    }

    // 获取单源路径
    public Iterable<Integer> path(int t){
        List<Integer> list = new ArrayList<>();
        // 先确认从源s 到 t 是否可达
        if (!isConnectedTo(t))
            return list;    //避免报空指针异常, 不要直接返回null, 尽管这个list为空的

        int cur = t;
        while (cur!=s) {
            list.add(cur);
            cur = prev[cur];
        }
        list.add(s);

        Collections.reverse(list);  //元素倒序
        return list;
    }

    private void dfs(int v, int parent) { //  parent->v
        visited[v] = true;
        prev[v] = parent;
        for (int w:G.adj(v))
            if (!visited[w])
                dfs(w,v);  // 从v这个顶点到w这个顶点
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g3.txt");
        SingleSourcePath singleSourcePath = new SingleSourcePath(graph,0);
        System.out.print("0->6: ");
        singleSourcePath.path(6).forEach(i -> System.out.print(i+" "));
    }
}
