package Graph.chapter08;

import Graph.chapter02.Graph;

import java.util.HashSet;

// 寻找割点
public class FindCutPoints {
    private Graph G;
    private boolean[] visited;

    private int[] ord;
    private int[] low;
    private int cnt;

    private HashSet<Integer> res;

    public FindCutPoints(Graph G){
        this.G = G;
        visited = new boolean[G.V()];

        res = new HashSet<>();
        ord = new int[G.V()];
        low = new int[G.V()];
        cnt = 0;

        for(int v = 0; v < G.V(); v ++)
            if(!visited[v])
                dfs(v, v);
    }

    private void dfs(int v, int parent){
        visited[v] = true;
        ord[v] = cnt;
        low[v] = ord[v];
        cnt ++;

        // 根节点的孩子节点，指的是遍历树中的节点
        int child = 0;
        for(int w: G.adj(v))
            if(!visited[w]){
                dfs(w, v);
                low[v] = Math.min(low[v], low[w]);
                // 如果不是根节点，low[w] >= ord[v] 判断是否是割点
                if(v != parent && low[w] >= ord[v])
                    res.add(v);
                child ++;
                // 对根节点进行处理
                if(v == parent && child > 1)
                    res.add(v);
            } else if(w != parent)
                low[v] = Math.min(low[v], low[w]);
    }

    public HashSet<Integer> result(){
        return res;
    }

    public static void main(String[] args){
        Graph g = new Graph("src\\main\\java\\Graph\\chapter08\\g.txt");
        FindCutPoints fc = new FindCutPoints(g);
        System.out.println("Cut Points in g : " + fc.result());

        Graph g2 = new Graph("src\\main\\java\\Graph\\chapter08\\g2.txt");
        FindCutPoints fc2 = new FindCutPoints(g2);
        System.out.println("Cut Points in g2 : " + fc2.result());

        Graph tree = new Graph("src\\main\\java\\Graph\\chapter08\\tree.txt");
        FindCutPoints fc3 = new FindCutPoints(tree);
        System.out.println("Cut Points in tree : " + fc3.result());
    }
}
