package NiuKe;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个由字母、数字和空格组成的字符串，和一个字符，然后输出输入字符串中该字符的出现次数。（不区分大小写字母）
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/9 20:30
 */
public class HW2 {
    public static void main(String[] args) {
        /*Scanner in = new Scanner(System.in);
        String str = "8 8 8  8A i i OOI              IIIaa";//in.nextLine();
        char c = 'a';//in.next().toLowerCase().toCharArray()[0];
        char c2 = String.valueOf(c).toUpperCase().toCharArray()[0];

        int count=0;
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i)==c || str.charAt(i)==c2) {
                count++;
            }
        }

        System.out.println(count);*/


        // 其他解法:
        // 1 就是将字符串都转大写
        // 2 然后去掉字符串1 中的 字符串2（字母）
        // 3 做减法，得到最终结果
        Scanner s = new Scanner(System.in);
        String input1= "8 8 8  8A i i OOI              IIIaa";//s.nextLine();
        String input2 = "A";//s.nextLine();
        String split3 = input1.toUpperCase().replaceAll(input2.toUpperCase(),"");
        System.out.println(input1.length() - split3.length());
    }
}
