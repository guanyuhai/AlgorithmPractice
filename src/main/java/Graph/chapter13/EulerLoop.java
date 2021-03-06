package Graph.chapter13;

import java.util.ArrayList;
import java.util.Stack;

// 无向图存在欧拉回路的充分必要条件：每个顶点的度为偶数
public class EulerLoop {

    private Graph G;

    public EulerLoop(Graph G){
        if(G.isDirected()) // 提示只能在无向图中求解
            throw new IllegalArgumentException("EulerLoop only works in undirected graph");
        this.G = G;
    }

    private boolean hasEulerLoop(){
        CC cc = new CC(G);
        if(cc.count() > 1) return false;

        for(int v = 0; v < G.V(); v ++)
            if(G.degree(v) % 2 == 1) return false;

        return true;
    }

    public ArrayList<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if(!hasEulerLoop()) return res;

        Graph g = (Graph)G.clone();

        Stack<Integer> stack = new Stack<>();
        int curv = 0;
        stack.push(curv);
        while(!stack.isEmpty()){
            if(g.degree(curv) != 0){
                stack.push(curv);
                int w = g.adj(curv).iterator().next();
                g.removeEdge(curv, w);
                curv = w;
            }
            else{
                res.add(curv);
                curv = stack.pop();
            }
        }
        return res;
    }
}
