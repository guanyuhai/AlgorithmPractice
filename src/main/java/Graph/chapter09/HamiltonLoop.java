package Graph.chapter09;

import Graph.chapter02.Graph;

import java.util.ArrayList;
import java.util.Collections;

public class HamiltonLoop {
    private Graph G;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonLoop(Graph G){
        this.G = G;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        end = -1;
        dfs(0, 0, G.V());
    }

    // left记录还剩下多少个顶点没有被访问过
    private boolean dfs(int v, int parent, int left){
        visited[v] = true;
        pre[v] = parent;
        left--;
        // 在这里判断也可以
        if (left == 0 && G.hasEdge(v, 0)) {
            end = v; // 记录回到0的前一个顶点
            return true;
        }

        for(int w: G.adj(v)) {
            if (!visited[w]) {
                if (dfs(w, v, left)) return true;
            }
            //else if (w == 0 && allVisited()) { // w又回到0，并且所有的顶点都被访问过，则返回true
            /*else if (w == 0 && left==0) { // 改成用整数left来判断所有的顶点都被访问过
                end = v; // 记录回到0的前一个顶点
                return true;
            }*/
        }
        // 回溯
        visited[v] = false;
        return false;
    }

    private boolean allVisited(){
        for(int v = 0; v < G.V(); v ++)
            if(!visited[v]) return false;
        return true;
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
