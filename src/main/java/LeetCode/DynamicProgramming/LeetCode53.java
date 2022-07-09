package LeetCode.DynamicProgramming;

/**
 * 最大子序和
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/11 16:09
 */
public class LeetCode53 {
    /**
     * 1.dp[i]代表当前下标对应的最大值
     * 2.递推公式 dp[i] = max (dp[i-1]+nums[i],nums[i]) res = max(res,dp[i])
     * 3.初始化 都为 0
     * 4.遍历方向，从前往后
     * 5.举例推导结果。。。
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int res = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            res = res > dp[i] ? res : dp[i];
        }
        return res;
    }
}
