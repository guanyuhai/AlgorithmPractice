package Sort;

import Stack.Array;

public class Test {
    public static void main(String[] args) {
        Array<Integer> arr = new Array(20);
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1,100);
        System.out.println(arr);

        // 测试删除所有元素e
        Array<Integer> arr2 = new Array(3);
        arr2.addLast(1);
        arr2.addLast(2);
        arr2.addLast(2);
        //System.out.println(arr2);
        //arr2.removeAllElement(2);
        System.out.println(arr2);

        // 测试查找到所有元素e所在的索引位置
        System.out.println(arr2.findAll(2));
    }
}
