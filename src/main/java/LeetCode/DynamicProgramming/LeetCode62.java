package LeetCode.DynamicProgramming;

/**
 * 不同路径
 *
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/8 17:38
 */
public class LeetCode62 {
    /**
     * 1. 确定dp数组下标含义 dp[i][j] 到每一个坐标可能的路径种类
     * 2. 递推公式 dp[i][j] = dp[i-1][j] dp[i][j-1]
     * 3. 初始化 dp[i][0]=1 dp[0][i]=1 初始化横竖就可
     * 4. 遍历顺序 一行一行遍历
     * 5. 推导结果 。。。。。。。。
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //初始化
        // 表示一直向下走，走的不同路径数为1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // 表示一直向右走，走的不同路径数为1
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    // 复习
    public int unique(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    // 改成一维数组的方式（可以理解为滚动数组）, 可以将空间复杂度从O(M*N) 降低到O(N)
    public static int uniquePaths2(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j-1];
            }
        }

        return dp[n-1];
    }
}
