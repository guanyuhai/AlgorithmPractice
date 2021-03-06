package Graph.chapter09;

import Graph.chapter02.Graph;

import java.util.ArrayList;
import java.util.Collections;

// 实现哈密尔顿路径
public class HamiltonPath {
    private Graph G;
    private int s;
    private boolean[] visited;
    private int[] pre;
    private int end;

    public HamiltonPath(Graph G, int s){
        this.G = G;
        this.s = s;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        end = -1;
        // 起始点改成用户传递的源点s
        dfs(s, s, G.V());
    }

    private boolean dfs(int v, int parent, int left){
        visited[v] = true;
        pre[v] = parent;
        left --;
        // 哈密尔顿路径只需要判断所有顶点是否都遍历过，不用判断是否回到顶点
        if(left == 0){
            end = v;
            return true;
        }

        for(int w: G.adj(v))
            if(!visited[w]){
                if(dfs(w, v, left)) return true;
            }

        visited[v] = false;
        return false;
    }

    public ArrayList<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if(end == -1) return res;

        int cur = end;
        while(cur != s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        HamiltonPath hp = new HamiltonPath(g, 0);
        System.out.println(hp.result());

        HamiltonPath hp2 = new HamiltonPath(g, 1);
        System.out.println(hp2.result());
    }
}
