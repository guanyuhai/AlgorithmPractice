package Graph.chapter05;

import Graph.chapter02.Graph;

import java.util.LinkedList;
import java.util.Queue;

// 使用bfs实现二分图检测
public class BipartitionDetection {
    private Graph G;

    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph G){

        this.G = G;
        visited = new boolean[G.V()];
        colors = new int[G.V()];
        for(int i = 0; i < G.V(); i ++)
            colors[i] = -1;

        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                if(!bfs(v)){
                    isBipartite = false;
                    break;
                }
    }

    // 从顶点s出发，看整张图是否是二分图
    private boolean bfs(int s){

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        colors[s] = 0;  // 起始点的染色值为0

        while(!queue.isEmpty()){
            int v = queue.remove();

            for(int w: G.adj(v))
                // 如果w没有被遍历过，需要被染色
                if(!visited[w]){
                    queue.add(w);
                    visited[w] = true;
                    // 给顶点w染的色，与v的颜色不同
                    colors[w] = 1 - colors[v];
                }
                else if(colors[v] == colors[w])
                    return false;
        }
        return true;
    }

    public boolean isBipartite(){
        return isBipartite;
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        BipartitionDetection bipartitionDetection = new BipartitionDetection(g);
        System.out.println(bipartitionDetection.isBipartite);
        // true

        Graph g2 = new Graph("g2.txt");
        BipartitionDetection bipartitionDetection2 = new BipartitionDetection(g2);
        System.out.println(bipartitionDetection2.isBipartite);
        // false

        Graph g3 = new Graph("g3.txt");
        BipartitionDetection bipartitionDetection3 = new BipartitionDetection(g3);
        System.out.println(bipartitionDetection3.isBipartite);
        //true
    }
}