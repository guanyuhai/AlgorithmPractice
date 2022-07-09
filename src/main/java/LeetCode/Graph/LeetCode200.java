package LeetCode.Graph;

/**
 * 岛屿的数量
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/14 15:18
 */
public class LeetCode200 {
    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},
                {'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(numIslands(grid));
    }

    // 正确解答
    public int numIslands2(char[][] grid) {
        int islandNum = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    infect(grid, i, j);
                    islandNum++;
                }
            }
        }
        return islandNum;
    }
    //感染函数
    public void infect(char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length ||
                j < 0 || j >= grid[0].length || grid[i][j] != '1'){
            return;
        }
        grid[i][j] = '2';
        infect(grid, i + 1, j);
        infect(grid, i - 1, j);
        infect(grid, i, j + 1);
        infect(grid, i, j - 1);
    }

    /**
     * 以下解答未考虑斜对角的情况
     */
    private static int[][] dirs = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    private static int R, C;   //raw, column
    //private char[][] grid;
    private static int[][] visited;
    private static Integer ccCount = 0;

    public static int numIslands(char[][] grid) {
        if (grid == null)
            return 0;
        R = grid.length;
        C = grid[0].length;
        if (R==0 || C==0)
            return 0;
        //this.grid = grid;

        visited = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                visited[i][j] = -1;
            }
        }
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 为-1，表示未被访问过
                if (visited[i][j]==-1 && grid[i][j] == '1') {
                    dfs(i, j, ccCount, grid);
                    ccCount++;
                }
            }
        }

        return ccCount;
    }

    // 遍历每个坐标对应的陆地
    private static void dfs(int x, int y, int ccid, char[][] grid) {
        visited[x][y] = ccid;

        for (int d = 0; d < 4; d++) {
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];

            if (inArea(nextX,nextY) && visited[nextX][nextY]==-1 && grid[nextX][nextY] == '1')
                dfs(nextX,nextY,ccid,grid);
        }
    }

    private static boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }
}
