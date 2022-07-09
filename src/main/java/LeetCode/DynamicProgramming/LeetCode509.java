package LeetCode.DynamicProgramming;

/**
 * 斐波那契数
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/8 16:38
 */
public class LeetCode509 {
    public int fib(int n) {
        if (n <= 1) return n;
        // dp数组的初始化
        int a = 0, b = 1, c = 0;
        for (int i = 1; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
    //非压缩状态的版本
    class Solution {
        public int fib(int n) {
            if (n <= 1) return n;
            int[] dp = new int[n + 1];
            dp[0] = 0;
            dp[1] = 1;
            for (int index = 2; index <= n; index++){
                dp[index] = dp[index - 1] + dp[index - 2];
            }
            return dp[n];
        }

        // 复习
        public int fib2(int n) {
            if (n<=1) return n;
            int[] dp = new int[n+1];
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i <= n ; i++) {
                dp[i] = dp[i-1] + dp[i-2];
            }
            return dp[n];
        }
    }
}
