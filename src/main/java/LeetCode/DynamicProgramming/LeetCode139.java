package LeetCode.DynamicProgramming;

import java.util.List;

/**
 * 单词拆分
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/9 22:03
 */
public class LeetCode139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] valid = new boolean[s.length() + 1];
        // dp[0]表示如果字符串为空的话，说明出现在字典里。其实并没有意义
        valid[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // 如果确定dp[j] 是true，且 [j, i] 这个区间的子串出现在字典里，那么dp[i]一定是true。（j < i ）
                // 所以递推公式是 if([j, i] 这个区间的子串出现在字典里 && dp[j]是true) 那么 dp[i] = true。
                if (wordDict.contains(s.substring(j,i)) && valid[j]) {
                    valid[i] = true;
                }
            }
        }

        return valid[s.length()];
    }
}
