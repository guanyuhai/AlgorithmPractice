package LeetCode.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原IP地址
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/7 14:55
 */
public class LeetCode93 {
    List<String> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        // 简单的剪枝优化
        if (s.length()>12 || s.length()<4) return result;

        backtracking(s, 0,0);
        return result;
    }

    // startIndex: 搜索的起始位置，pointNum:添加逗点的数量
    private void backtracking(String s, int startIndex, int pointNum) {
        // 终止条件: 逗点数量为3时，分隔结束
        int length = s.length();
        if (pointNum == 3) {
            // 判断第四段子字符串是否合法，如果合法就放进result中
            if (isValid(s, startIndex, length - 1)) {
                result.add(s);
                return;
            }
        }

        for (int i = startIndex; i < length; i++) {
            if (isValid(s, startIndex, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);    //在str的后⾯插⼊⼀个逗点
                pointNum++;
                // 插⼊逗点之后下⼀个⼦串的起始位置为i+2
                backtracking(s, i + 2, pointNum);
                pointNum--;// 回溯
                s = s.substring(0, i + 1) + s.substring(i + 2);// 回溯删掉逗点
            } else {
                break;
            }
        }
    }

    // 判断字符串s在左闭⼜闭区间[start, end]所组成的数字是否合法
    private Boolean isValid(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '0' && start != end) { // 0开头的数字不合法
            return false;
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') { // 遇到⾮数字字符不合法
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) { // 如果⼤于255了不合法
                return false;
            }
        }
        return true;
    }
}
