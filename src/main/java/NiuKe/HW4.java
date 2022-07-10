package NiuKe;

import java.util.Scanner;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/9 22:28
 */
public class HW4 {
    public static void main2(String[] args) {
        Scanner in = new Scanner(System.in);
        //String text = in.nextLine();
        String text = "12345678";

        // 被8整除的次数
        int count = text.length()/8;
        for (int i = 0; i < count; i++) {
            System.out.println(text.substring(i*8,(i+1)*8));
        }
        // 最后一个不满长度8的字符串特殊处理
        if (text.length() % 8 > 0) {
            String last_Str = text.substring(count*8);
            int last = 8 - last_Str.length();
            for (int i = 0; i < last; i++) {
                last_Str += "0";
            }
            System.out.println(last_Str);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //String text = in.nextLine();
        String text = "12345678";

        // 被8整除的次数
        int count = text.length()/8;
        for (int i = 0; i < count; i++) {
            System.out.println(text.substring(i*8,(i+1)*8));
        }
        // 最后一个不满长度8的字符串特殊处理
        if (text.length() % 8 > 0) {
            String last_Str = text.substring(count*8);
            int last = 8 - last_Str.length();
            for (int i = 0; i < last; i++) {
                last_Str += "0";
            }
            System.out.println(last_Str);
        }
    }
}
