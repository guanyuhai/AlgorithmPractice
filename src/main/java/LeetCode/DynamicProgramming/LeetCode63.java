package LeetCode.DynamicProgramming;

/**
 * 不同路径II：存在障碍物的情况
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/9 10:26
 */
public class LeetCode63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            //一旦遇到障碍，后续都到不了
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            //一旦遇到障碍，后续都到不了
            if (obstacleGrid[0][i] == 1) break;
            dp[0][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 1) continue;
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[n-1][m-1];
    }

    // 复习
    public int unique(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            if (obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[0][i] == 1) break;
            dp[0][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 1) break;
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[n-1][m-1];
    }
}
