package NiuKe;

import BST.LinkedListSet;

import java.util.*;

/**
 * @author JavaClimber
 * @version 1.0
 * @date 2022/7/9 20:46
 */
public class HW3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();

        ArrayList<Integer> nums = new ArrayList<>();
        while (in.hasNext()) {
            int num = in.nextInt();
            if (nums.contains(num)) continue;
            nums.add(num);
        }

        Collections.sort(nums);

        int size = nums.size();
        for (int i = 0; i < size; i++) {
            System.out.println(nums.get(i));
        }


        // 题目要求的去重、排序，实际就是TreeSet的操作
        Scanner sc = new Scanner(System.in);
        //获取个数
        int num = sc.nextInt();
        //创建TreeSet进行去重排序
        TreeSet set = new TreeSet();
        //输入
        for(int i =0 ; i < num ;i++){
            set.add(sc.nextInt());
        }

        //输出
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
    public static int[] sort(int[] datas) {
        int length = datas.length;
        for (int i = length-1; i >= 0; i--) {
            // 先把倒数第二个元素保留，如果倒数第一个比倒数第二个小，倒数第二个的位置就会被顶替
            int tmp = datas[i];
            int j;
            // 当i=length-1时，就不会进入循环，因为+1后不小于length了
            for (j = i; j + 1 < length; j++) {
                // 如果倒数第一个元素比倒数第二个元素小，则将倒数第二个元素向后移动
                // 这里datas[j]为倒数第二的元素，datas[j+1]为倒数第一的元素
                if (datas[j+1] < datas[j]){
                    datas[j] = datas[j+1];
                } else break;
            }
            datas[j] = tmp;
        }
        return datas;
    }
}
