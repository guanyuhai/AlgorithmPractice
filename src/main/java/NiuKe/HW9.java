package NiuKe;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 提取不重复的整数
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/10 10:51
 */
public class HW9 {
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = 9876673;//scanner.nextInt();
        String str = String.valueOf(num);
        // 存储倒序遍历的不重复数字
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        sb.append(str.charAt(length-1));
        for (int i = length-2; i >=0; i--) {
            String text = sb.toString();
            char c = str.charAt(i);
            String str2 = String.valueOf(c);
            if (!text.contains(str2))
                sb.append(str.charAt(i));
        }

        System.out.println(Integer.parseInt(sb.toString()));
    }

    /** 解法2 */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 使用HashSet来判断是否是不重复的
        HashSet<Integer> hs = new HashSet<>();
        int target = sc.nextInt();// 获取代求解的值
        while(target != 0){ // 求解每位上面的整数
            int temp = target % 10;
            if(hs.add(temp)) // 如果能加入，就是说明没有重复
                System.out.print(temp);
            target /= 10;// 除10能去掉最右边的数字
        }
        System.out.println();
    }

}
