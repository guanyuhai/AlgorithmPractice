package Graph.chapter02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @description: 邻接矩阵
 * @author: wtx
 * @createDate: 2020/6/2
 */
public class AdjMatrix {

    private int V;
    private int E;
    private int[][] adj;

    //由文件来构建邻接矩阵
    public AdjMatrix(String fileName) {
        File file = new File(fileName);

        try( Scanner scanner = new Scanner(file) ) {
            V = scanner.nextInt();
            if(V<0)
                throw new IllegalArgumentException("V < 0");
            adj = new int[V][V];
            E = scanner.nextInt();
            if(E<0)
                throw new IllegalArgumentException("V < 0");

            for (int i = 0; i < E; i++){
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                validateVertext(a);
                validateVertext(b);
                // 判断是否是自环边
                if (a==b)
                    throw new IllegalArgumentException("Self Loop is Detected");
                // 判断是否是平行边
                if (adj[a][b]==1)
                    throw new IllegalArgumentException("Parallel Edges are Detected");

                adj[a][b] = 1;
                adj[b][a] = 1;
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

    // 是否存在某条边
    public boolean hasEdge(int v,int w){
        validateVertext(v);
        validateVertext(w);
        return adj[v][w] == 1;
    }

    //返回v所有相邻的顶点
    public List<Integer> adj(int v){
        validateVertext(v);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < V; i++)
            if (adj[v][i]==1)
                list.add(i);
        return list;
    }

    //求顶点的度
    public int degree(int v){
        return adj(v).size();
    }

    public int V(){
        return V;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for(int i = 0; i < V; i ++){
            for(int j = 0; j < V; j ++)
                sb.append(String.format("%d ", adj[i][j]));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("g2.txt");   //注意g.txt文件要和src目录平级
        System.out.println(adjMatrix);
    }
}
