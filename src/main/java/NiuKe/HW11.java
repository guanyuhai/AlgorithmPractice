package NiuKe;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 数字颠倒
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/10 15:16
 */
public class HW11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nums = sc.nextInt();
        int copy = nums;
        while (nums != 0) {
            System.out.print(nums%10);
            nums/=10;
        }
        if (copy==0) System.out.println(copy);

    }
}
