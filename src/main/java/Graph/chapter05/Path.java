package Graph.chapter05;

import Graph.chapter02.Graph;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

/**
 * 广度优先遍历解决单源路径问题，提前返回的方式
 */
public class Path {
    private Graph G;
    private int s, t;

    private int[] pre;
    private boolean[] visited;

    public Path(Graph G, int s, int t){
        G.validateVertex(s);
        G.validateVertex(t);

        this.G = G;
        this.s = s;
        this.t = t;
        visited = new boolean[G.V()];
        pre = new int[G.V()];
        for(int i = 0; i < pre.length; i ++)
            pre[i] = -1;

        bfs();
        // 通过打印visited数组进行验证
        for(boolean e: visited)
            System.out.print(e + " ");
        System.out.println();
    }


    private void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        pre[s] = s;

        if(s == t) return; // 如果起始点就是终止点，提前返回

        while(!queue.isEmpty()){
            int v = queue.remove();

            for(int w: G.adj(v))
                if(!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                    pre[w] = v;
                    if(w == t) return; // 如果发现w就是终止点，那么就找到了从s到t的路径，直接返回
                }
        }
    }

    public boolean isConnected(){
        return visited[t];
    }

    // 逻辑不变
    public Iterable<Integer> path(){
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(!isConnected()) return res;

        int cur = t;
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
        Path path = new Path(g, 0, 6);
        System.out.println("0 -> 6 : " + path.path());

        Path path2 = new Path(g, 0, 5);
        System.out.println("0 -> 5 : " + path2.path());

        Path path3 = new Path(g, 0, 1);
        System.out.println("0 -> 1 : " + path3.path());
    }
}
