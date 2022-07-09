package LeetCode.DynamicProgramming;

/**
 * 组合总和IV
 * 如果是要求出所有的排列方式，只能用回溯
 * 但本题要求的是排列方式的个数，因此可以用DP
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/9 17:14
 */
public class LeetCode377 {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        // 这里的初始化其实没有意义，至少为了推导公式
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}
