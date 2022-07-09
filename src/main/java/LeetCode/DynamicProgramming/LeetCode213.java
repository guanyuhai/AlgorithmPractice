package LeetCode.DynamicProgramming;

/**
 * 打家劫舍II
 * 所有房屋围成一圈，即数组的开头和结尾属于相邻的房屋
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/10 10:49
 */
public class LeetCode213 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int len = nums.length;
        if (len == 1) return nums[0];
        // 两种情况： 1 不包含尾元素， 2 不包含首部元素
        return Math.max(robAction(nums, 0, len - 1), robAction(nums, 1, len));
    }

    int robAction(int[] nums, int start, int end) {
        int x = 0, y = 0, z = 0;
        for (int i = start; i < end; i++) {
            y = z;
            z = Math.max(y, x + nums[i]);
            x = y;
        }
        return z;
    }
}
