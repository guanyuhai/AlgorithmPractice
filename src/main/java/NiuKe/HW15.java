package NiuKe;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 求int型正整数在内存中存储时 1 的个数
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/10 16:00
 */
public class HW15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = 19934318;//sc.nextInt();

        // 转为二进制
        int count = 0;
        for(int i=0; i < 32; i++){
            if((num&1) == 1)    //如果末位为1则计数
                count++;
            num = num >>> 1;    //无符号右移
        }

        System.out.println(count);
    }

}
