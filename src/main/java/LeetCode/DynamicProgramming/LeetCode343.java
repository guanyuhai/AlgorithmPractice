package LeetCode.DynamicProgramming;

/**
 * 整数拆分
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/9 10:44
 */
public class LeetCode343 {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(integerBreak(n));
    }

    public static int integerBreak(int n) {
        //dp[i]为正整数i拆分结果的最大乘积
        int[] dp = new int[n+1];
        // 初始化DP从2开始，严格从dp[i]的定义来说，dp[0] dp[1] 就不应该初始化，也就是没有意义的数值。
        // 拆分0和拆分1的最大乘积是多少？这是无解的。
        // 初始化dp[2] = 1，从dp[i]的定义来说，拆分数字2，得到的最大乘积是1
        dp[2] = 1;
        for (int i = 3; i <= n; ++i) {
            for (int j = 1; j < i - 1; ++j) {
                //j*(i-j)代表把i拆分为j和i-j两个数相乘
                //j*dp[i-j]代表把i拆分成j和继续把(i-j)这个数拆分，取(i-j)拆分结果中的最大乘积与j相乘
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}
