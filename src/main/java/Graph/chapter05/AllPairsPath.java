package Graph.chapter05;

import Graph.chapter02.Graph;

import java.util.List;

/**
 * @description: 任意顶点的路径问题
 * @author: wtx
 * @createDate: 2020/6/4
 */
public class AllPairsPath {

    private Graph graph;
    private SingleSourcePath[] paths;

    public AllPairsPath(Graph graph) {
        this.graph = graph;
        paths = new SingleSourcePath[graph.V()];
        for (int i = 0; i < graph.V(); i++)
            paths[i] = new SingleSourcePath(graph, i);

    }

    //任意两点是否可达
    public boolean isConnected(int s, int w) {
        return paths[s].isConnected(w);
    }

    //任意两点的路径
    public List<Integer> getPathFromStoW(int s, int w){
        return paths[s].getPathTo(w);
    }

    public static void main(String[] args) {
        AllPairsPath allPairsPath = new AllPairsPath(new Graph("g.txt"));
        allPairsPath.getPathFromStoW(0,6).forEach(i -> System.out.print(i+" "));
    }
}
