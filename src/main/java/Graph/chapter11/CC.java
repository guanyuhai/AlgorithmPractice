package Graph.chapter11;

import java.util.ArrayList;

/**
 * @description: Connected Component, 连通分量 支持带权图
 * @author: wtx
 * @createDate: 2020/6/3
 */
public class CC {
    private WeightedGraph G;
    private int[] visited;
    private Integer ccCount = 0;

    public CC(WeightedGraph G){
        this.G = G;
        this.visited = new int[G.V()];

        for (int i = 0; i < visited.length; i++)
            visited[i] = -1;

        //不管是否为连通图都可以遍历
        for (int v = 0; v < G.V(); v++)
            if (visited[v]==-1) {
                dfs(v,ccCount); //此时与v连通的顶点都会被遍历
                ccCount++;
            }
    }

    // 返回各连通分量的顶点
    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] res = new ArrayList[ccCount];
        for (int i = 0; i < ccCount; i++)
            res[i] = new ArrayList<>();

        for (int v = 0; v < G.V(); v++)
            res[visited[v]].add(v);
        return res;
    }

    private void dfs(int v, int ccCount) {

        visited[v] = ccCount;

        for (int w:G.adj(v))
            if (visited[w] == -1)
                dfs(w,ccCount);
    }

    public Integer getCcCount() {
        return ccCount;
    }

    public boolean isConnected(int v,int w){

        G.validateVertex(v);
        G.validateVertex(w);
        return visited[v] == visited[w];
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph("g.txt");
        CC cc = new CC(graph);
        System.out.println(cc.getCcCount());
        for (int i: cc.visited)
            System.out.print(i+" ");

        System.out.println();
        System.out.println(cc.isConnected(0,6));
        System.out.println(cc.isConnected(0,5));
    }
}
