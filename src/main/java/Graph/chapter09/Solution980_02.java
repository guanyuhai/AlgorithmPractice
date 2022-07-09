package Graph.chapter09;

/**
 * 使用状态压缩进行优化
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/15 11:13
 */
public class Solution980_02 {
    private int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    private int R, C;
    private int[][] grid;
    private int start, end;

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        int left = R * C;

        for(int i = 0; i < R; i ++) {
            for (int j = 0; j < C; j++) {
                // 1 表示起点
                if (grid[i][j] == 1) {
                    start = i * C + j; // 将二维坐标转为一维
                    grid[i][j] = 0;
                } else if (grid[i][j] == 2) { // 2 表示终点
                    end = i * C + j;
                    grid[i][j] = 0;
                } else if (grid[i][j] == -1) // -1 表示障碍
                    left--;
            }
        }
        int visited = 0;
        return dfs(visited, start, left);
    }

    private int dfs(int visited, int v, int left){
        visited += (1<<v);
        left --;
        if(v == end && left == 0){
            visited -= (1<<v);
            return 1;
        }

        int x = v / C, y = v % C;
        int res = 0;
        for(int d = 0; d < 4; d ++){
            int nextx = x + dirs[d][0], nexty = y + dirs[d][1];
            int next = nextx*C + nexty;
            if(inArea(nextx, nexty) && grid[nextx][nexty] == 0 && (visited & (1<<next)) == 0)
                res += dfs(visited, nextx * C + nexty, left);
        }

        visited -= (1<<v);
        return res;
    }

    private boolean inArea(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}
