package LeetCode.Graph;

/**
 * 岛屿的最大面积
 * leetcode 695 https://leetcode-cn.com/problems/max-area-of-island/
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/14 15:47
 */
public class LeetCode695 {
    private int[][] dirs = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    private int R, C;   //raw, column
    private int[][] grid;
    private boolean[][] visited;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null)
            return 0;
        R = grid.length;
        C = grid[0].length;
        if (R==0 || C==0)
            return 0;
        this.grid = grid;

        int res = 0;
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j] && grid[i][j] == 1)
                    res = Math.max(res, dfs(i, j));
            }
        }

        return res;
    }

    // 返回顶点(x,y) 所在的连通分量包含的顶点数
    private int dfs(int x, int y) {
        int res = 1;
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];

            if (inArea(nextX,nextY) && !visited[nextX][nextY] && grid[nextX][nextY] == 1)
                res += dfs(nextX,nextY);
        }

        return res;
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }
}
