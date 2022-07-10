package NiuKe;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 句子逆序
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/10 15:32
 */
public class HW13 {
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String line = sc.nextLine();
            StringBuilder sb = new StringBuilder();
            String[] strings = line.split(" ");
            int length = strings.length;
            for (int i = length-1; i >= 0 ; i--) {
                sb.append(strings[i]);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }

    /** 高级写法：双向链表 */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Deque<String> words = new LinkedList<>();
            for (String word:line.split("\\s+")) {
                words.addFirst(word);
            }
            System.out.println(String.join(" ", words));
        }
    }
}
