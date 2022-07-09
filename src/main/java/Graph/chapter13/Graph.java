package Graph.chapter13;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

// 无权图 (支持有向、无向)
public class Graph implements Cloneable {

    private int V;
    private int E;
    private TreeSet<Integer>[] adj;
    private boolean directed;
    private int[] inDegrees; // 入度
    private int[] outDegrees; // 出度

    // directed控制有向还是无向
    public Graph(String filename, boolean directed){
        this.directed = directed;

        File file = new File(filename);
        try(Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if(V < 0) throw new IllegalArgumentException("V must be non-negative");
            adj = new TreeSet[V];
            for(int i = 0; i < V; i ++)
                adj[i] = new TreeSet<Integer>();

            inDegrees = new int[V];
            outDegrees = new int[V];

            E = scanner.nextInt();
            if(E < 0) throw new IllegalArgumentException("E must be non-negative");

            for(int i = 0; i < E; i ++){
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);

                if(a == b) throw new IllegalArgumentException("Self Loop is Detected!");
                if(adj[a].contains(b)) throw new IllegalArgumentException("Parallel Edges are Detected!");

                // 是否为有向图
                adj[a].add(b);

                //出度、入度
                if (directed){
                    outDegrees[a]++; // a的出度多了一条边
                    inDegrees[b]++; // b的入度多了一条边
                }
                if(!directed) // 如果是无向图，才在adj[b]里添加a
                    adj[b].add(a);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public int indegree(int v){
        if (!directed)
            throw new RuntimeException("indegree only work in directed graph");
        validateVertex(v);
        return inDegrees[v];
    }

    public int outdegree(int v){
        if (!directed)
            throw new RuntimeException("indegree only work in directed graph");
        validateVertex(v);
        return outDegrees[v];
    }

    // 默认为无向图
    public Graph(String filename){
        this(filename, false);
    }

    // 判断是有向图还是无向图
    public boolean isDirected(){
        return directed;
    }

    public void validateVertex(int v){
        if(v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + "is invalid");
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public boolean hasEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v){
        if (directed)
            throw new RuntimeException("degree only works in undirected graph");
        validateVertex(v);
        return adj[v].size();
    }

    public void removeEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);

        // 维护入度和出度
        if(adj[v].contains(w)) {
            E--;
            if (directed){
                outDegrees[v]--;
                inDegrees[w]--;
            }
        }

        adj[v].remove(w);
        if(!directed) // 如果是无向图才执行
            adj[w].remove(v);
    }

    @Override
    public Object clone(){
        try{
            Graph cloned = (Graph) super.clone();
            cloned.adj = new TreeSet[V];
            for(int v = 0; v < V; v ++){
                cloned.adj[v] = new TreeSet<Integer>();
                for(int w: adj[v])
                    cloned.adj[v].add(w);
            }
            return cloned;
        }
        catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d, directed = %b\n", V, E, directed));
        for(int v = 0; v < V; v ++){
            sb.append(String.format("%d : ", v));
            for(int w : adj[v])
                sb.append(String.format("%d ", w));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args){
        Graph ug = new Graph("ug.txt", true);
        System.out.print(ug);

        Graph ug2 = new Graph("ug.txt", false);
        System.out.print(ug2);
    }

    public Graph(TreeSet<Integer>[] adj, boolean directed) {
        this.adj = adj;
        this.directed = directed;
        this.V  =adj.length;
        this.E = 0;

        inDegrees = new int[V];
        outDegrees = new int[V];

        for (int v = 0; v < V; v++)
            for (int w:adj(v)){
                outDegrees[v]++;
                inDegrees[v]++;
                this.E++;
            }
        if (!directed)
            this.E /= 2;
    }


    // 求解一个图的反图
    public Graph reverseGraph() {
        TreeSet<Integer>[] rAdj = new TreeSet[V];    //反图对应的邻接表
        for (int i = 0; i < V; i++)
            rAdj[i] = new TreeSet<>();

        for (int v = 0; v < V; v++)
            for (int w: adj(v))
                rAdj[w].add(v);

        return new Graph(rAdj,directed);
    }
}
