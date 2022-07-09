package Graph.chapter03;

import Graph.chapter02.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @description: 深度优先遍历的非递归实现
 * @author: wtx
 * @createDate: 2020/6/4
 */
public class GraphDFS_NON_RE {

    private Graph G;
    private boolean[] visited;
    private List<Integer> order = new ArrayList<>();

    public GraphDFS_NON_RE(Graph G){
        this.G = G;
        this.visited = new boolean[G.V()];

        //不管是否为连通图都可以遍历
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(v);
    }

    private void dfs(int v) {
        Stack<Integer> stack = new Stack<>();
        stack.add(v);
        visited[v] = true;

        while (!stack.isEmpty()){
            // 遍历栈顶的元素
            Integer cur = stack.pop();
            order.add(cur);
            for (int w: G.adj(cur)) {
                // 遍历与cur相邻的顶点
                if (!visited[w]) {   //顶点w未被遍历
                    stack.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public List<Integer> getOrder() {
        return order;
    }

    public static void main(String[] args) {
        GraphDFS_NON_RE dfs_non_re = new GraphDFS_NON_RE(new Graph("g2.txt"));
        dfs_non_re.getOrder().forEach(i -> System.out.print(i+" "));
    }
}
