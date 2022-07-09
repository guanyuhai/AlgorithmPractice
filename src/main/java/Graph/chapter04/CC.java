package Graph.chapter04;

import Graph.chapter02.Graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @description: Connected Component, 连通分量
 * @author: wtx
 * @createDate: 2020/6/3
 */
public class CC {
    private Graph G;
    private int[] visited;  // 改成int[] 包含更多的信息，如每个联通分量的标识
    private Integer ccCount = 0;

    public CC(Graph G){
        this.G = G;
        this.visited = new int[G.V()];

        // 默认将visited初始化为-1
        Arrays.fill(visited,-1);
        /*for (int i = 0; i < visited.length; i++)
            visited[i] = -1;*/

        //不管是否为连通图都可以遍历
        for (int v = 0; v < G.V(); v++)
            // 为-1时，该节点没有被访问过
            if (visited[v]==-1) {
                dfs(v,ccCount); //此时与v连通的顶点都会被遍历
                ccCount++;
            }
    }

    // 用ccCount的值做每个联通分量的标识
    private void dfs(int v, int ccid) {
        visited[v] = ccid;

        for (int w:G.adj(v))
            if (visited[w] == -1)
                dfs(w,ccid);
    }

    // 问题1 求无向图的联通分量的个数
    public Integer getCcCount() {
        return ccCount;
    }

    // 判断两个顶点是否在同一个联通分量中，或者说是否相连
    public boolean isConnected(int v,int w){
        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    // 返回各连通分量的顶点
    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[ccCount];
        for (int i = 0; i < ccCount; i++)
            res[i] = new ArrayList<>();

        for (int v = 0; v < G.V(); v++)
            res[visited[v]].add(v); // 使用visited中的标识做下标
        return res;
    }

    public static void main(String[] args) {
        Graph graph = new Graph("g2.txt");
        CC cc = new CC(graph);
        System.out.println(cc.getCcCount());
        for (int i: cc.visited)
            System.out.print(i+" ");

        System.out.println();
        // 测试两个顶点是否相连
        System.out.println(cc.isConnected(0,6));
        System.out.println(cc.isConnected(0,5));

        // 输出每个联通分量的顶点
        ArrayList<Integer>[] comp = cc.components();
        for (int ccid = 0; ccid < comp.length; ccid++) {
            System.out.print(ccid + ": ");
            for (int w : comp[ccid])
                System.out.print(w + " ");
            System.out.println();
        }
    }
}
