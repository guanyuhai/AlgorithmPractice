package LeetCode.DynamicProgramming;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/9 11:19
 */
public class LeetCode96 {
    public int numTrees(int n) {
        //初始化 dp 数组
        // dp[i]：1到i为节点组成的二叉搜索树的个数为dp[i]。
        int[] dp = new int[n + 1];
        //初始化0个节点和1个节点的情况
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                //对于第i个节点，需要考虑1作为根节点直到i作为根节点的情况，所以需要累加
                // dp[i] += dp[以j为头结点左子树节点数量] * dp[以j为头结点右子树节点数量]
                //一共i个节点，对于根节点为j时,左子树的节点个数为j-1，右子树的节点个数为i-j
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
