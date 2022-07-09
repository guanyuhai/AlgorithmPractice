package Graph.chapter10;

import Graph.chapter02.Graph;
import Graph.chapter04.CC;

import java.util.ArrayList;
import java.util.Stack;

// 判断欧拉回路
public class EulerLoop {
    private Graph G;

    public EulerLoop(Graph G){
        this.G = G;
    }

    // 判断是否存在欧拉回路
    private boolean hasEulerLoop(){
        CC cc = new CC(G);
        // 判断是否是联通图
        if(cc.getCcCount() > 1) return false;

        for(int v = 0; v < G.V(); v ++)
            if(G.degree(v) % 2 == 1) return false;
        return true;
    }

    // 找到欧拉回路的路径
    public ArrayList<Integer> result(){
        ArrayList<Integer> res = new ArrayList<>();
        if(!hasEulerLoop()) return res;

        // 备份
        Graph g = (Graph) G.clone();

        Stack<Integer> stack = new Stack<>();
        int curv = 0;
        stack.push(curv);
        while(!stack.isEmpty()){
            // 不等于0， 表明还有路可以走
            if(g.degree(curv) != 0){
                stack.push(curv);
                int w = g.adj(curv).iterator().next();
                g.removeEdge(curv, w);
                curv = w;
            }
            else{
                // 找到一个环，开始回退
                res.add(curv);
                curv = stack.pop();
            }
        }
        return res;
    }

    public static void main(String[] args){
        Graph g = new Graph("src\\main\\java\\Graph\\chapter10\\g.txt");
        EulerLoop el = new EulerLoop(g);
        System.out.println(el.result());

        Graph g2 = new Graph("src\\main\\java\\Graph\\chapter10\\g2.txt");
        EulerLoop el2 = new EulerLoop(g2);
        System.out.println(el2.result());
    }
}
