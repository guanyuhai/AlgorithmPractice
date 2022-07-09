package LeetCode.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/7 21:53
 */
public class LeetCode22 {
    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs(n, n, "");
        return result;
    }

    private void dfs(int left, int right, String curStr) {
        if (left == 0 && right == 0) { // 左右括号都不剩余了，递归终止
            result.add(curStr);
            return;
        }

        if (left > 0) { // 如果左括号还剩余的话，可以拼接左括号
            dfs(left - 1, right, curStr + "(");
        }
        if (right > left) { // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
            dfs(left, right - 1, curStr + ")");
        }
    }
}
