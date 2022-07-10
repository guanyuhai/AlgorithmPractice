package NiuKe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.BitSet;

/**
 * 字符个数统计
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/10 11:27
 */
public class HW10 {
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = "uqic^g`(s&jnl(m#vt!onwdj(ru+os&wx";//sc.nextLine();

        int length = str.length();
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            set.add(c);
        }
        System.out.println(set.size());
    }

    /** 位图计算 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.next();
        //总共有128个字符。字需要用128位
        BitSet bitSet = new BitSet(128);
        for (char c : line.toCharArray()) {
            //判断字符c是否已出现
            if (!bitSet.get(c)) {
                //未出现就设置为已出现
                bitSet.set(c);
            }
        }
        //统计有多少字符已出现过
        System.out.println(bitSet.cardinality());
    }
}
