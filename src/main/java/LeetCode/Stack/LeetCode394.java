package LeetCode.Stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 字符串解码
 * @author JavaClimber
 * @version 1.0
 * @date 2022/1/29 18:50
 */
public class LeetCode394 {
    /**
     * 双栈解法：
     * 准备两个栈，一个存放数字，一个存放字符串
     * 遍历字符串分4中情况
     * 一、如果是数字 将字符转成整型数字 注意数字不一定是个位 有可能是十位，百位等 所以digit = digit*10 + ch - '0';
     * 二、如果是字符 直接将字符放在临时字符串中
     * 三、如果是"[" 将临时数字和临时字符串入栈
     * 四、如果是"]" 将数字和字符串出栈 此时临时字符串res = 出栈字符串 + 出栈数字*res
     */
    public String decodeString2(String s) {
        //创建数字栈，创建字符串栈 及临时数字和临时字符串
        Deque<Integer> stack_digit = new LinkedList<>();
        Deque<StringBuilder> stack_string = new LinkedList<>();
        int digit = 0;
        StringBuilder res = new StringBuilder();
        //遍历字符串 分4中情况
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '[') {
                //如果是"[" 将临时数字和临时字符串入栈
                stack_digit.push(digit);
                stack_string.push(res);
                digit = 0;
                res = new StringBuilder();
            }else if (ch == ']') {
                //如果是"]" 将数字和字符串出栈 此时临时字符串res = 出栈字符串 + 出栈数字*res
                StringBuilder temp = stack_string.poll();
                int count = stack_digit.poll();
                for (int j = 0; j < count; j++) {
                    temp.append(res.toString());
                }
                res = temp;
            }else if (Character.isDigit(ch)) {
                //如果是数字 将字符转成整型数字 ch-‘0’。 注意数字不一定是个位 比如100[a] 所以digit要*10
                digit = digit*10 + ch - '0';
            }else {
                //如果是字符 直接将字符放在临时字符串中
                res.append(ch);
            }
        }
        return res.toString();
    }



    // 错误
    public static String decodeString(String s) {
        // 解码的开始位置
        Stack<Integer> starts = new Stack<>();
        Stack<Integer> ends = new Stack<>();
        Stack<Integer> ends2 = new Stack<>();

        // 获取待解码内容的起始和结束位置
        int j = s.length()-1;
        for (int i = 0; i < s.length(); i++,j--) {
            char start = s.charAt(i);
            char end = s.charAt(j);
            if (start >= '0' && start <= '9')
                starts.push(i);
            if (start == ']')
                ends2.push(i);
            if (end == ']') {
                ends.push(j);
            }
        }

        // 解码后的内容较原先内容的长度变化
        int changeLenght = 0;
        int last_end = 0;
        StringBuilder last_decoder = new StringBuilder();
        while (!starts.isEmpty()) {
            int start = starts.pop();
            int end = ends.pop();
            // 获取解码内容
            if (end > last_end) end = end+changeLenght;
            if (end<start) {
                ends = ends2;
                end = ends.pop();
            }
            String encoder = s.substring(start-1, end);

            int firstIndex = 0;
            for (int i = 0; i < encoder.length(); i++) {
                if (encoder.charAt(i) == '[') {
                    firstIndex = i;
                    break;
                }
            }
            int loop = Integer.valueOf(encoder.substring(0,firstIndex));
            String loopStr = encoder.substring(2,encoder.length());
            for (int i = 0; i < loop; i++) {
                last_decoder.append(loopStr);
            }

            // 替换源字符串内容
            String replace = s.substring(start-1,end+1);
            s = s.replace(replace,last_decoder.toString());
            changeLenght = last_decoder.length()-replace.length();
            last_decoder = new StringBuilder();

            last_end = end;
        }


        return s;
    }

    public static void main(String[] args) {
        String s = "10[a]";
        System.out.println(decodeString(s));
    }
}
