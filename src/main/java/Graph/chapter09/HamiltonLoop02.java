package Graph.chapter09;

import Graph.chapter02.Graph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 基于状态压缩的方式，实现哈密尔顿回路
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/15 11:00
 */
public class HamiltonLoop02 {
    private Graph G;
    //private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonLoop02(Graph G){
        this.G = G;
        //visited = new boolean[G.V()];
        // 使用二进制来表示true和false
        int visited=0;
        pre = new int[G.V()];
        end = -1;
        dfs(visited, 0, 0, G.V());
    }

    // left记录还剩下多少个顶点没有被访问过
    private boolean dfs(int visited, int v, int parent, int left){
        // 将顶点v 设置为true
        //visited[v] = true;
        visited +=  (1<<v);
        pre[v] = parent;
        left--;
        // 在这里判断也可以
        if (left == 0 && G.hasEdge(v, 0)) {
            end = v; // 记录回到0的前一个顶点
            return true;
        }

        for(int w: G.adj(v)) {
            //if (!visited[w]) {
            if ((visited & (1<<w)) == 0) {
                if (dfs(visited, w, v, left)) return true;
            }
        }
        // 回溯
        //visited[v] = false;
        visited -= (1<<v);
        return false;
    }

    public ArrayList<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        // 如果end值还是-1，则表示没有哈密尔顿回路
        if(end == -1) return res;

        int cur = end;
        while(cur != 0){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(0);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){
        Graph g = new Graph("src\\main\\java\\Graph\\chapter09\\g.txt");
        HamiltonLoop hl = new HamiltonLoop(g);
        System.out.println(hl.result());

        Graph g2 = new Graph("src\\main\\java\\Graph\\chapter09\\g2.txt");
        HamiltonLoop hl2 = new HamiltonLoop(g2);
        System.out.println(hl2.result());
    }
}
