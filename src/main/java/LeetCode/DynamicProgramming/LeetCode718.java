package LeetCode.DynamicProgramming;

/**
 * 最长重复子数组
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/11 15:33
 */
public class LeetCode718 {
    public int findLength(int[] nums1, int[] nums2) {
        // 当前最大连续递增子数组大小
        int result = 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];

        for (int i = 1; i < nums1.length + 1; i++) {
            for (int j = 1; j < nums2.length + 1; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    result = Math.max(result, dp[i][j]);
                }
            }
        }

        return result;
    }

    // 版本二: 滚动数组
    class Solution {
        public int findLength(int[] nums1, int[] nums2) {
            int[] dp = new int[nums2.length + 1];
            int result = 0;

            for (int i = 1; i <= nums1.length; i++) {
                for (int j = nums2.length; j > 0; j--) {
                    if (nums1[i - 1] == nums2[j - 1]) {
                        dp[j] = dp[j - 1] + 1;
                    } else {
                        dp[j] = 0;
                    }
                    result = Math.max(result, dp[j]);
                }
            }
            return result;
        }
    }
}
