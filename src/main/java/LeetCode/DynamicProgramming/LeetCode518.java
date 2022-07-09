package LeetCode.DynamicProgramming;

/**
 * 零钱兑换
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/9 16:33
 */
public class LeetCode518 {
    public int change(int amount, int[] coins) {
        //递推表达式
        int[] dp = new int[amount + 1];
        //初始化dp数组，表示金额为0时只有一种情况，也就是什么都不装
        dp[0] = 1;

        // 由于是寻找不同的组合，因此先遍历背包容量，再遍历物品
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
