package Graph.chapter06;

import java.util.HashSet;

/**
 * @description: leetcode 695 https://leetcode-cn.com/problems/max-area-of-island/
 * 给定的矩阵grid 的长度和宽度都不超过 50。
 * @author: wtx
 * @createDate: 2020/6/4
 */
public class Solution695 {
    // 四联通：找到一个点上下左右的其他的点
    private int[][] dirs = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    private int R, C;   //raw, column
    private int[][] grid;
    private HashSet<Integer>[] graph;   // 每个顶点都有对应的hashSet, 里面存放与该顶点相连的顶点
    private boolean[] visited;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null)
            return 0;
        R = grid.length;
        C = grid[0].length;
        if (R==0 || C==0)
            return 0;
        this.grid = grid;

        //缺点: 建图的操作比较多余
        graph = constructGraph();

        int res = 0;
        visited = new boolean[graph.length];
        for (int v = 0; v < graph.length; v++) {
            // 将顶点转为对应的二维坐标
            int x = v / C;
            int y = v % C;
            if (!visited[v] && grid[x][y] == 1)
                res = Math.max(res, dfs(v));  // 找出最大面积的陆地
        }

        return res;
    }

    // 返回顶点v 所在的连通分量包含的顶点数
    private int dfs(int v) {
        int res = 1;
        visited[v] = true;
        for (int w: graph[v])
            if (!visited[w])
                res += dfs(w);
        return res;
    }

    // 建图
    private HashSet<Integer>[] constructGraph() {
        HashSet<Integer>[] graph = new HashSet[R*C];    //一共有R*C个顶点
        for (int i = 0; i < graph.length; i++)
            graph[i] = new HashSet<>();

        for (int v = 0; v < graph.length; v++) {
            // 将顶点转为对应的二维坐标
            int x = v / C;
            int y = v % C;
            if (grid[x][y] == 1){  // ==1 表示等于陆地
                for (int d = 0; d < 4; d++) {
                    int nextX = x + dirs[d][0];
                    int nextY = y + dirs[d][1];
                    // 判断当前顶点的上下左右的顶点是否为陆地
                    if (inArea(nextX,nextY) && grid[nextX][nextY] == 1){
                        // 将二维坐标再转为顶点
                        int next = nextX * C + nextY;
                        // 将其顶点分别添加到顶点中
                        graph[v].add(next);
                        graph[next].add(v);
                    }
                }
            }
        }
        return graph;
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }
}
