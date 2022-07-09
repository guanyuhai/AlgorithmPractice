package Graph.chapter13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description: 拓扑排序
 * @author: wtx
 * @createDate: 2020/6/8
 */
public class TopoSort {
    private Graph graph;
    private List<Integer> res;  // 存放排序的结果
    private boolean hasCycle = false;

    public TopoSort(Graph graph) {
        if (!graph.isDirected()) // 只作用有向图
            throw new IllegalArgumentException("only works in directed graph");
        this.graph = graph;

        res = new ArrayList<>();
        int[] inDegrees = new int[graph.V()]; // 存储每一个顶点的入度值

        Queue<Integer> queue = new LinkedList<>(); // 初始入度值为0的顶点
        for (int v = 0; v < graph.V(); v++){
            inDegrees[v] = graph.indegree(v);
            if (inDegrees[v]==0)
                queue.add(v);
        }

        //topo
        while (!queue.isEmpty()){
            int cur = queue.remove();
            res.add(cur);

            for (int next: graph.adj(cur)){
                inDegrees[next]--;
                if (inDegrees[next]==0)
                    queue.add(next);
            }
        }

        //环检测，不等于说明存在环
        if (res.size() != graph.V()){
            this.hasCycle = true;
            res.clear();
        }
    }

    public boolean isHasCycle(){
        return hasCycle;
    }

    public List<Integer> result(){
        return res;
    }
}
