package NiuKe;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 字符串排序
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/10 15:42
 */
public class HW14 {
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (!line.equals(""))list.add(line);
        }
        Collections.sort(list);
        for (String key : list) {
            System.out.println(key);
        }
    }

    // 使用自己实现的sort方法
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (!line.equals(""))list.add(line);
        }
        list.sort((s1, s2) -> {
            int i = 0;
            while (i < s1.length() && i < s2.length()) {
                if (s1.charAt(i) > s2.charAt(i)) {
                    return 1;
                } else if (s1.charAt(i) < s2.charAt(i)) {
                    return -1;
                } else i++;
            }
            return s1.length() - s2.length();
        });

        list.forEach(System.out::println);
    }
}
