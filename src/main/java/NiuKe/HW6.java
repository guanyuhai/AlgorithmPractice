package NiuKe;
import java.util.Scanner;
/**
 * 质数因子
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/10 9:32
 */
public class HW6 {
    public static void main(String[] args) {
        /** 当前算法复杂度过高 */
        Scanner in = new Scanner(System.in);
        int num = 64577;//in.nextInt();

        // 收集质数因子
        StringBuilder sb = new StringBuilder();
        int index = 2;
        // 当index小于输入整数时，继续查找
        while (index <= num) {
            if (num % index == 0) {
                if (index == num) {
                    sb.append(index).append(" ");
                    break;
                } else {
                    sb.append(index).append(" ");
                    // 缩小查找范围
                    // 如果num还有其他质数因子，则num>index，反之找到所有质数因子，直接跳出
                    num = num / index;
                }
            } else {
                index++;
            }
        }

        System.out.println(sb.toString());
    }

    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long num = 180;//scanner.nextLong();
        // 优化：没必要从2循环到num，可以循环到根号num，如果此时数字还没用除数，则可判断其本身是一个质数
        long k = (long) Math.sqrt(num);

        for (long i = 2; i <= k; ++i) {
            while (num % i == 0) {
                System.out.print(i + " ");
                num /= i;
            }
        }
        System.out.println(num == 1 ? "": num+" ");
    }
}
