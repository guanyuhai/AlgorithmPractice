package LeetCode.DynamicProgramming;

/**
 * 最后一块石头的重量II
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/9 15:29
 */
public class LeetCode1049 {
    // 尽量让石头分成重量相同的两堆，相撞之后剩下的石头最小，这样就化解成01背包问题了。
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i : stones) {
            sum += i;
        }
        int target = sum >> 1; // sum/2

        //初始化dp数组
        int[] dp = new int[target + 1];
        for (int i = 0; i < stones.length; i++) {
            //采用倒序
            for (int j = target; j >= stones[i]; j--) {
                //两种情况，要么放，要么不放
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[target];
    }
}
