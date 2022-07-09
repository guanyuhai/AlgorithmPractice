package Graph.chapter11;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// 无向带权图
public class WeightedGraph  implements Cloneable{
    private int V;
    private int E;
    private TreeMap<Integer,Integer>[] adj; //adj[i] 表示与顶点i相邻的顶点以及对应的权值

    public WeightedGraph(String filename){
        File file = new File(filename);

        try(Scanner scanner = new Scanner(file)){
            V = scanner.nextInt();
            if(V < 0)
                throw new IllegalArgumentException("V must be non-negative");
            adj = new TreeMap[V];
            for(int i = 0; i < V; i ++)
                adj[i] = new TreeMap<Integer,Integer>();

            E = scanner.nextInt();
            if(E < 0)
                throw new IllegalArgumentException("E must be non-negative");

            for(int i = 0; i < E; i ++){
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                // 读取第三个数，权值
                int weight = scanner.nextInt();

                if(a == b)
                    throw new IllegalArgumentException("Self Loop is Detected!");
                if(adj[a].containsKey(b))
                    throw new IllegalArgumentException("Parallel Edges are Detected!");

                adj[a].put(b,weight);
                adj[b].put(a,weight);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
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
        return adj[v].containsKey(w);
    }

    // 返回顶点v 相邻的边
    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v].keySet();
    }

    public int degree(int v){
        validateVertex(v);
        return adj[v].size();
    }

    // 根据顶点v、w 来获取对应的权值
    public int getWeight(int v, int w){
        if (hasEdge(v,w))
            return adj[v].get(w);
        throw new IllegalArgumentException("No edge");
    }

    @Override
    public Object clone(){
        try{
            WeightedGraph cloned = (WeightedGraph) super.clone();
            cloned.adj = new TreeMap[V];
            for(int v = 0; v < V; v ++){
                cloned.adj[v] = new TreeMap<Integer, Integer>();
                for(Map.Entry<Integer, Integer> entry: adj[v].entrySet())
                    cloned.adj[v].put(entry.getKey(), entry.getValue());
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

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for(int v = 0; v < V; v ++){
            sb.append(String.format("%d : ", v));
            for(Map.Entry<Integer, Integer> entry: adj[v].entrySet())
                sb.append(String.format("(%d: %d) ", entry.getKey(), entry.getValue()));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args){

        WeightedGraph g = new WeightedGraph("g.txt");
        System.out.print(g);
    }
}
