package Graph.chapter02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @description: 邻接表, 使用TreeSet, 红黑树
 * @author: wtx
 * @createDate: 2020/6/3
 */
public class AdjSet {
    private int V;
    private int E;
    private TreeSet<Integer>[] adj;  //链表数组

    //由文件来构建邻接表
    public AdjSet(String fileName) {
        File file = new File(fileName);

        try( Scanner scanner = new Scanner(file) ) {
            V = scanner.nextInt();
            if(V<0)
                throw new IllegalArgumentException("V < 0");

            adj = new TreeSet[V];
            for (int i = 0; i < V; i++)
                adj[i] = new TreeSet<>();

            E = scanner.nextInt();
            if(E<0)
                throw new IllegalArgumentException("V < 0");

            for (int i = 0; i < E; i++){
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                validateVertext(a);
                validateVertext(b);
                if (a==b)
                    throw new IllegalArgumentException("Self Loop is Detected");
                if (adj[a].contains(b)) //表示已经含有a、b 边
                    throw new IllegalArgumentException("Parallel Edges are Detected");

                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void validateVertext(int v){
        if (v < 0 || v>=this.V)
            throw new IllegalArgumentException(" v is invalid");
    }

    public int getV(){
        return this.V;
    }
    public int getE(){
        return this.E;
    }

    public boolean hasEdge(int v,int w){

        validateVertext(v);
        validateVertext(w);
        return adj[v].contains(w);
    }

    //返回v所有相邻的顶点
    public Iterable<Integer> adj(int v){    //屏蔽底层细节
        validateVertext(v);
        return adj[v];
    }

    //求顶点的度
    public int degree(int v){
        validateVertext(v);
        return adj[v].size();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for(int v = 0; v < V; v ++){
            sb.append(String.format("%d : ", v));
            for(int w : adj[v])
                sb.append(String.format("%d ", w));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjSet AdjList = new AdjSet("g.txt");   //注意g.txt文件要和src目录平级
        System.out.println(AdjList);
    }
}
