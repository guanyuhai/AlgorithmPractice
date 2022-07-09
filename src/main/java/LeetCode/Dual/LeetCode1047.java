package LeetCode.Dual;

/**
 * 删除字符串中所有相邻的重复项
 * @author JavaClimber
 * @version 1.0
 * @date 2022/2/5 9:58
 */
public class LeetCode1047 {
    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicates2(s));
    }

    public static String removeDuplicates2(String S) {
        int index = -1;
        char[] chars = S.toCharArray();
        int len = chars.length;
        for (int i = 0; i < len; i++) {
            if (index >= 0 && chars[index] == chars[i]) {
                index--;
            } else {
                index++;
                chars[index] = chars[i];
            }
        }
        return String.copyValueOf(chars, 0, index + 1);
    }

    // 超出时间限制
    public static String removeDuplicates(String s) {
        int len = s.length();
        for (int i = 0,j=i+1; i < len-1; i++,j++) {
            if (s.charAt(i)==s.charAt(j)) {
                StringBuilder replace = new StringBuilder();
                replace.append(s.charAt(i));
                replace.append(s.charAt(j));
                s = s.replaceFirst(replace.toString(),"");
                i=-1;
                j=0;
                len-=2;
            }
        }

        return s;
    }
}
