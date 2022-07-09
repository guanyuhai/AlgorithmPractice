package NiuKe;
import java.util.Scanner;

/**
 * 计算字符串最后一个单词的长度，单词以空格隔开，字符串长度小于5000。（注：字符串末尾不以空格为结尾）
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/9 16:41
 */
public class HW1 {

    // 注意类名必须为 Main, 不要有任何 package xxx 信息
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //String text = in.nextLine();
        String text = "hello nowcoder";

        // 反过来打印
        int length = text.length();
        int count = 0;
        int start = length-1;
        for (int i = start; i >= 0; i--) {
            if (text.charAt(i)==' ') break;
            count++;
        }
        System.out.println(count);
    }
}
