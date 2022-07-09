package LeetCode.DynamicProgramming;

/**
 * 最少花费爬楼梯
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/8 17:13
 */
public class LeetCode746 {
    public static void main(String[] args) {
        int[] cost = new int[]{1,100,1,1,1,100,1,1,100,1};
        System.out.println(minCostClimbingStairs(cost));
    }

    // 本题假设最后一步不花费体力
    public static int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length==0) return 0;
        if (cost.length==1) return cost[0];

        // 初始化DP数组
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            // DP推导式，取花费最小的 + 当前花费
            // 因为可以跨一步或两步，因此可以从前一个台阶，或前两个台阶上来
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }
        //最后一步，如果是由倒数第二步爬，则最后一步的体力花费可以不用算
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }

    // 复习
    public int minCost(int[] cost) {
        if (cost==null || cost.length==0) return 0;
        if (cost.length==1) return cost[0];

        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }
        return Math.min(dp[cost.length-1], dp[cost.length-2]);
    }
}
