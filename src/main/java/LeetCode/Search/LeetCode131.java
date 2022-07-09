package LeetCode.Search;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 分割回文字符串
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/7 14:27
 */
public class LeetCode131 {
    List<List<String>> result = new ArrayList<>();
    Deque<String> path = new LinkedList<>();

    public List<List<String>> partition(String s) {
        parttitionHelper(s, 0);
        return result;
    }

    private void parttitionHelper(String s, int startIndex) {
        // 如果起始位置已经大于s的大小，说明已经找到了一组分割方案了
        int length = s.length();
        if (startIndex >= length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < length; i++) {
            // 如果是回文字串，则记录
            if (isPalindrome(s, startIndex, i)) {
                String str = s.substring(startIndex, i+1);
                path.addLast(str);
            } else continue;
            // 起始位置后移，保证不重复
            parttitionHelper(s, i+1);
            // 回溯
            path.removeLast();
        }
    }

    // 复习
    private void part(String s, int startIndex) {
        int length = s.length();
        if (startIndex >= length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < length; i++) {
            if (isPalindrome(s, startIndex, i)) {
                String str = s.substring(startIndex, i+1);
                path.addLast(str);
            } else continue;

            part(s, i+1);
            path.removeLast();
        }
    }

    // 判断是否为回文字符串：使用双指针的方式
    private boolean isPalindrome(String s, int startIndex, int end) {
        for (int i = startIndex, j = end; i < j; i++,j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}
