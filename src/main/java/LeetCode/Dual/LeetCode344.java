package LeetCode.Dual;

/**
 * 反转字符串
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/2 10:15
 */
public class LeetCode344 {
    public static void main(String[] args) {
        char[] s = new char[]{'h','e','l','l','o'};
        reverseString(s);
    }

    public static void reverseString(char[] s) {
        char tmp;
        int len = s.length;
        for (int i = 0,j = len-1; i < len/2; i++,j--) {
            if (i != j) {
                tmp = s[i];
                s[i] = s[j];
                s[j] = tmp;
            }
        }
        for (int i = 0; i < len; i++) {
            System.out.println(s[i]);
        }
    }
}
