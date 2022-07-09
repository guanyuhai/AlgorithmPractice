package Graph.chapter07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @description:  BFS: https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/
 * 这条从左上角到右下角的最短畅通路径的长度 --> BFS, 求无向图的最短路径更加简单
 * @author: wtx
 * @createDate: 2020/6/6
 */
public class Solution1091 {

    private int raw;
    //八连通
    private int[][] dirs = {{-1,0}, {-1,1}, {1,1}, {1,0}, {1,-1}, {-1,-1}, {0,-1}, {0,1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        raw = grid.length;
        boolean[][] visited = new boolean[raw][raw];    //方形 N×N
        int[][] dis = new int[raw][raw];    //记录原点任意可达顶点的最短路径长度

        if (grid[0][0]==1)
            return -1;
        if (raw == 1)
            return 1;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0][0] = true;
        dis[0][0] = 1;

        while(!queue.isEmpty()){
            int cur = queue.remove();
            int curX = cur / raw;
            int curY = cur % raw;
            for (int d = 0; d < 8; d++) {
                int nextX = curX + dirs[d][0];
                int nextY = curY + dirs[d][1];
                if (inArea(nextX,nextY) && !visited[nextX][nextY] && grid[nextX][nextY] == 0){
                    queue.add(nextX*raw + nextY);
                    visited[nextX][nextY] = true;
                    dis[nextX][nextY] = dis[curX][curY] + 1;

                    if (nextX == raw-1 && nextY == raw-1)
                        return dis[nextX][nextY];
                }
            }
        }

        return -1;
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<raw && y>=0 && y<raw;
    }
}
