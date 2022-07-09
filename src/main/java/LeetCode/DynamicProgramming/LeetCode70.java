package LeetCode.DynamicProgramming;

/**
 * 爬楼梯
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/8 16:51
 */
public class LeetCode70 {
    public int climbStairs(int n) {
        // 跟斐波那契数列一样
        if(n <= 2) return n;
        int a = 1, b = 2, sum = 0;

        for(int i = 3; i <= n; i++){
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    // 常规方式
    public int climbStairs2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 复习
    public int cli(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    /**
     * 改为：一步一个台阶，两个台阶，三个台阶，.......，直到 m个台阶。问有多少种不同的方法可以爬到楼顶呢？
     * 1阶，2阶，.... m阶就是物品，楼顶就是背包。
     * 每一阶可以重复使用，例如跳了1阶，还可以继续跳1阶。
     * 问跳到楼顶有几种方法其实就是问装满背包有几种方法。
     * 这样这个问题就从01背包问题变成完全背包问题
     */
    public int climbStairs3(int n) {
        int[] dp = new int[n + 1];
        int[] weight = {1,2};
        dp[0] = 1;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < weight.length; j++) {
                if (i >= weight[j]) dp[i] += dp[i - weight[j]];
            }
        }

        return dp[n];
    }
}
