package NiuKe;


import java.util.Scanner;

/**
 * 坐标移动
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/10 18:59
 */
public class HW17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        int left = 0;
        int right = 0;
        String[] regix = str.split(";");
        for (String next : regix) {
            if (next.equals("")) continue;
            String directing = next.substring(0,1);
            String num = next.substring(1);
            // 合规性判断
            if (isNumeric(num)) {
                if ("A".equals(directing)) left-=Integer.parseInt(num);
                else if ("D".equals(directing)) left+=Integer.parseInt(num);
                else if ("S".equals(directing)) right-=Integer.parseInt(num);
                else if ("W".equals(directing)) right+=Integer.parseInt(num);
            }
        }

        /** 另一种循环判断方式，但用字符串匹配的方式，效率要低一些 */
        for (String next : regix) {
            // 合规性判断
            if (!next.matches("[ADWS][0-9]{1,2}")) continue;
            int num = Integer.parseInt(next.substring(1));
            switch (next.charAt(0)){
                case 'A':
                    left-=num;
                    break;
                case 'D':
                    left+=num;
                    break;
                case 'S':
                    right-=num;
                    break;
                case 'W':
                    right+=num;
                    break;
            }
        }

        System.out.println(left + "," + right);
    }

    public static boolean isNumeric(final CharSequence cs) {
        // 通过 length() 方法计算cs传入进来的字符串的长度，并将字符串长度存放到sz中
        final int sz = cs.length();
        // 通过字符串长度循环
        for (int i = 0; i < sz; i++) {
            // 判断每一个字符是否为数字，如果其中有一个字符不满足，则返回false
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        // 验证全部通过则返回true
        return true;
    }
}
