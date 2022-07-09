package LeetCode.DynamicProgramming;

/**
 * 最长公共子序列
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/11 15:44
 */
public class LeetCode1143 {

    public int longestCommonSubsequence(String text1, String text2) {
        // 先对dp数组做初始化操作, 默认为0
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        for (int i = 1; i <= text1.length(); i++) {
            char char1 = text1.charAt(i - 1);
            for (int j = 1; j <= text2.length(); j++) {
                char char2 = text2.charAt(j - 1);
                if (char1 == char2) { // 开始列出状态转移方程
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    // 复习
    public int longest(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        for (int i = 1; i <= text1.length() ; i++) {
            char char1 = text1.charAt(i-1);
            for (int j = 1; j <= text2.length(); j++) {
                char char2 = text2.charAt(j - 1);
                if (char1 == char2)
                    dp[i][j] = dp[i-1][j-1] + 1;
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }

        return dp[text1.length()][text2.length()];
    }
}
