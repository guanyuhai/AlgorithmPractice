package LeetCode.DynamicProgramming;

/**
 * 完全平方数
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/9 17:37
 */
public class LeetCode279 {
    public static void main(String[] args) {
        int n = 12;
        System.out.println(numSquares(12));
    }

    // 版本一，先遍历物品, 再遍历背包
    public static int numSquares(int n) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        //初始化
        for (int j = 0; j <= n; j++) {
            dp[j] = max;
        }
        //当和为0时，组合的个数为0
        dp[0] = 0;
        // 遍历物品
        for (int i = 1; i * i <= n; i++) {
            // 遍历背包
            for (int j = i * i; j <= n; j++) {
                if (dp[j - i * i] != max) {
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
                }
            }
        }
        return dp[n];
    }
}
