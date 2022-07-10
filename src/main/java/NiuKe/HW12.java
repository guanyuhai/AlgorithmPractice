package NiuKe;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 字符串反转
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/10 15:23
 */
public class HW12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int length = str.length();
        // 接收反转后的字符串
        StringBuilder sb = new StringBuilder();
        for (int i = length-1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        System.out.println(sb.toString());


        /** 取巧的写法 */
        StringBuffer strb = new StringBuffer(str);
        strb.reverse();
        System.out.println(strb.toString());
    }
}
