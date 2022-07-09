package LeetCode.Stack;

import java.util.Stack;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/29 15:27
 */
public class LeetCode20 {
    public static boolean isValid(String s) {
        if (s.length()%2!=0) return false;

        Stack<Character> stack = new Stack<>();
        for(char c: s.toCharArray()){
            if(c=='(')stack.push(')');
            else if(c=='[')stack.push(']');
            else if(c=='{')stack.push('}');
            else if(stack.isEmpty()||c!=stack.pop())return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "{[]}";
        System.out.println(isValid(s));
    }
}
