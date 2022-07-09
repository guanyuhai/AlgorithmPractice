package LeetCode.DynamicProgramming;

/**
 * 回文子串
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/11 20:23
 */
public class LeetCode647 {
    // 动态规划方案
    public int countSubstrings(String s) {
        int len, ans = 0;
        if (s == null || (len = s.length()) < 1) return 0;

        //dp[i][j]：s字符串下标i到下标j的字串是否是一个回文串，即s[i, j]
        boolean[][] dp = new boolean[len][len];
        for (int right = 0; right < len; right++) {
            for (int left = 0; left <= right; left++) {
                //当两端字母一样时，才可以两端收缩进一步判断
                if (s.charAt(left) == s.charAt(right)) {
                    // 情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
                    // 情况二：下标i 与 j相差为1，例如aa，也是文子串
                    if (right - left < 3) {
                        dp[left][right] = true;
                    } else {
                        // 进一步收缩，例如cabac，此时s[left]与s[right]已经相同了，
                        // 我们看left 到right 区间是不是回文子串就看aba是不是回文，
                        // 那么aba的区间就是 left+1 与 right-1区间，这个区间是不是回文就看dp[left + 1][right - 1]是否为true。
                        dp[left][right] = dp[left + 1][right - 1];
                    }
                } else {//两端字符不一样，不是回文串
                    dp[left][right] = false;
                }
            }
        }
        // 二选一
        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                //当两端字母一样时，才可以两端收缩进一步判断
                if (s.charAt(i) == s.charAt(j)) {
                    //i++，j--，即两端收缩之后i,j指针指向同一个字符或者i超过j了,必然是一个回文串
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        //否则通过收缩之后的字串判断
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {//两端字符不一样，不是回文串
                    dp[i][j] = false;
                }
            }
        }

        //遍历每一个字串，统计回文串个数
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (dp[i][j]) ans++;
            }
        }
        return ans;
    }
    // 复习
    public int count1(String s) {
        int len, ans = 0;
        if (s == null || (len = s.length()) < 1) return 0;

        boolean[][] dp = new boolean[len][len];
        for (int right = 0; right < len; right++) {
            for (int left = 0; left <= right; left++) {
                //当两端字母一样时，才可以两端收缩进一步判断
                if (s.charAt(left) == s.charAt(right)) {
                    // 情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
                    // 情况二：下标i 与 j相差为1，例如aa，也是文子串
                    if (right - left < 3) {
                        dp[left][right] = true;
                    } else {
                        // 进一步收缩，例如cabac，此时s[left]与s[right]已经相同了，
                        // 我们看left 到right 区间是不是回文子串就看aba是不是回文，
                        // 那么aba的区间就是 left+1 与 right-1区间，这个区间是不是回文就看dp[left + 1][right - 1]是否为true。
                        dp[left][right] = dp[left + 1][right - 1];
                    }
                } else {//两端字符不一样，不是回文串
                    dp[left][right] = false;
                }
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (dp[i][j]) ans++;
            }
        }
        return ans;
    }

    // 中心扩散法
    class Solution {
        public int countSubstrings(String s) {
            int len, ans = 0;
            if (s == null || (len = s.length()) < 1) return 0;
            //总共有2 * len - 1个中心点
            for (int i = 0; i < 2 * len - 1; i++) {
                //通过遍历每个回文中心，向两边扩散，并判断是否回文字串
                //有两种情况，left == right，right = left + 1，这两种回文中心是不一样的
                int left = i / 2, right = left + i % 2;
                while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                    //如果当前是一个回文串，则记录数量
                    ans++;
                    left--;
                    right++;
                }
            }
            return ans;
        }

        // 复习
        public int count2(String s) {
            int len, ans = 0;
            if (s == null || (len = s.length()) < 1) return 0;

            for (int i = 0; i < 2*len-1; i++) {
                int left = i/2, right = left + i%2;
                while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                    ans++;
                    left--;
                    right++;
                }
            }

            return ans;
        }
    }
}
