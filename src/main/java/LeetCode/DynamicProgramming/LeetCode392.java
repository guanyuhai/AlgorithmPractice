package LeetCode.DynamicProgramming;

/**
 * 判断子序列
 * 实际可以用双指针实现，但这里使用DP
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/11 16:15
 */
public class LeetCode392 {
    public boolean isSubsequence(String s, String t) {
        int length1 = s.length(); int length2 = t.length();
        int[][] dp = new int[length1+1][length2+1];

        for(int i = 1; i <= length1; i++){
            for(int j = 1; j <= length2; j++){
                if(s.charAt(i-1) == t.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = dp[i][j-1];
                }
            }
        }

        return dp[length1][length2] == length1;
    }

    // 根据index位置，来确定是否为子序列
    public static boolean isSubsequence2(String s, String t) {
        int index = -1;
        for (char c : s.toCharArray()){
            index = t.indexOf(c, index+1);
            if (index == -1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "acb";
        String t = "ahbgdc";
        System.out.println(isSubsequence2(s,t));
    }
}
