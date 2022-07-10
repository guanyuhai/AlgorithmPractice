package NiuKe;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 合并表记录
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/10 10:29
 */
public class HW8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 输入键值对的个数
        int size = scanner.nextInt();

        HashMap<Integer,Integer> result = new HashMap<>(size);
        while (scanner.hasNext()) {
            int key = scanner.nextInt();
            int value = scanner.nextInt();

            // 改用getOrDefault()方法，减少判断
            result.put(key,result.getOrDefault(key,0)+value);
            /*if (!result.isEmpty() && result.containsKey(key))
                result.put(key,result.get(key)+value);
            else result.put(key,value);*/
        }

        for (int k:result.keySet()) {
            System.out.println(k + " " + result.get(k));
        }
    }
}
