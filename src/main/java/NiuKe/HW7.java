package NiuKe;

import java.util.Scanner;

/**
 * 取近似值
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/10 10:05
 */
public class HW7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = "1.499";//scanner.nextLine();
        String[] strs = str.split("\\.");
        int prefix_num = Integer.parseInt(strs[0]);
        int last_num = Integer.parseInt(strs[1].substring(0,1));
        if (last_num>=5) prefix_num+=1;

        System.out.println(prefix_num);
    }
}
