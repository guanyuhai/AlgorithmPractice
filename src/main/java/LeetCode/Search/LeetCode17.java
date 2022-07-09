package LeetCode.Search;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码与字母组合
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/6 16:08
 */
public class LeetCode17 {
    //设置全局列表存储最后的结果
    List<String> list = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return list;
        }
        //初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
        String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //迭代处理
        backTracking(digits, numString, 0);
        return list;
    }

    //每次迭代获取一个字符串，所以会设计大量的字符串拼接，所以这里选择更为高效的 StringBuild
    StringBuilder temp = new StringBuilder();

    //比如digits如果为"23",num 为0，则str表示2对应的 abc
    private void backTracking(String digits, String[] numString, int num) {
        //遍历全部一次记录一次得到的字符串
        int len = digits.length();
        if (num == len) {
            list.add(temp.toString());
            return;
        }

        //str 表示当前num对应的字符串
        String str = numString[digits.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            backTracking(digits,numString, num+1);
            // 删除末尾的字符，继续尝试
            temp.deleteCharAt(temp.length()-1);
        }
    }

    // 复习
    private void helper(String digits, String[] numString, int num) {
        int length = digits.length();
        if (num == length) {
            list.add(temp.toString());
            return;
        }

        String str = numString[digits.charAt(num) - '0'];
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            helper(digits, numString, num+1);
            temp.deleteCharAt(temp.length()-1);
        }
    }
}
