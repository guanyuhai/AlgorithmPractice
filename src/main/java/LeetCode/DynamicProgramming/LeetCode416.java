package LeetCode.DynamicProgramming;

/**
 * 分割等和子集
 * 可以使用回溯算法，但会超时
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/9 15:11
 */
public class LeetCode416 {
    public boolean canPartition(int[] nums) {
        if(nums == null || nums.length == 0) return false;
        int n = nums.length;
        // 获取总和sum， 使用SUM/2作为背包可承受的最大重量
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
        //总和为奇数，不能平分
        if(sum % 2 != 0) return false;

        int target = sum / 2;
        // 初始化一维DP数组
        int[] dp = new int[target + 1];
        for(int i = 0; i < n; i++){
            for(int j = target; j >= nums[i]; j--){
                //物品 i 的重量是 nums[i]，其价值也是 nums[i]
                dp[j] = Math.max(dp[j], dp[j-nums[i]] + nums[i]);
            }
        }
        return dp[target] == target;
    }
}
