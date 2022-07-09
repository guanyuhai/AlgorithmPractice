package LeetCode.DynamicProgramming;

/**
 * 打家劫舍
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/10 10:37
 */
public class LeetCode198 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        // 初始化：dp[0] 一定是 nums[0]，dp[1]就是nums[0]和nums[1]的最大值
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // 如果偷第i房间，那么dp[i] = dp[i - 2] + nums[i]
            // 如果不偷第i房间，那么dp[i] = dp[i - 1]，即考虑i-1房
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }
}
