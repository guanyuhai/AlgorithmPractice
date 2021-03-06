package Graph.chapter15;

import java.util.*;

public class Hungarian_dfs {
    private Graph G;
    private int maxMatching;
    private int matching[];
    private boolean visited[];

    public Hungarian_dfs(Graph G){
        BipartitionDetection bd = new BipartitionDetection(G);
        if(!bd.isBipartite())
            throw new IllegalArgumentException("BipartiteMatching only works for bipartite graph.");

        this.G = G;

        int[] colors = bd.colors();

        matching = new int[G.V()];
        Arrays.fill(matching, -1);

        visited = new boolean[G.V()];
        for(int v = 0; v < G.V(); v ++)
            if(colors[v] == 0 && matching[v] == -1){
                Arrays.fill(visited, false);
                if(dfs(v)) maxMatching ++;
            }
    }

    private boolean dfs(int v){
        visited[v] = true;
        for(int u: G.adj(v))
            if(!visited[u]){
                visited[u] = true;
                if(matching[u] == -1 || dfs(matching[u])){
                    matching[u] = v;
                    matching[v] = u;
                    return true;
                }
            }
        return false;
    }

    public int maxMatching(){
        return maxMatching;
    }

    public boolean isPerfectMatching(){
        return maxMatching * 2 == G.V();
    }

    public static void main(String[] args){

        Graph g = new Graph("g.txt");
        Hungarian_dfs hungarianDfs = new Hungarian_dfs(g);
        System.out.println(hungarianDfs.maxMatching());

        Graph g2 = new Graph("g2.txt");
        Hungarian_dfs hungarianDfs2 = new Hungarian_dfs(g2);
        System.out.println(hungarianDfs2.maxMatching());
    }
}
