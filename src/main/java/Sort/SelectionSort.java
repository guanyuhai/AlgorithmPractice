package Sort;

import java.util.*;

public class SelectionSort {
    // 构建私有构造方法，不被外部创建实例
    private SelectionSort(){}

    public void main(String[] args) throws ClassNotFoundException {
        Integer[] datas = {6,4,2,3,1,5};

        /** 基本数据类型测试 */
        Integer[] sort_datas = select_sort2(datas);
        for (int i = 0; i < sort_datas.length; i++) {
            System.out.print(sort_datas[i] + " ");
        }
        System.out.println();

        /** 自定义类排序测试 */
        Student[] students = {new Student("Alice",98),
                new Student("coco",100),new Student("zsan",80)};
        Student[] sort_stu = select_sort2(students);
        for (Student student : sort_stu) {
            System.out.println(student +" ");
        }

        /** 性能测试 */
        int[] dataSize = {10000,100000};
        for (int n : dataSize) {
            Integer[] arr = SortHelper.generateArray(n,n);
            SortHelper.sortTest(this.getClass().getName(),arr);
        }

    }

    /**
     * 选择排序,需要开辟新空间的做法
     * 循环不变量：arr[i...n]未排序，arr[0...i]已排序
     * @param datas
     * @return
     */
    public static int[] select_sort(Integer[] datas) {
        int[] sort_datas = new int[datas.length];
        List<Integer> list = Arrays.asList(datas);
        List<Integer> datatList = new ArrayList<Integer>(list);
        for (int i = 0; i < sort_datas.length; i++) {
            int min = datas[0];
            int ind = 0;
            // 找到当前列表中的最小值，与最小值所在的index索引位置
            for (int j = 1; j < datatList.size(); j++) {
                if (datatList.get(j) < min) {
                    min = datatList.get(j);
                    ind = j;
                }
            }
            // 开辟新空间的做法
            sort_datas[i] = min;
            if (datatList.size()>=1) datatList.remove(ind);
        }
        return sort_datas;
    }

    /**
     * 选择排序，原地排序法，不开辟新空间
     * 实现Comparable接口，要求约束类要能比较
     * 这是基于比较的排序算法
     * 循环不变量：arr[i...n]未排序，arr[0...i]已排序
     * @param datas
     * @return
     */
    public static <E extends Comparable<E>> E[] select_sort2(E[] datas) {
        for (int i = 0; i < datas.length; i++) {
            E min = datas[i];
            int minInd = i;
            // 找到当前列表中的最小值，与最小值所在的index索引位置
            for (int j = i; j < datas.length; j++) {
                if (datas[j].compareTo(min) < 0) {
                    min = datas[j];
                    minInd = j;
                }
            }
            datas[minInd] = datas[i];
            datas[i] = min;
        }
        return datas;
    }

    /**
     * 改成从后往前排序，从后面开始，就得从大到小
     * @param datas
     * @param <E>
     * @return
     */
    public static <E extends Comparable<E>> E[] select_sort3(E[] datas) {
        for (int i = datas.length; i >=0 ; i--) {
            E min = datas[i];
            int minInd = i;
            // 找到当前列表中的最小值，与最小值所在的index索引位置
            for (int j = i; j >=0 ; j--) {
                if (datas[j].compareTo(min) > 0) {
                    min = datas[j];
                    minInd = j;
                }
            }
            datas[minInd] = datas[i];
            datas[i] = min;
        }
        return datas;
    }
}
