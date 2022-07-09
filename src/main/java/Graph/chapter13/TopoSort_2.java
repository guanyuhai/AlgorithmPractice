package Graph.chapter13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 使用DFS的后序遍历做拓扑排序，该做法无法检测到图中的环
 * @description: 拓扑排序 2
 * @author: wtx
 * @createDate: 2020/6/8
 */
public class TopoSort_2 {
    private Graph graph;
    private List<Integer> res;  // 存放排序的结果
    private boolean hasCycle = false;

    public TopoSort_2(Graph graph) {
        if (!graph.isDirected())
            throw new IllegalArgumentException("only works in directed graph");
        this.graph = graph;

        res = new ArrayList<>();

        // 保证graph无环
        hasCycle = new DirectedCycleDetection(graph).hasCycle();

        if (hasCycle)
            throw new RuntimeException("has cycle");

        GraphDFS dfs = new GraphDFS(graph);
        dfs.post().forEach(i->res.add(i));
        // 使用DFS后序遍历后，顺序与tp排序相反
        Collections.reverse(res);
    }

    public boolean isHasCycle(){
        return hasCycle;
    }

    public List<Integer> result(){
        return res;
    }
}
