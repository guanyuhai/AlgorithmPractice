package LeetCode.DynamicProgramming;

/**
 * 不相交的线
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/11 16:01
 */
public class LeetCode1035 {
    // 本题说是求绘制的最大连线数，其实就是求两个字符串的最长公共子序列的长度！
    // 实际上就和1143题一样
    public int maxUncrossedLines(int[] A, int[] B) {
        int [][] dp = new int[A.length+1][B.length+1];
        for(int i=1;i<=A.length;i++) {
            for(int j=1;j<=B.length;j++) {
                if (A[i-1]==B[j-1]) {
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else {
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[A.length][B.length];
    }
}
