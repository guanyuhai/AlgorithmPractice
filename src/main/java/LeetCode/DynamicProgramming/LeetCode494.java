package LeetCode.DynamicProgramming;

/**
 * 目标和
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/9 15:54
 */
public class LeetCode494 {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        // 不能整除，表明无法装满
        if ((target + sum) % 2 != 0) return 0;
        int size = (target + sum) / 2;
        if(size < 0) size = -size;

        int[] dp = new int[size + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }
        return dp[size];
    }

    // 复习
    public int find(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((target+sum) % 2 != 0) return 0;
        int size = (target+sum) / 2;
        if (size<0) size = -size;

        int[] dp = new int[size + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = size; j >= nums[i] ; j--) {
                dp[j] += dp[j-nums[i]];
            }
        }

        return dp[size];
    }
}
